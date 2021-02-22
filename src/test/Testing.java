package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import carRental.Address;
import carRental.Car;
import carRental.CarRental;
import carRental.Customer;
import carRental.RentalOffice;
import carRental.Reservation;
import carRental.Client;

class Testing {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	  private RentalOffice rentalOffice = new RentalOffice();
	  private CarRental carRental=null;
	  
	  private Car tesla;
	  private Customer customer;


	  @BeforeEach
	  public void setUp() {
		  
	  carRental=Client.setupCarRental();

/*

CarRental carRental = new CarRental("Bertel O. Steen", 11223344, null, null, new Address("Bilveien 1", 5053, "Bergen"));

tesla = new Car("EV99999", "Tesla", "Model X", "Hvit",'B', 0);
List<Car> cars = new ArrayList<>();
cars.add(tesla);

RentalOffice rentalOffice = new RentalOffice(1, new Address("Fyllingenveien 3", 5143, "Fyllingsdalen"), 99988877, cars);
customer = new Customer("Jan", "Paulsen", new Address("Kronstadveien 9", 5053, "Kronstad"), 99922233);

 Reservation r = carRental.pickUpCar(rentalOffice, tesla, customer,  .parseDato("01.01.2021"), .parseDato("02.01.2021"), "123");
 System.out.println(r);
	    
	    */
	  }
	
	@Test
	public void createCustomer() {
		CarRental emptyCarRental = new CarRental("Test Rental", 55555555, new ArrayList<Customer>(), new ArrayList<RentalOffice>(),Client.randomAddress());
		emptyCarRental.createCustomer("Jens", "Jensen", Client.randomAddress(), 39399299);
		assertTrue(emptyCarRental.getCustomers().size()==1);
		
	}
	
	
	@Test
	public void pickUpCar() {
		
		 boolean reservation = carRental.pickUpCar(0);

		    assertTrue(rentalOffice.getReservations().contains(reservation));
		    assertTrue(rentalOffice.getReservations() != null);

	}
	
	
	@Test
	public void dropOffCar() {
		
		 Reservation reservation = rentalOffice.getReservations().get(0);
		    
		 carRental.dropOffCar(reservation.getReservationId());

		    assertFalse(rentalOffice.getReservations().contains(reservation));
		    assertTrue(rentalOffice.getReservations() == null);
		    
		
	}
	
	@Test
	public void makeReservation() {

		LocalDateTime pickUpDate=LocalDateTime.of(2021, 2, 25, 14, 30);
		LocalDateTime dropOffDate=pickUpDate.plusDays(5);
		RentalOffice office=carRental.getOffices().get(0);
		office.setReservations(new ArrayList<Reservation>());
		
		Car car=office.searchCars(pickUpDate, dropOffDate).get(0);
		Customer customer=Client.randomCustomer();
		
		Integer reservationID= carRental.makeReservation(office, car, customer,pickUpDate, dropOffDate);
		Reservation reservation=carRental.getReservationsMap().get(reservationID);
		
		
		assertTrue(reservation.getDeliveryDueDate().equals(dropOffDate));
		assertTrue(reservation.getPickUpDate().equals(pickUpDate));
		
	}


	@Test
	public void searchCars() {

		LocalDateTime pickUpDate = LocalDateTime.of(2021, 2, 25, 14, 30);
		LocalDateTime dropOffDate = pickUpDate.plusDays(8);
		RentalOffice office = carRental.getOffices().get(0);
		office.setReservations(new ArrayList<Reservation>());
		office.setCarPark(new ArrayList<Car>());
		office.getCarPark().add(Client.randomCar());


		// Check car is avaialbe
		assertTrue(office.searchCars(pickUpDate, dropOffDate).size()==1);


		Car car1 = office.getCarPark().get(0);
		Customer customer1=Client.randomCustomer();

		Integer reservationID = carRental.makeReservation(office, car1, customer1,pickUpDate, dropOffDate);
		Reservation reservation = carRental.getReservationsMap().get(reservationID);


		// Check car is avaialbe when period before reservation
		assertTrue(office.searchCars(pickUpDate.minusDays(100), dropOffDate.minusDays(100)).size()==1);

		// Check car is avaialbe when period after reservation
		assertTrue(office.searchCars(pickUpDate.plusDays(100), dropOffDate.plusDays(100)).size()==1);


		// Check conflict at exactly same DateTimes
		assertTrue(office.searchCars(pickUpDate, dropOffDate).size()==0);

		// Check conflict when startDate inside reservation
		assertTrue(office.searchCars(pickUpDate.plusDays(2), dropOffDate.plusDays(6)).size()==0);

		// Check conflict when deliveryDueDate inside reservation
		assertTrue(office.searchCars(pickUpDate.minusDays(2), dropOffDate.minusDays(2)).size()==0);

		// Check conflict when wanted period is inside reservation
		assertTrue(office.searchCars(pickUpDate.plusDays(2), dropOffDate.minusDays(2)).size()==0);

		// Check conflict when reservation inside wanted period
		assertTrue(office.searchCars(pickUpDate.minusDays(2), dropOffDate.plusDays(2)).size()==0);




	}

	@Test
	public void createReservation(){
		CarRental emptyCarRental = new CarRental("Test Rental", 55555555, new ArrayList<Customer>(), new ArrayList<RentalOffice>(),Client.randomAddress());
		emptyCarRental.getOffices().add(Client.randomOffice(0));

		RentalOffice office = emptyCarRental.getOffices().get(0);
		office.getCarPark().add(Client.randomCar());
		Reservation reservation = office.createReservation(0,office.getCarPark().get(0),Client.randomCustomer(), LocalDateTime.now(),LocalDateTime.now().plusDays(6));

		assertTrue(office.getReservations().contains(reservation));



	}
	@Test
	public void pickUpEvent(){
	  	RentalOffice office = carRental.getOffices().get(0);

	  	Reservation reservation = office.getReservations().get(0);
		int originalCarMilage = reservation.getCar().getMilage();

		office.pickUpEvent(reservation);

		assertTrue(reservation.getStartMilage() == originalCarMilage && reservation.getStartMilage() < reservation.getCar().getMilage());




	}

	@Test
	public void dropOffEvent(){

		LocalDateTime pickUpDate = LocalDateTime.now();
		LocalDateTime dropOffDate = pickUpDate.plusDays(8);
		RentalOffice office = carRental.getOffices().get(0);
		office.setReservations(new ArrayList<Reservation>());
		office.setCarPark(new ArrayList<Car>());
		office.getCarPark().add(Client.randomCar());


		Car car1 = office.getCarPark().get(0);
		office.getPriceMap().put(car1.getCarClassification(), 1000);

		Customer customer1=Client.randomCustomer();

		Integer reservationID = carRental.makeReservation(office, car1, customer1,pickUpDate, dropOffDate);
		Reservation reservation = carRental.getReservationsMap().get(reservationID);
		carRental.pickUpCar(reservationID);
		assertTrue(carRental.dropOffCar(reservationID)==8000);


	}




}
