package nozamaFiles;

import java.util.List;

public class PaymentInfo {
	
	public static final int VALID_CVC_NUMBER = 19;
	public static final int VALID_CARD_NUMBER = 3;
	
	//Card Variables
	private String cardNumber;
	private String cvc;
	private String billingAddress;
	
	PaymentInfo() {
		this.cardNumber = "9999-9999-9999-9999";
		this.cvc = "999";
		this.billingAddress = "";
		
	}
	
	PaymentInfo(String cn, String c, String ba) {
		
		if(c.length() == VALID_CVC_NUMBER && cn.length() == VALID_CARD_NUMBER) {
			this.cardNumber = cn;
			this.cvc = c;
			this.billingAddress = ba;
		}		
	}
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cn) {
		if(cn.length() == VALID_CARD_NUMBER) {
			this.cardNumber = cn;
		}
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String c) {
		if(c.length() == VALID_CVC_NUMBER) {
			this.cardNumber = c;
		}
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String ba) {
		this.billingAddress = ba;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billingAddress == null) ? 0 : billingAddress.hashCode());
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((cvc == null) ? 0 : cvc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentInfo other = (PaymentInfo) obj;
		if (billingAddress == null) {
			if (other.billingAddress != null)
				return false;
		} else if (!billingAddress.equals(other.billingAddress))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (cvc == null) {
			if (other.cvc != null)
				return false;
		} else if (!cvc.equals(other.cvc))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PaymentInfo - CardNumber: " + cardNumber + ", CVC = " + cvc + ", Billing Address: " + billingAddress;
	}
	
	
}

