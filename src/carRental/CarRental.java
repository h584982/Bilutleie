package carRental;

import java.time.LocalDateTime;
import java.util.*;

public class CarRental {

    private String name;
    private int phoneNumber;
    private ArrayList<Customer> customers;
    private ArrayList<RentalOffice> offices;
    private Address address;
    private HashMap<Integer, Reservation> reservationsMap;
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

    public int giveNextReservationID() {

        return this.nextReservationID++;

    }

    public CarRental() {

    }

    /**
     * <p>Create and add customer to customers ArrayList</p>
     *
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     */
    public void createCustomer(String firstName, String lastName, Address address, int phoneNumber) {
        this.customers.add(new Customer(firstName, lastName, address, phoneNumber));
    }




    /**
     * <p>Searches for all RentalOffice in offices that match given location</p>
     *
     * @param location
     * @return all matching RentalOffices
     */
    public ArrayList<RentalOffice> findOffices(String location) {
        ArrayList<RentalOffice> locations = new ArrayList<RentalOffice>();
        for (RentalOffice office : this.getOffices()) {
            if (office.getAddress().getCity().toLowerCase().equals(location.toLowerCase())) {
                locations.add(office);
            }
        }
        return locations;
    }

    /**
     * <p>Finds all available cars at given RentalOffice for given time period</p>
     *
     * @param office
     * @param pickUpTime
     * @param deliveryDueDate
     * @return
     */
    public ArrayList<Car> searchQuery(RentalOffice office, LocalDateTime pickUpTime, LocalDateTime deliveryDueDate) {

    	
    	ArrayList<Car> availableCars = office.searchCars(pickUpTime, deliveryDueDate);
    		
        return availableCars;


    }

    /**
     * Let a customer pick up a car and check if the customer got a registered credit card. 
     * Updates values in reservation about the car at the pick up time
     * @param reservationID
     * @return true if successfully picking up a car
     */
    public boolean pickUpCar(int reservationID) {

        Reservation reservation = this.reservationsMap.get(reservationID);
        RentalOffice office = offices.get(reservation.getPickUpOffice());


        if (reservation.getCustomer().getCardNumber() == null) {
            return false;
        }
        office.pickUpEvent(reservation);
        return true;
    }


    /**
     * Returns a car to the same office and to the same date as the reservation and calculates price.
     * @param reservationID
     * @return price for the reservation
     */
    public int dropOffCar(int reservationID)
    {

        Reservation reservation = this.reservationsMap.get(reservationID);
        LocalDateTime dropOffDate = reservation.getDeliveryDueDate();
        RentalOffice office = offices.get(reservation.getPickUpOffice());

        int price = office.dropOffEvent(reservation, dropOffDate, office.getOfficeId());
        return price;
    }

    /**
     * Returns a car to another office, but to the same date as the reservation and calculates price.
     * @param dropOffOffice
     * @param reservationID
     * @return price for the reservation
     */
    public int dropOffCar(int dropOffOffice, int reservationID)
    {

        Reservation reservation = this.reservationsMap.get(reservationID);
        LocalDateTime dropOffDate = reservation.getDeliveryDueDate();
        RentalOffice office = offices.get(reservation.getPickUpOffice());
        int price = office.dropOffEvent(reservation, dropOffDate, dropOffOffice);
        return price;
    }

    /**
 	* Returns a car to the same office, but to another time as the reservation and calculates price.
 	* @param reservationID
 	* @param dropOffDate
 	* @return price for the reservation
 	*/
    public int dropOffCar(int reservationID, LocalDateTime dropOffDate)
    {

        Reservation reservation = this.reservationsMap.get(reservationID);
        RentalOffice office = offices.get(reservation.getPickUpOffice());

        if (reservation.getPickUpDate().isBefore(dropOffDate)) { // test if dropOffDate is before pickUpDate

            if (dropOffDate.isBefore(reservation.getDropOffDate())) { //if dropOffDate is before scheduled delivery, price remain same as before TODO:CHECK IF LEGAL
                int price = office.dropOffEvent(reservation, reservation.getDropOffDate(), office.getOfficeId());
                return price;
            }

            int price = office.dropOffEvent(reservation, dropOffDate, office.getOfficeId());

            return price;
        }
        return -1; //if illegal dropOffDate
    }

    /**
     * Returns a car to another office and to another time than the reservation and calculates price.
     * @param dropOffOffice
     * @param reservationID
     * @param dropOffDate
     * @return price for the reservation
     */
    public int dropOffCar(int dropOffOffice, int reservationID, LocalDateTime dropOffDate)
    {

        Reservation reservation = this.reservationsMap.get(reservationID);
        RentalOffice office = offices.get(reservation.getPickUpOffice());

        if (reservation.getPickUpDate().isBefore(dropOffDate)) { // test if dropOffDate is before pickUpDate

            if (dropOffDate.isBefore(reservation.getDropOffDate())) { //if dropOffDate is before scheduled delivery, price remain same as before TODO:CHECK IF LEGAL
                int price = office.dropOffEvent(reservation, reservation.getDropOffDate(), dropOffOffice);
                return price;
            }

            int price = office.dropOffEvent(reservation, dropOffDate, dropOffOffice);

            return price;
        }
        return -1; //if illegal dropOffDate
    }


    /**
     * Creates a new reservation with the given parameters and puts it in the reservation map
     * @param office
     * @param car
     * @param customer
     * @param pickUpDate
     * @param dropOffDate
     * @return
     */
    public Integer makeReservation(RentalOffice office, Car car, Customer customer, LocalDateTime pickUpDate, LocalDateTime dropOffDate) {
        Reservation reservation = office.createReservation(this.giveNextReservationID(), car, customer,pickUpDate, dropOffDate);
        this.reservationsMap.put(reservation.getReservationId(), reservation);
         return reservation.getReservationId();
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

    public HashMap<Integer, Reservation> getReservationsMap() {
        return this.reservationsMap;
    }


}
