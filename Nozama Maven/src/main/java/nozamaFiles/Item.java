package nozamaFiles;

import nozamaFiles.Item;

/**
 * The Item class creates an item used in the Nozama store
 *
 * @author - Ashley Bickham, Joshua Hunter, Austin Lehman, Tyler Ross
 * @version 1.0 (Apr 27, 2021)
 */
public class Item {
	private String itemID;

	/**
	 * Item constructor
	 * 
	 * @param string representing the id of the item
	 */
	public Item(String itemID) {
		super();
		this.itemID = itemID;
	}

	/**
	 * Get the item's id number
	 *
	 * @return string representing the item's id
	 */
	public String getItemID() {
		return itemID;
	}

	/**
	 * Set the item's id number
	 *
	 * @param string representing the item's id
	 * @return void
	 */
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	@Override
	/**
	 * Creates a specific mapping to a value
	 *
	 * @return integer hashed value of the input value
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemID == null) ? 0 : itemID.hashCode());
		return result;
	}

	@Override
	/**
	 * Tells if two objects are equal
	 *
	 * @param object to compare to the object being used
	 * @return true if the two objects are equal, false otherwise
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (itemID == null) {
			if (other.itemID != null)
				return false;
		} else if (!itemID.equals(other.itemID))
			return false;
		return true;
	}
}
