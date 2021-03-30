package nozamaFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemCatalog {
	private static List<ItemSpecification> items;
	
	public ItemCatalog(List<ItemSpecification> items) {
		ItemCatalog.items = items;
	}
	
	public static Integer getNumberOfItems() {
		return items.size();
	}

	public static List<ItemSpecification> getItems() {
		return items;
	}

	public static void setItems(List<ItemSpecification> items) {
		Collections.sort(items, ItemSpecification.compareByID);
		ItemCatalog.items = items;
	}
	
	public static ItemSpecification getItemSpecification(String itemID) {
		int ndx = Collections.binarySearch(ItemCatalog.items, new ItemSpecification(null, null, null, itemID, null), ItemSpecification.compareByID);
		
		return ItemCatalog.items.get(ndx);
	}
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
		
		while((line = in.readLine()) != null) {
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
