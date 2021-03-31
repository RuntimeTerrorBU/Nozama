package nozamaFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	List<Pair<Item, Integer>> cart;
	Double subtotal = 0.0;
	
	public ShoppingCart() {
		this.cart = new ArrayList<Pair<Item, Integer>>();
	}
	public ShoppingCart(List<Pair<Item, Integer>> cart, Double subtotal) {
		super();
		this.cart = cart;
		this.subtotal = subtotal;
	}
	public List<Pair<Item, Integer>> getCart() {
		return cart;
	}
	public void setCart(List<Pair<Item, Integer>> cart) {
		this.cart = cart;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	
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
	}
	
	
	
	
	@Override
	public String toString() {
		String str = "";
		
		for(Pair<Item, Integer>p:cart) {
			str += p.first.getItemID() + "," + p.second.toString() + '\n';
		}
		
		return str;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
		result = prime * result + ((subtotal == null) ? 0 : subtotal.hashCode());
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
	
	
	
	
}
