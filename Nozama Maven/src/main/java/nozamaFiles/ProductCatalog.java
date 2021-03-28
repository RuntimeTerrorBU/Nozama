package nozamaFiles;

public class ProductCatalog {
	private Integer numberOfItems;
	private ArrayList<Product> products;
	
	public ProductCatalog(Integer numberOfItems, ArrayList<Product> products) {
		super();
		this.numberOfItems = numberOfItems;
		this.products = products;
	}

	public Integer getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(Integer numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numberOfItems == null) ? 0 : numberOfItems.hashCode());
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
		ProductCatalog other = (ProductCatalog) obj;
		if (numberOfItems == null) {
			if (other.numberOfItems != null)
				return false;
		} else if (!numberOfItems.equals(other.numberOfItems))
			return false;
		return true;
	}
	
	
}
