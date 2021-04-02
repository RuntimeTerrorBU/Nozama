import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import nozamaFiles.PaymentInfo;
import nozamaFiles.ShoppingCart;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBException;

public class TestPaymentInfo {
	
	PaymentInfo testVal;

	@BeforeEach
	void init() {
		testVal = new PaymentInfo();
	}
	
	@Test
	public void testVerificiation() {
		assertTrue(testVal.validateCardInfo("9999-9999-9999-9999", "123"));	
	}
	
	@Test
	public void testVerificiation2() {
		assertFalse(testVal.validateCardInfo("9999a999999999999", "101"));	
	}
	
	@Test
	public void testNullVerify() {
		assertThrows(NullPointerException.class, () -> {
			testVal.validateCardInfo(null, null);
		});
	}

}
