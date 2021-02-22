package carRental;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

public class RentalOffice {
	
	
	private int officeId;
	private Address address;
	private int phoneNumber;
	private ArrayList<Car> carPark;
	private ArrayList<Reservation> reservations;
	private ArrayList<Reservation> reservationArchive;
	private HashMap<Character, Integer> priceMap;
	
	public RentalOffice(int officeId, Address address, int phoneNumber, ArrayList<Car> carPark,
			ArrayList<Reservation> reservations, ArrayList<Reservation> reservationArchive, HashMap<Character, Integer> priceMap) {
		super();
		this.officeId = officeId;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.carPark = carPark;
		this.reservations = reservations;
		this.reservationArchive = reservationArchive;
		this.priceMap = priceMap;
	}
	
	public RentalOffice() {
		
	}
	
	/**
	 * Search through an office car park and remove cars from the list that is reserved for the given time
	 * @param pickUpDate
	 * @param deliveryDueDate
	 * @return a list of available cars
	 */
	public ArrayList<Car> searchCars(LocalDateTime pickUpDate, LocalDateTime deliveryDueDate) {
		ArrayList<Car> carSet = new ArrayList<Car>(carPark);
		System.out.println(carSet.size());
        for (Reservation reservation : reservations) {

            // Only need to check cars still in carSet
            if (carSet.contains(reservation.getCar())) {

				if (pickUpDate.isAfter(reservation.getPickUpDate()) && pickUpDate.isBefore(reservation.getDeliveryDueDate())) { // check if pickUpTime is inside already reserved date
					carSet.remove(reservation.getCar());
					System.out.println("conflict pickup\n new pickuptime" + pickUpDate.toString()
							+ "\n reservation pickuptime " + reservation.getPickUpDate().toString()
							+ "\n reservation dropoff" + reservation.getDeliveryDueDate().toString()
							+ "\n" + reservation.getCar().toString());
					continue;
				}
				if (deliveryDueDate.isAfter(reservation.getPickUpDate()) && deliveryDueDate.isBefore(reservation.getDeliveryDueDate())) { // check if deliveryDate is inside already reserved date
					carSet.remove(reservation.getCar());
					System.out.println("conflict delivery\n new deliverytime" + deliveryDueDate.toString()
							+ "\n reservation pickuptime " + reservation.getPickUpDate().toString()
							+ "\n reservation dropoff" + reservation.getDeliveryDueDate().toString()
							+ "\n" + reservation.getCar().toString());
					continue;
				}
				if (pickUpDate.isBefore(reservation.getPickUpDate()) && deliveryDueDate.isAfter(reservation.getDeliveryDueDate())) { // check if already reserved date is inside planned reservation
					carSet.remove(reservation.getCar());
					System.out.println("conflict long reservation\n new deliverytime" + deliveryDueDate.toString()
							+ "\n new pickuptime" + pickUpDate.toString()
							+ "\n reservation pickuptime " + reservation.getPickUpDate().toString()
							+ "\n reservation dropoff" + reservation.getDeliveryDueDate().toString()
							+ "\n" + reservation.getCar().toString());
					continue;
				}
				/*
				*test if planned reservation is equal to delivery date or pick up date
				* doesn't work

				if (deliveryDueDate.isEqual(reservation.getPickUpDate()) || pickUpDate.isEqual(reservation.getPickUpDate())) { // check if pickUpTime or deliveryDueDate is equal to already reserved pickUpDate
					carSet.remove(reservation.getCar());
					System.out.println("conflict  pickuptime\n new pickuptime" + pickUpDate.toString()
							+ "\n reservation pickuptime " + reservation.getPickUpDate().toString()
							+ "\n reservation dropoff" + reservation.getDeliveryDueDate().toString()
							+ "\n" + reservation.getCar().toString());
					continue;

				}
				if (deliveryDueDate.isEqual(reservation.getDeliveryDueDate()) || pickUpDate.isEqual(reservation.getDeliveryDueDate())) { // check if pickUpTime or deliveryDueDate is equal to already reserved pickUpDate
					carSet.remove(reservation.getCar());
					System.out.println("conflict delivery\n new deliverytime" + deliveryDueDate.toString()
							+ "\n reservation pickuptime " + reservation.getPickUpDate().toString()
							+ "\n reservation dropoff" + reservation.getDeliveryDueDate().toString()
							+ "\n" + reservation.getCar().toString());
					continue;
				}
				*/
            }
        }

		System.out.println(carSet.size());
		return carSet;
	}
	
	/**
	 * Create a reservation and add it to reservations-array
	 * @param nextReservationID
	 * @param car
	 * @param customer
	 * @param pickupDate
	 * @param dropOfDate
	 * @return the newly created reservation
	 */
	public Reservation createReservation(int nextReservationID, Car car, Customer customer, LocalDateTime pickupDate, LocalDateTime dropOfDate) {
		Reservation reservation = new Reservation(nextReservationID, customer, officeId, pickupDate, dropOfDate, car);
		reservations.add(reservation);
		return reservation;
	}
	
	/**
	 * Deliver the car out and register the mileage
	 * @param reservation
	 */
	public void pickUpEvent(Reservation reservation) { 
		
		reservation.activatePickUp();
	}
	
	/**
	 * Processes a reservation by updating the given reservation and moving the reservation to archive
	 * @param reservation
	 * @param dropOffDate
	 * @param dropOffOfficeId
	 * @return calculated price for the reservation
	 */
	public int dropOffEvent(Reservation reservation, LocalDateTime dropOffDate, int dropOffOfficeId){
		
		//Checks if the car is returned to the same office, if not 1 day is added for transportation
		if(officeId != dropOffOfficeId) {
			dropOffDate = dropOffDate.plusDays(1);
		}
		
		// Update values in reservation after returning of a car
		reservation.returnCar(dropOffDate, dropOffOfficeId);
		
		// Move the reservation from active reservations to archive
		reservations.remove(reservation);
		reservationArchive.add(reservation);
		
		// Calculate the price and returns it
		return calculatePrice(reservation);
	}
	
	/**
	 * Calculates the price based on days rented for a given reservation
	 * @param reservation
	 * @return calculated price
	 */
	private int calculatePrice(Reservation reservation) {
		// Calculates the difference in time from pick up to delivery in hours
		Long deltaTime = reservation.getPickUpDate().until(reservation.getDropOffDate(), ChronoUnit.HOURS);
		
		// Calculates days rounded up
		int deltaDays = (int) Math.ceil(deltaTime/24);

		
		int priceEachDay = priceMap.get(reservation.getCar().getCarClassification());
		
		return deltaDays * priceEachDay;
	}
	
	public int getOfficeId() {
		return officeId;
	}
	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
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
	public HashMap<Character, Integer> getPriceMap() {
		return priceMap;
	}
	public void setPriceMap(HashMap<Character, Integer> priceMap) {
		this.priceMap = priceMap;
	}
}
	
