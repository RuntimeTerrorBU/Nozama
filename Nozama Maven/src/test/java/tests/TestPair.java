package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import nozamaFiles.*;

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
