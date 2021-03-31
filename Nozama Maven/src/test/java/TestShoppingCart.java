import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import nozamaFiles.Item;
import nozamaFiles.Pair;
import nozamaFiles.ShoppingCart;

public class TestShoppingCart {
	private ShoppingCart sc = null;
	
	@BeforeEach
	void init() {
		sc = new ShoppingCart();
	}
	
	@Test
	void testNullLoad() {
		assertThrows(NullPointerException.class, () -> sc.loadCart(null));
	}
	
	@Test
	void testValidLoad() {
		File file = new File("resources/testCart.csv");
		assertDoesNotThrow(() -> sc.loadCart(file));
	}
	
	@Test
	void testCorrectValuesFromLoad() {
		File file = new File("resources/testCart.csv");
		
		// create the correct list of items and quantities
		List<Pair<Item, Integer>> cart = new ArrayList<Pair<Item, Integer>>();
		cart.add(new Pair<Item, Integer>(new Item("1"), (Integer)(10)));
		cart.add(new Pair<Item, Integer>(new Item("2"), (Integer)(20)));
		cart.add(new Pair<Item, Integer>(new Item("3"), (Integer)(30)));
		cart.add(new Pair<Item, Integer>(new Item("4"), (Integer)(40)));
		cart.add(new Pair<Item, Integer>(new Item("5"), (Integer)(50)));
		// create a shopping cart with the list
		ShoppingCart sc2 = new ShoppingCart(cart, 0.0);
		
		// read the csv file
		try {
			sc.loadCart(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// see if they are equal
		assertEquals(sc2, sc);
	}
	
	@Test
	void testAddItemToCart() {
		File file = new File("resources/testCart.csv");
		
		// create the correct list of items and quantities
		List<Pair<Item, Integer>> cart = new ArrayList<Pair<Item, Integer>>();
		cart.add(new Pair<Item, Integer>(new Item("1"), (Integer)(10)));
		cart.add(new Pair<Item, Integer>(new Item("2"), (Integer)(20)));
		cart.add(new Pair<Item, Integer>(new Item("3"), (Integer)(30)));
		cart.add(new Pair<Item, Integer>(new Item("4"), (Integer)(40)));
		cart.add(new Pair<Item, Integer>(new Item("5"), (Integer)(50)));
		cart.add(new Pair<Item, Integer>(new Item("6"), (Integer)(60)));
		// create a shopping cart with the list
		ShoppingCart sc2 = new ShoppingCart(cart, 0.0);
		
		// read the csv file
		try {
			sc.loadCart(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// add the item
		sc.addItemToCart(new Item("6"), 60);
		
		// see if they are equal
		assertEquals(sc2, sc);
	}
}
