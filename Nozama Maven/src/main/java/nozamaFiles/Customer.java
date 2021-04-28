package nozamaFiles;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The Customer class creates a Customr who will use the Nozama store
 *
 * @author - Ashley Bickham, Joshua Hunter, Austin Lehman, Tyler Ross
 * @version 1.0 (Apr 27, 2021)
 */
public class Customer {
	private String username;
	private String password;
	private String shippingAddress;
	private static int incrementalID = 0;
	private int customerID;
	private ShoppingCart wishlist;
	private Map<String, String> customerInformation = new HashMap<String, String>();
	private Boolean isCompany;

	private ShoppingCart customerCart;
	private File cartFile;
	private File wishListFile;

	/**
	 * Create the Customer using the information of the user
	 * 
	 * @param String representing username passed by the new Customer
	 * @param String representing password passed by the new Customer
	 * @param String representing shipping address of the new Customer
	 * @param int representing the customer ID set to a custom ID for each customer
	 * @param ShoppingCart representing the wishlist for each user
	 * @param map filled with the customer information
	 * @param boolean to check if the Customer is also a Company member
	 * @param shoppingCart filled with items the Customer has in their cart
	 * @param file filled with the items stored in the Customer's cart
	 */
	public Customer(String username, String password, String shippingAddress, int customerID, ShoppingCart wishlist,
			Map<String, String> customerInformation, Boolean isCompany, ShoppingCart customerCart, File cartFile) {
		super();
		this.username = username;
		this.password = password;
		this.shippingAddress = shippingAddress;
		this.customerID = customerID;
		this.customerInformation = customerInformation;
		this.isCompany = isCompany;
		this.cartFile = cartFile;
		this.customerCart = new ShoppingCart();
		this.wishlist = new ShoppingCart();
		
		try {
			// File f = new File("resources/testCart.csv");
			File f = new File("resources/carts/" + username + "Cart.csv");
			this.customerCart.loadCart(f);
			this.cartFile = f;

			File w = new File("resources/wishlists/" + username + "Wishlist.csv");
			
			this.wishlist.loadCart(w);
			this.wishListFile = w;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.wishlist = new ShoppingCart();
		try {
			File f = new File("resources/wishlists/" + username + "Wishlist.csv");
			this.wishlist.loadCart(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check if the Customer is also a Company member
	 *
	 * @return true if the Customer is also a Company member, false otherwise
	 */
	public Boolean getIsCompany() {
		return isCompany;
	}

	/**
	 * Set the Company status of a Customer
	 *
	 * @param true if the Customer is a company member, false otherwise
	 * @return void
	 */
	public void setIsCompany(Boolean b) {
		this.isCompany = b;
	}

	/**
	 * Get the customer's username
	 *
	 * @return string representing the customer's username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set the username of a customer
	 *
	 * @param string representing the username to be used for the customer
	 * @return void
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Get the customer's password
	 *
	 * @return string representing the customer's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the password of a customer
	 *
	 * @param string representing the password to be used for the customer
	 * @return void
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the customer's shipping address
	 *
	 * @return string representing the customer's shipping address
	 */
	public String getShippingAddress() {
		return shippingAddress;
	}

	/**
	 * Set the shipping address of a customer
	 *
	 * @param string representing the shipping address to be used for the customer
	 * @return void
	 */
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	/**
	 * Get the customer's id number
	 *
	 * @return integer representing the customer's id number
	 */
	public int getCustomerID() {
		return customerID;
	}

	/**
	 * Get the customer's wishlist
	 *
	 * @return ShoppingCart representing the customer's wishlist
	 */
	public ShoppingCart getWishlist() {
		return wishlist;
	}

	/**
	 * Set the wishlist of a customer
	 *
	 * @param ShoppingCart representing the wishlist to be used by the customer
	 * @return void
	 */
	public void setWishlist(ShoppingCart wishlist) {
		this.wishlist = wishlist;
	}

	/**
	 * Get the information of the customer
	 *
	 * @return map filled with usernames and passwords
	 */
	public Map<String, String> getCustomerInformation() {
		return this.customerInformation;
	}

	/**
	 * Set the customer information of a customer
	 *
	 * @param Map filled with username and passwords representing the customers
	 * @return void
	 */
	public void setCustomerInformation(Map<String, String> customerInformation) {
		this.customerInformation = customerInformation;
	}

	/**
	 * Get the customer's shopping cart
	 *
	 * @return ShoppingCart representing the customer's shopping cart
	 */
	public ShoppingCart getCustomerCart() {
		return customerCart;
	}

	/**
	 * Set the shopping cart of a customer
	 *
	 * @param ShoppingCart representing the shopping cart to be used by the customer
	 * @return void
	 */
	public void setCustomerCart(ShoppingCart customerCart) {
		this.customerCart = customerCart;
	}

	/**
	 * Get the file of the shopping cart
	 *
	 * @return file filled with the information of the customer's shopping cart
	 */
	public File getCartFile() {
		return cartFile;
	}

	/**
	 * Set the shopping cart file of a customer
	 *
	 * @param file representing the shopping cart file to be used by the customer
	 * @return void
	 */
	public void setCartFile(File cartFile) {
		this.cartFile = cartFile;
	}

	/**
	 * Allows the user to login to Nozama using the username and password they created
	 *
	 * @param string representing the username of the customer trying to login
	 * @param string representing the password of the customer trying to login
	 * @return true if the customer can login, false otherwise
	 */
	public boolean login(String username, String password) {
		boolean toReturn = false;

		if (password == this.getCustomerInformation().get(username)) {
			toReturn = true;
		}

		return toReturn;
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

	/**
	 * Create the Customer using the information of the user
	 * 
	 * @param username passed by the new Customer
	 * @param password passed by the new Customer
	 * @param shipping address of the new Customer
	 * @param customer ID set to a custom ID for each customer
	 * @param wishlist for each user
	 */
	public Customer(String username, String password, String shippingAddress, int customerID, ShoppingCart wishlist) {
		super();
		this.username = username;
		this.password = password;
		this.shippingAddress = shippingAddress;
		this.customerID = incrementalID;
		this.incrementalID++;
		this.wishlist = wishlist;
		this.customerInformation.put(username, password);

		// FIXME added for testing purposes, will need to update to work with different
		// customers
		this.wishlist = new ShoppingCart();
		this.customerCart = new ShoppingCart();
		try {
			// File f = new File("resources/testCart.csv");
			File f = new File("resources/carts/" + username + "Cart.csv");
			this.customerCart.loadCart(f);
			this.cartFile = f;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			File f = new File("resources/carts/" + username + "Wishlist.csv");
			this.wishlist.loadCart(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * Displays all information of a Customer object
	 *
	 * @return string filled with the Customer information
	 */
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", shippingAddress=" + shippingAddress
				+ ", customerID=" + customerID + ", wishList=" + wishlist + ", customerInformation="
				+ customerInformation + "]";
	}
}
