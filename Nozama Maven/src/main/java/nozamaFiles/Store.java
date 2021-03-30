package nozamaFiles;

public class Store {
	private String name;
	private String url;
	
	public Store(String n, String u) {
		super();
		this.name = n;
		this.url = u;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String u) {
		this.url = u;
	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		this.name = n;
	}
 }
