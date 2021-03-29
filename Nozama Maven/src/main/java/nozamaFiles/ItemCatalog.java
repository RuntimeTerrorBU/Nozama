package nozamaFiles;

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
		int ndx = Collections.binarySearch(ItemCatalog.items, new ItemSpecification(null, null, itemID, null), ItemSpecification.compareByID);
		
		return ItemCatalog.items.get(ndx);
	}
}
