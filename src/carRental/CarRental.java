package carRental;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class CarRental {

	private String name;
	private int phoneNumber;
	private ArrayList<Customer> customers;
	private ArrayList<RentalOffice> offices;
	private Address address;
	private int nextReservationID;
	
	public CarRental(String name, int phoneNumber, ArrayList<Customer> customers, ArrayList<RentalOffice> offices,
			Address address) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.customers = customers;
		this.offices = offices;
		this.address = address;
		this.nextReservationID = 0;
	}

	public int giveNextReservationID(){

		return this.nextReservationID++;

	}
	
	public CarRental() {
		
	}
	
	public void createCustomer(String firstName, String lastName, Address address, int phoneNumber) {
		this.customers.add(new Customer(firstName,lastName,address,phoneNumber));
	}
	
	public void createRentalOffice(Address address, int phoneNumber) {
		// TODO
	}
	
	public void session() {
		//TODO
	}
	
	private ArrayList<RentalOffice> findOffices(String location){
		// TODO
		return null;
	}
	
	private ArrayList<Car> searchQuery(RentalOffice office, LocalDateTime time){
		// TODO
		return null;
	}
	
	private void presentCars() {
		//TODO
	}
	
	public boolean pickUpCar(String location, int reservationId) {
		// TODO
		return true;
	}
	
	public boolean dropOffCar(String location, int reservationId) {
		// TODO
		return true;
	}
	
	private boolean makeReservation(RentalOffice office, Car car, Customer customer, LocalDateTime pickUpDate, LocalDateTime dropOffDate) {
		// TODO
		return true;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	public ArrayList<RentalOffice> getOffices() {
		return offices;
	}
	public void setOffices(ArrayList<RentalOffice> offices) {
		this.offices = offices;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
