package nozamaFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The ShoppingCart class creates a ShoppingCart item for each customer
 * in the Nozama Store
 *
 * @author - Ashley Bickham, Joshua Hunter, Austin Lehman, Tyler Ross
 * @version 1.0 (Apr 27, 2021)
 */
public class ShoppingCart {
	List<Pair<Item, Integer>> cart;
	Double subtotal = 0.0;
	
	/**
	 * Create the blank, default ShoppingCart item for a Customer
	 */
	public ShoppingCart() {
		this.cart = new ArrayList<Pair<Item, Integer>>();
	}
	
	/**
	 * Create the ShoppingCart item for a Customer
	 * 
	 * @param cart, List of items and the amount of the item to store as a cart
	 * @param subtotal, Double representing the total amount (in dollars) of the items in the cart
	 */
	public ShoppingCart(List<Pair<Item, Integer>> cart, Double subtotal) {
		super();
		this.cart = cart;
		this.subtotal = subtotal;
	}
	
	/**
	 * Get the shopping cart
	 *
	 * @return List of pairs of items and amounts representing the shopping cart
	 */
	public List<Pair<Item, Integer>> getCart() {
		return cart;
	}
	
	/**
	 * Set the shopping cart
	 *
	 * @param cart, List of pairs of items and amounts representing the shopping cart
	 */
	public void setCart(List<Pair<Item, Integer>> cart) {
		this.cart = cart;
	}
	
	/**
	 * Get the shopping cart subtotal
	 *
	 * @return Double representing the subtotal of the items in the cart
	 */
	public Double getSubtotal() {
		return subtotal;
	}
	
	/**
	 * Set the subtotal of the shopping cart
	 *
	 * @param subtotal, Double representing the subtotal of the items in the cart
	 */
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	
	
	/**
	 * Load the data into Shopping Cart
	 * 
	 * @param file, File to load the data into a shopping cart
	 * @throws IOException if the file is not able to be used
	 */
	public void loadCart(File file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line;
		String id;
		Integer quantity;
		
		while((line = in.readLine()) != null) {
			String[] data = line.split(",");
			id = data[0];
			quantity = Integer.parseInt(data[1]);
			
			cart.add(new Pair<Item, Integer>(new Item(id), quantity));
		}
		in.close();
		
		subtotal = calculateSub();
	}
	
	/**
	 * Calculate the subtotal of the items inside the shopping cart
	 * 
	 * @return double representing the subtotal of the items in the shopping cart
	 */
	public double calculateSub() {
		double itemPrice;
		double res = 0.0;
		for(Pair<Item, Integer> p:cart) {
			if (ItemCatalog.getItemSpecification(p.first.getItemID()) != null) {
				itemPrice = ItemCatalog.getItemSpecification(p.first.getItemID()).getPrice();
				res += itemPrice * p.second;
			}
		}
		return res;
	}
	
	@Override
	/**
	 * Displays all information of a ShoppingCart object
	 *
	 * @return string filled with the ShoppingCart information
	 */
	public String toString() {
		String str = "";
		
		for(Pair<Item, Integer>p:cart) {
			str += p.first.getItemID() + "," + p.second.toString() + '\n';
		}
		
		return str;
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
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
		result = prime * result + ((subtotal == null) ? 0 : subtotal.hashCode());
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
		ShoppingCart other = (ShoppingCart) obj;
		if (cart == null) {
			if (other.cart != null)
				return false;
		} else if (!cart.equals(other.cart))
			return false;
		if (subtotal == null) {
			if (other.subtotal != null)
				return false;
		} else if (!subtotal.equals(other.subtotal))
			return false;
		return true;
	}
	
	/**
	 * Add an item to the shopping cart
	 *
	 * @param item, Item representing the item to be added to the cart
	 * @param quantity, Integer representing the quantity of the item to be added
	 */
	public void addItemToCart(Item item, Integer quantity) {
		boolean alreadyInCart = false;
		Double itemPrice = 0.0;
		if (ItemCatalog.getItemSpecification(item.getItemID()) != null) {
			itemPrice = ItemCatalog.getItemSpecification(item.getItemID()).getPrice();
		}
		for(Pair<Item, Integer> p : cart) {
			if(p.first.equals(item)) {
				p.second += quantity;
				alreadyInCart = true;
			}
		}
		if(!alreadyInCart) {
			cart.add(new Pair<Item, Integer>(item, quantity));
		}
		
		subtotal += itemPrice * quantity;
	}	
	
	/**
	 * Remove an item to the shopping cart
	 *
	 * @param item, Item representing the item to be removed from the cart
	 * @param quantitiy, Integer representing the quantity of the item to be removed
	 * 
	 */
	public void removeItemFromCart(Item item, Integer quantity) {
		Double itemPrice = 0.0;
		if (ItemCatalog.getItemSpecification(item.getItemID()) != null) {
			itemPrice = ItemCatalog.getItemSpecification(item.getItemID()).getPrice();
		}
		for(int i = 0; i < cart.size(); i++) {
			Pair<Item, Integer> p = cart.get(i);
			//if item is in cart
			if(p.first.getItemID().equals(item.getItemID())) {
				if(p.second.equals(quantity)) {
					cart.remove(i);
					subtotal -= itemPrice * quantity;
				}
				else if(p.second > quantity) {
					p.second -= quantity;
					subtotal -= itemPrice * quantity;
				}
			}
		}
	}
}