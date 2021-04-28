package nozamaFiles;

import java.util.List;

/**
 * The SaleLineItem class specifies the amount of item sold on Nozama store
 *
 * @author - Ashley Bickham, Joshua Hunter, Austin Lehman, Tyler Ross
 * @version 1.0 (Apr 27, 2021)
 */
public class SalesLineItem {
	private int quantity;
	
	/**
	 * Get the SalesLineItem quantity
	 * 
	 * @return int representing the SaleLineItem quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Set the SalesLineItem quantity
	 * 
	 * @param q, int representing the SaleLineItem quantity
	 */
	public void setQuantity(int q) {
		this.quantity = q;
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
		result = prime * result + quantity;
		return result;
	}
	
	@Override
	/**
	 * Tells if two objects are equal
	 *
	 * @param obj, Object to compare to the object being used
	 * @return boolean, true if the two objects are equal, false otherwise
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesLineItem other = (SalesLineItem) obj;
		if (quantity != other.quantity)
			return false;
		return true;
	}
}
