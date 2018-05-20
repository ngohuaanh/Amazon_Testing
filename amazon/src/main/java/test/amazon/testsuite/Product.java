package test.amazon.testsuite;

public class Product {
	
	public Product(String id) {
		this.id = id;
	}
	
	public Product(String id, String name, Price price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	private String name;
	private Price price;
}
