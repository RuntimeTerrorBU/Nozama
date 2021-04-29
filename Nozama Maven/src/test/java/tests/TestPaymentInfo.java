package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nozamaFiles.PaymentInfo;


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
