package nozamaFiles;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {
	private String username;
	private String password;
	private String shippingAddress;
	private static int incrementalID = 0;
	private int customerID;
	private ShoppingCart wishlist;
	private Map<String, String> customerInformation = new HashMap<String, String> ();
	private Boolean isCompany;
	
	//added by Austin
	private ShoppingCart customerCart;
	private File cartFile;
	
	public Boolean getIsCompany() {
		return isCompany;
	}
	
	public void setIsCompany(Boolean b) {
		this.isCompany = b;
	}
	
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
	
	public int getCustomerID() {
		return customerID;
	}
	
	public ShoppingCart getWishlist() {
		return wishlist;
	}
	
	public void setWishlist(ShoppingCart wishlist) {
		this.wishlist = wishlist;
	}
	
	public Map<String, String> getCustomerInformation() {
		return this.customerInformation;
	}
	
	public void setCustomerInformation(Map<String, String> customerInformation) {
		this.customerInformation = customerInformation;
	}
	
	public ShoppingCart getCustomerCart() {
		return customerCart;
	}
	
	public void setCustomerCart(ShoppingCart customerCart) {
		this.customerCart = customerCart;
	}
	
	public File getCartFile() {
		return cartFile;
	}
	
	public void setCartFile(File cartFile) {
		this.cartFile = cartFile;
	}
	
	public boolean login(String username, String password) {
		boolean toReturn = false;
		
		if(password == this.getCustomerInformation().get(username)) {
			toReturn = true;
		}
		
		return toReturn;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerCart == null) ? 0 : customerCart.hashCode());
		result = prime * result + customerID;
		result = prime * result + ((customerInformation == null) ? 0 : customerInformation.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((shippingAddress == null) ? 0 : shippingAddress.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((wishlist == null) ? 0 : wishlist.hashCode());
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
		if (customerCart == null) {
			if (other.customerCart != null)
				return false;
		} else if (!customerCart.equals(other.customerCart))
			return false;
		if (customerID != other.customerID)
			return false;
		if (customerInformation == null) {
			if (other.customerInformation != null)
				return false;
		} else if (!customerInformation.equals(other.customerInformation))
			return false;
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
		if (wishlist == null) {
			if (other.wishlist != null)
				return false;
		} else if (!wishlist.equals(other.wishlist))
			return false;
		return true;
	}
	
	public Customer(String username, String password, String shippingAddress, int customerID, ShoppingCart wishlist) {
		super();
		this.username = username;
		this.password = password;
		this.shippingAddress = shippingAddress;
		this.customerID = incrementalID;
		this.incrementalID++;
		this.wishlist = wishlist;
		this.customerInformation.put(username, password);
		
		//FIXME added for testing purposes, will need to update to work with different customers
		this.wishlist = new ShoppingCart();
		this.customerCart = new ShoppingCart();
		try {
			File f = new File("resources/testCart.csv");
			this.customerCart.loadCart(f);
			this.cartFile = f;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", shippingAddress=" + shippingAddress
				+ ", customerID=" + customerID + ", wishList=" + wishlist + ", customerInformation="
				+ customerInformation + "]";
	}
}
