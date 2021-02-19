package carRental;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
	 * @param pickUpTime
	 * @param delivieryDueDate
	 * @return a list of available cars
	 */
	public ArrayList<Car> searchCars(LocalDateTime pickUpTime, LocalDateTime delivieryDueDate) {
		ArrayList<Car> carSet = new ArrayList<Car>(carPark);
        for (Reservation reservation : reservations) {

            // Only need to check cars still in carSet
            if (carSet.contains(reservation.getCar())) {

                // Check and remove car if queried pickup and drop off time is within the reservation time period
                if (pickUpTime.isAfter(reservation.getPickUpDate()) && pickUpTime.isBefore(reservation.getDeliveryDueDate())) {
                    if (delivieryDueDate.isAfter(reservation.getPickUpDate()) && delivieryDueDate.isBefore(reservation.getDeliveryDueDate())) {
                        carSet.remove(reservation.getCar());
                    }
                }
            }
        }
		
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
	
	// Processs reservation, update reservations and reservationArchive, calculate and return price
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
	 * @return
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
	
