package test;

import static org.junit.jupiter.api.Assertions.*;

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
		  
	   CarRental carRental = new CarRental("Bertel O. Steen", 11223344, null, null, new Address("Bilveien 1", 5053, "Bergen"));
	    
	   tesla = new Car("EV99999", "Tesla", "Model X", "Hvit",'B', 0);
	   List<Car> cars = new ArrayList<>();
	   cars.add(tesla);

	   rentalOffice = new RentalOffice(1, new Address("Fyllingenveien 3", 5143, "Fyllingsdalen"), 99988877, cars);
	   customer = new Customer("Jan", "Paulsen", new Address("Kronstadveien 9", 5053, "Kronstad"), 99922233);

	    Reservation r = carRental.pickUpCar(rentalOffice, tesla, customer, .parseDato("01.01.2021"), .parseDato("02.01.2021"), "123");
	    System.out.println(r);
	    
	  }
	
	@Test
	public void createCustomer() {
		
		
	}
	
	
	@Test
	public void pickUpCar() {
		
		 Reservation reservation = carRental.pickUpCar(location, reservationID);

		    assertTrue(rentalOffice.getReservations().contains(reservation));
		    assertTrue(tesla.getReservations().contains(reservation));
		    assertTrue(rentalOffice.getReservations() != null);

	}
	
	
	@Test
	public void dropOffCar() {
		
		 Reservation reservation = rentalOffice.getReservations().get(0);
		    
		 carRental.dropOffCar(reservation.getReservationId(), rentalOffice);

		    assertFalse(rentalOffice.getReservations().contains(reservation));
		    assertFalse(tesla.getReservations().contains(reservation));
		    assertTrue(rentalOffice.getReservations() == null);
		    
		
		
	}
	
	@Test
	public void makeReservation() {
		
		
	}

}
