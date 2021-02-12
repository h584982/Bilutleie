package carRental;

public class Car {

	private String registrationNumber;
	private String brand;
	private String model;
	private String color;
	private char rentalGroup;
	private boolean available;

	public Car(String registrationNumber, String brand, String model, String color, char rentalGroup, boolean available) {
		this.registrationNumber = registrationNumber;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.rentalGroup = rentalGroup;
		this.available = available;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public char getRentalGroup() {
		return rentalGroup;
	}

	public void setRentalGroup(char rentalGroup) {
		this.rentalGroup = rentalGroup;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

}