package tests;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import nozamaFiles.*;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBException;
public class TestPair {
	
	@Test
	void testNullPair() {
		Pair<Object, Object> p = new Pair<Object, Object>(null, null);
		
		assertNull(p.first);
	}
	
	@Test
	void testValidPair() {
		String str = "Hi";
		String str2 = "Give me an A";
		
		Pair<String, String> p = new Pair<String, String>(str, str2);
		
		assertEquals(p.first, "Hi");
		assertEquals(p.second, "Give me an A");
	}
}
