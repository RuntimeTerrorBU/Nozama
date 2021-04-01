package nozamaFiles;

import java.util.List;

import javax.swing.JOptionPane;

public class PaymentInfo {
	
	public static final int VALID_CARD_NUMBER = 19;
	public static final int VALID_CARD_NUMBER2 = 16;
	public static final int VALID_CVC_NUMBER = 3;
	
	//Card Variables
	private String cardNumber;
	private String cvc;
	private String billingAddress;
	
	public PaymentInfo() {
		this.cardNumber = "9999-9999-9999-9999";
		this.cvc = "999";
		this.billingAddress = "";
		
	}
	
	public PaymentInfo(String cn, String c, String ba) {
		
		if(validateCardInfo(cn, c)) {
			if(c.length() == VALID_CVC_NUMBER && cn.length() == VALID_CARD_NUMBER) {
				this.cardNumber = cn;
				this.cvc = c;
				this.billingAddress = ba;
			}	
			else if(c.length() == VALID_CVC_NUMBER && cn.length() == VALID_CARD_NUMBER2) {
				
				String formatString = "";
				
				formatString = cn.substring(0, 4) + "-" + cn.substring(4, 8) + "-" + cn.substring(8, 12) + "-" + cn.substring(12, 16);
				
				this.cardNumber = formatString;
				this.cvc = c;
				this.billingAddress = ba;
			}	
		}
	}
	
	public Boolean validateCardInfo(String cn, String c) {
		
		Boolean toReturn = false;
		
		//Return true if input card information is valid (Dashed format)
		if(c.length() == VALID_CVC_NUMBER && cn.length() == VALID_CARD_NUMBER) {
			
			toReturn = true;
			
			for(int i = 0; i < VALID_CARD_NUMBER; i++) {
				
				if(i == 4 || i == 9 || i == 14) {
					if(!(cn.charAt(i) == '-')) {
						toReturn = false;
					}
					i++;
				}
				
				if(!(cn.charAt(i) >= '0' && cn.charAt(i) <= '9')) {
					toReturn = false;
				}
			}
			
			for(int i = 0; i < VALID_CVC_NUMBER; i++) {
				if(!(c.charAt(i) >= '0' && c.charAt(i) <= '9')) {
					toReturn = false;
				}
			}
		}
		//Return true if input card information is valid (Pure number format)
		else if(c.length() == VALID_CVC_NUMBER && cn.length() == VALID_CARD_NUMBER2) {
			
			toReturn = true;
			
			for(int i = 0; i < VALID_CARD_NUMBER2; i++) {
				if(!(cn.charAt(i) >= '0' && cn.charAt(i) <= '9')) {
					toReturn = false;
				}
			}
			
			for(int i = 0; i < VALID_CVC_NUMBER; i++) {
				if(!(c.charAt(i) >= '0' && c.charAt(i) <= '9')) {
					toReturn = false;
				}
			}
		}
		
		return toReturn;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cn) {
		if(cn.length() == VALID_CARD_NUMBER) {
			this.cardNumber = cn;
		}
		else if(cn.length() == VALID_CARD_NUMBER2) {
			
			String formatString = "";
			
			formatString = cn.substring(0, 4) + "-" + cn.substring(4, 8) + "-" + cn.substring(8, 12) + "-" + cn.substring(12, 16);
			
			this.cardNumber = formatString;
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

