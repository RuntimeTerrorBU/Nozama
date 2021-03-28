package nozamaFiles;

public class ItemCatalog {
	private List<Item> items;

	public ItemCatalog(List<Item> items) {
		super();
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public Integer getNumberOfItems() {
		return items.size();
	}
}
