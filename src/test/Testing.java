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
	  private CarRental carRental = new CarRental();
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
		
		Car c1 = new Car("BT54321", "Audi", "E-tron", "Grå", 'B', 0);
		Reservation r1 = new Reservation();
		
		LocalDateTime pickUpDate=LocalDateTime.of(2021, 2, 25, 14, 30);
		LocalDateTime dropOffDate=pickUpDate.plusDays(5);
//		r1.makeReservation("Bertel O. Steen",c1, customer,pickUpDate, dropOffDate);
		
		
		
//		assertFalse(c1.isAvailable());
		
	}

}
