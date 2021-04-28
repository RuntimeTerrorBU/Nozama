package nozamaFiles;

import java.util.List;

import javax.swing.JOptionPane;


/**
 * The PaymentInfo class specifies the payment information of a user
 *
 * @author - Ashley Bickham, Joshua Hunter, Austin Lehman, Tyler Ross
 * @version 1.0 (Apr 27, 2021)
 */
public class PaymentInfo {
	
	public static final int VALID_CARD_NUMBER = 19;
	public static final int VALID_CARD_NUMBER2 = 16;
	public static final int VALID_CVC_NUMBER = 3;
	
	//Card Variables
	private String cardNumber;
	private String cvc;
	private String billingAddress;
	
	/**
	 * PaymentInfo constructor
	 */
	public PaymentInfo() {
		this.cardNumber = "9999-9999-9999-9999";
		this.cvc = "999";
		this.billingAddress = "";
	}
	
	/**
	 * PaymentInfo constructor to be made with user inputted information
	 * 
	 * @param string representing the card number
	 * @param string representing the cvc number
	 * @param string representing the billing address
	 */
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
	
	/**
	 * Check if the payment information is valid
	 * 
	 * @param string representing the card number
	 * @param string representing the cvc number
	 * @return true if the card info is valid, false otherwise
	 */
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
	
	/**
	 * Get the card number
	 * 
	 * @return String representing the card number
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * Set the card number
	 * 
	 * @param String representing the card number
	 * @return void
	 */
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

	/**
	 * Get the card cvc number
	 * 
	 * @return String representing the cardcvc  number
	 */
	public String getCvc() {
		return cvc;
	}

	/**
	 * Set the card cvc number
	 * 
	 * @param String representing the card cvc number
	 * @return void
	 */
	public void setCvc(String c) {
		if(c.length() == VALID_CVC_NUMBER) {
			this.cardNumber = c;
		}
	}

	/**
	 * Get the billing address
	 * 
	 * @return String representing the billing address
	 */
	public String getBillingAddress() {
		return billingAddress;
	}

	/**
	 * Set the billing address
	 * 
	 * @param String representing the billing address
	 * @return void
	 */
	public void setBillingAddress(String ba) {
		this.billingAddress = ba;
	}

	@Override
	/**
	 * Creates a specific mapping to a value
	 *
	 * @return integer hashed value of the input value
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billingAddress == null) ? 0 : billingAddress.hashCode());
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((cvc == null) ? 0 : cvc.hashCode());
		return result;
	}

	@Override
	/**
	 * Tells if two objects are equal
	 *
	 * @param object to compare to the object being used
	 * @return true if the two objects are equal, false otherwise
	 */
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

