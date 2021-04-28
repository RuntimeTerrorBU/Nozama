package nozamaFiles;

import java.util.Comparator;

/**
 * The ItemSpecification class specifies the parts of an item
 *
 * @author - Ashley Bickham, Joshua Hunter, Austin Lehman, Tyler Ross
 * @version 1.0 (Apr 27, 2021)
 */
public class ItemSpecification {
	private String name;
	private String description;
	private Double price;
	private String itemID;
	private Integer quantity;

	/**
	 * Create the ItemSpecification using the information entered the user
	 * 
	 * @param name, String representing the name of the item
	 * @param description, String representing the description of the item
	 * @param price, Double representing the price of the item
	 * @param itemID, String representing the id of the item
	 * @param quantity, Integer representing the quantity of the item
	 */
	public ItemSpecification(String name, String description, Double price, String itemID, Integer quantity) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.itemID = itemID;
		this.quantity = quantity;
	}

	/**
	 * Get the item's name
	 *
	 * @return String representing the itme's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the item's name
	 *
	 * @param name, String representing the itme's name to be set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the item's description
	 *
	 * @return String representing the itme's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the item's description
	 *
	 * @param description, String representing the itme's description to be set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the item's price
	 *
	 * @return Double representing the itme's price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Set the item's price
	 *
	 * @param price, Double representing the itme's price to be set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Get the itmems's id
	 *
	 * @return String representing the items's id
	 */
	public String getItemID() {
		return itemID;
	}

	/**
	 * Set the item's id
	 *
	 * @param itemID, String representing the itme's id to be set
	 */
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	/**
	 * Get the itmems's quantity
	 *
	 * @return Integer representing the items's quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * Set the item's quantity
	 *
	 * @param quantity, Integer representing the itme's quantity to be set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	/**
	 * Creates a specific mapping to a value
	 *
	 * @return int hashed value of the input value
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((itemID == null) ? 0 : itemID.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}

	@Override
	/**
	 * Tells if two objects are equal
	 *
	 * @param obj, Object to compare to the object being used
	 * @return boolean. true if the two objects are equal, false otherwise
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemSpecification other = (ItemSpecification) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (itemID == null) {
			if (other.itemID != null)
				return false;
		} else if (!itemID.equals(other.itemID))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

	static final Comparator<ItemSpecification> compareByID = new Comparator<ItemSpecification>() {
		@Override
		/**
		 * Compare two ItemSpecifications
		 *
		 * @param o1, ItemSpecification to compare to the other parameter
		 * @param 02, ItemSpecification to compare to the other parameter
		 * @return int representing the comparison between the two ItemSpecifications
		 */
		public int compare(ItemSpecification o1, ItemSpecification o2) {
			return o1.getItemID().compareTo(o2.getItemID());
		}
	};
}
