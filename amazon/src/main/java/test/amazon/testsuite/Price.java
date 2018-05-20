package test.amazon.testsuite;

public class Price {
	private String currency;
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPriceWhole() {
		return priceWhole;
	}
	public void setPriceWhole(String priceWhole) {
		this.priceWhole = priceWhole;
	}
	public String getPriceFractional() {
		return priceFractional;
	}
	public void setPriceFractional(String priceFractional) {
		this.priceFractional = priceFractional;
	}
	private String priceWhole;
	private String priceFractional;
	public Price(String currency, String priceWhole, String priceFractional) {
		this.currency = currency;
		this.priceWhole = priceWhole;
		this.priceFractional = priceFractional;
	}

}
