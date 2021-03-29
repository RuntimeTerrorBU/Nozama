package nozamaFiles;

import java.util.List;

public class ShoppingCart {
	List<Pair<Item, Integer>> cart;
	Double subtotal = 0.0;
	
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
		Double itemPrice = ItemCatalog.getItemSpecification(item.getItemID()).getPrice();
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
