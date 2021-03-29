package nozamaFiles;

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
		ItemCatalog.items = items;
	}
}
