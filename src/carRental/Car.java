package carRental;

public class Car {

	private String registrationId;
	private String carBrand;
	private String carModel;
	private String color;
	private char carClassification;
	private int milage;


	public Car(String registrationId, String carBrand, String carModel, String color, char carClassification,
			int milage) {
		super();
		this.registrationId = registrationId;
		this.carBrand = carBrand;
		this.carModel = carModel;
		this.color = color;
		this.carClassification = carClassification;
		this.milage = milage;
	}


	public String getRegistrationId() {
		return registrationId;
	}


	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}


	public String getCarBrand() {
		return carBrand;
	}


	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}


	public String getCarModel() {
		return carModel;
	}


	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public char getCarClassification() {
		return carClassification;
	}


	public void setCarClassification(char carClassification) {
		this.carClassification = carClassification;
	}


	public int getMilage() {
		return milage;
	}


	public void setMilage(int milage) {
		this.milage = milage;
	}


	@Override
	public String toString() {
		return 	"Make:'" + carBrand + '\'' +
				", Model:'" + carModel + '\'' +
				", Color:'" + color + '\'' +
				", Car classification:'" + carClassification;
	}
}