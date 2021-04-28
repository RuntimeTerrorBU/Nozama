package nozamaFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The ItemCatalog class creates an the catalog of an item
 *
 * @author - Ashley Bickham, Joshua Hunter, Austin Lehman, Tyler Ross
 * @version 1.0 (Apr 27, 2021)
 */
public class ItemCatalog {
	private static List<ItemSpecification> items = new ArrayList<ItemSpecification>();

	/**
	 * ItemCatalog constructor
	 * 
	 * @param List of ItemSpecification representing the catalog of items
	 */
	public ItemCatalog(List<ItemSpecification> items) {
		ItemCatalog.items = items;
	}

	/**
	 * Get the number of items in the Nozama store
	 * 
	 * @return static integer representing the number of items
	 */
	public static Integer getNumberOfItems() {
		return items.size();
	}

	/**
	 * Get the data of items in the Nozama store
	 * 
	 * @return List representing the items in the Nozama Store
	 */
	public static List<ItemSpecification> getItems() {
		return items;
	}

	/**
	 * Set the data of items in the Nozama store
	 * 
	 * @param List representing the items to be set in the Nozama Store
	 * @return void
	 */
	public static void setItems(List<ItemSpecification> items) {
		Collections.sort(items, ItemSpecification.compareByID);
		ItemCatalog.items = items;
	}
	
	/**
	 * Set the item specification of item wanted in the Nozama store
	 * 
	 * @param String representing the id of the item to be set
	 * @return ItemSpecification of the item set
	 */
	public static ItemSpecification getItemSpecification(String itemID) {
		int ndx = Collections.binarySearch(ItemCatalog.items, new ItemSpecification(null, null, null, itemID, null),
				ItemSpecification.compareByID);

		if (ndx != -1) {
			return ItemCatalog.items.get(ndx);
		}
		return null;
	}

	/**
	 * Load the data into the Item Catalog
	 * 
	 * @param File to load the data into an item catalog
	 * @throws IOException if he file is not able to be used
	 */
	public static void loadData(File file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line;
		String[] data;
		List<ItemSpecification> myItems = new ArrayList<ItemSpecification>();
		String name;
		String description;
		Double price;
		String itemID;
		Integer quantity;

		while ((line = in.readLine()) != null) {
			data = line.split(",");
			name = data[0];
			description = data[1];
			price = Double.parseDouble(data[2]);
			itemID = data[3];
			quantity = Integer.parseInt(data[4]);

			myItems.add(new ItemSpecification(name, description, price, itemID, quantity));
		}

		in.close();

		ItemCatalog.items = myItems;
	}
}
