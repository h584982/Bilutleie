package carRental;

public class Address {
	
	private String street;
	private int zipCode;
	private String City;
	
	public Address(String street, int zipCode, String city) {
		super();
		this.street = street;
		this.zipCode = zipCode;
		City = city;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	

}
