package nozamaFiles;

/**
 * The Sale class specifies the sale of a item on Nozama store
 *
 * @author - Ashley Bickham, Joshua Hunter, Austin Lehman, Tyler Ross
 * @version 1.0 (Apr 27, 2021)
 */
public class Sale {
	private String date;
	private String time;
	
	/**
	 * Get the sale date
	 * 
	 * @return String representing the sale date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Set the sale date
	 * 
	 * @param String representing the sale date
	 * @return void
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Get the sale time
	 * 
	 * @return String representing the sale time
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * Set the sale time
	 * 
	 * @param String representing the sale time
	 * @return void
	 */
	public void setTime(String time) {
		this.time = time;
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
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		Sale other = (Sale) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	
	/**
	 * Sale constructor to be made with user inputted information
	 * 
	 * @param string representing the sale date
	 * @param string representing the sale time
	 */
	public Sale(String date, String time) {
		super();
		this.date = date;
		this.time = time;
	}
	
	@Override
	/**
	 * Displays all information of a Sale object
	 *
	 * @return string filled with the Sale information
	 */
	public String toString() {
		return "Sale [date=" + date + ", time=" + time + "]";
	}
}
