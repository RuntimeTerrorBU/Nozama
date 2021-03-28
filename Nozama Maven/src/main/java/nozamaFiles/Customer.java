package nozamaFiles;

import java.util.List;

public class Customer {
	private String username;
	private String password;
	private String shippingAddress;
	private List<String> wishList;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public List<String> getWishList() {
		return wishList;
	}
	public void setWishList(List<String> wishList) {
		this.wishList = wishList;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((shippingAddress == null) ? 0 : shippingAddress.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((wishList == null) ? 0 : wishList.hashCode());
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
		Customer other = (Customer) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (shippingAddress == null) {
			if (other.shippingAddress != null)
				return false;
		} else if (!shippingAddress.equals(other.shippingAddress))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (wishList == null) {
			if (other.wishList != null)
				return false;
		} else if (!wishList.equals(other.wishList))
			return false;
		return true;
	}
	
	public Customer(String username, String password, String shippingAddress, List<String> wishList) {
		super();
		this.username = username;
		this.password = password;
		this.shippingAddress = shippingAddress;
		this.wishList = wishList;
	}	
	
	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", shippingAddress=" + shippingAddress
				+ ", wishList=" + wishList + "]";
	}
}
