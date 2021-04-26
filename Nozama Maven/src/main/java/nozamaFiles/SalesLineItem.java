package nozamaFiles;

import java.util.List;

public class SalesLineItem {
	private int quantity;
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int q) {
		this.quantity = q;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + quantity;
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
		SalesLineItem other = (SalesLineItem) obj;
		if (quantity != other.quantity)
			return false;
		return true;
	}
}
