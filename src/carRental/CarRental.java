package carRental;

import java.time.LocalDateTime;
import java.util.*;

public class CarRental {

	private String name;
	private int phoneNumber;
	private ArrayList<Customer> customers;
	private ArrayList<RentalOffice> offices;
	private Address address;
	private HashMap<Integer,Reservation> reservationsMap;
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
		this.reservationsMap = new HashMap<>();
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
		ArrayList<RentalOffice> locations = new ArrayList<RentalOffice>();
		for(RentalOffice office : this.getOffices()){
			if (office.getAddress().getCity().toLowerCase().equals(location.toLowerCase())) {
				locations.add(office);
			}
		}
		return locations;
	}
	
	private ArrayList<Car> searchQuery(RentalOffice office, LocalDateTime pickUpTime, LocalDateTime delivieryDueDate){


		Set<Car> carSet = new HashSet<>(office.getCarPark());
		for(Reservation reservation : office.getReservations()){
			if (pickUpTime.isAfter(reservation.getPickUpDate()) && pickUpTime.isBefore(reservation.getDeliveryDueDate()) ){
				if(delivieryDueDate.isAfter(reservation.getPickUpDate()) && delivieryDueDate.isBefore(reservation.getDeliveryDueDate())){
					carSet.remove(reservation.getCar());

				}
			}


		}

		return  new ArrayList<>(carSet);

	}
	
	private void presentCars() {
		//TODO
	}
	
	public boolean pickUpCar(String location, int reservationID) {

		Reservation reservation = this.reservationsMap.get(reservationID);
		if ((Integer)reservation.getCustomer().getCardNumber() == null){
			return false;
		}


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
