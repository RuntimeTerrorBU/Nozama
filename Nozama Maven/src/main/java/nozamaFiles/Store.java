package nozamaFiles;

/**
 * The Store class creates the specifics of the Nozama Store
 *
 * @author - Ashley Bickham, Joshua Hunter, Austin Lehman, Tyler Ross
 * @version 1.0 (Apr 27, 2021)
 */
public class Store {
	private String name;
	private String url;
	
	/**
	 * Store constructor
	 * 
	 * @param string representing the name of the store
	 * @param string representing the url of the store
	 */
	public Store(String n, String u) {
		super();
		this.name = n;
		this.url = u;
	}

	/**
	 * Get the url of the store
	 *
	 * @return String representing the url of the store
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Set the url of the store
	 *
	 * @param String representing the url of the store
	 * @return void
	 */
	public void setUrl(String u) {
		this.url = u;
	}

	/**
	 * Get the name of the store
	 *
	 * @return String representing the name of the store
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the store
	 *
	 * @param String representing the name of the store
	 * @return void
	 */
	public void setName(String n) {
		this.name = n;
	}
 }
