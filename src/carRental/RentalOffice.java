package carRental;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RentalOffice {
	
	
	private int officeId;
	private Address adress;
	private int phoneNumber;
	private ArrayList<Car> carPark;
	private ArrayList<Reservation> reservations;
	private ArrayList<Reservation> reservationArchive;
	private HashMap priceMap;
	
	public RentalOffice(int officeId, Address adress, int phoneNumber, ArrayList<Car> carPark,
			ArrayList<Reservation> reservations, ArrayList<Reservation> reservationArchive, HashMap priceMap) {
		super();
		this.officeId = officeId;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.carPark = carPark;
		this.reservations = reservations;
		this.reservationArchive = reservationArchive;
		this.priceMap = priceMap;
	}
	
	public RentalOffice() {
		
	}
	
	public Car[] searchCars(Date time) {
		// TODO
		return null;
	}
	
	public boolean createReservation(Integer reservationID, Car car, Customer customer, Date pickupDate, Date deliveryDate) {
		// TODO
		return true;
	}
	
	public int calculatePrice() {
		// TODO
		return 0;
	}
	
	public int getOfficeId() {
		return officeId;
	}
	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}
	public Address getAdress() {
		return adress;
	}
	public void setAdress(Address adress) {
		this.adress = adress;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public ArrayList<Car> getCarPark() {
		return carPark;
	}
	public void setCarPark(ArrayList<Car> carPark) {
		this.carPark = carPark;
	}
	public ArrayList<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}
	public ArrayList<Reservation> getReservationArchive() {
		return reservationArchive;
	}
	public void setReservationArchive(ArrayList<Reservation> reservationArchive) {
		this.reservationArchive = reservationArchive;
	}
	public HashMap getPriceMap() {
		return priceMap;
	}
	public void setPriceMap(HashMap priceMap) {
		this.priceMap = priceMap;
	}
	
	
/*
	Scanner scanner = new Scanner(System.in);

	List<Car> cars = Arrays.asList(
			new Car("EV99999", "Tesla", "Model S", "Rød", 'B', true),
			new Car("EV99998", "Tesla", "Model X", "Hvit", 'A', true),
			new Car("EV99997", "BMW", "i8", "Hvit", 'B', true),
			new Car("EV99996", "BMW", "i3", "Blå", 'C', true),
			new Car("EV99995", "Porsche", "Taycan", "Svart", 'C', true),
			new Car("EV99994", "Porsche", "Taycan Turbo", "Blå", 'C', true)
			);

	public void chooseCar() {
		String regex = "^[A-ZÆØÅa-zæøå ]+$";
		System.out.println("Velkommen til bilutleie. Skriv inn hvilket bilmerke du vil leie:");

		// sjekke alle bilmerker og skriver de ut
		System.out.println(cars.stream().map(Car::getBrand).distinct().collect(Collectors.joining("\n")));

		String carBrand = scanner.nextLine();

		// while(!cars.stream()
		// .map(Car::getBrand)
		// .distinct()
		// .collect(Collectors.joining("\n")).contains(carBrand)){
		// System.out.println("Skriv inn et bilmerke som er tilgjengelig.");
		// carBrand=scanner.nextLine();
		// }

		System.out.println("Skriv inn hvilken bilmodell du vil leie:");

		// sjekke alle modeller til bilmerket og skrive de ut
		System.out.println(cars.stream().filter(a -> a.getBrand().equals(carBrand)).map(Car::getModel)
				.collect(Collectors.joining("\n")));
		String carModel = scanner.nextLine();

		System.out.println("Skriv inn hvilken farge på bilen du vil leie:");

		// sjekke alle farger til valgt modell
		System.out.println(cars.stream().filter(a -> a.getBrand().equals(carBrand) && a.getModel().equals(carModel))
				.map(Car::getColor).collect(Collectors.joining("\n")));
		String carColor = scanner.nextLine();

		System.out.println("Leier ut bil ");

	}
	*/
}
	
