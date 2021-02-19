package carRental;

import java.time.LocalDateTime;

public class Reservation {

	private int reservationId; 
	private Customer customer;
	private int pickUpOffice;
	private int dropOffOffice;
	private LocalDateTime pickUpDate;
	private LocalDateTime deliveryDueDate;
	private Car car;
	private int startMilage;
	private int endMilage;
	private LocalDateTime dropOffDate;
	private boolean isPaid;
	
	
	
	
	public Reservation(int reservationId, Customer customer, int pickUpOffice,  LocalDateTime pickUpDate,
					   LocalDateTime deliveryDueDate, Car car) {
		super();
		this.reservationId = reservationId;
		this.customer = customer;
		this.pickUpOffice = pickUpOffice;
		this.pickUpDate = pickUpDate;
		this.deliveryDueDate = deliveryDueDate;
		this.car = car;
	}
	
	public Reservation() {
		
	}
	
	/**
	 * Setter riktige verdi til startMilage og passer på at en kunde har kredittkort registrert
	 * @param creditCard
	 * @return
	 */
	public void activatePickUp() {
			 
		startMilage = car.getMilage();
	}
	
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getPickUpOffice() {
		return pickUpOffice;
	}
	public void setPickUpOffice(int pickUpOffice) {
		this.pickUpOffice = pickUpOffice;
	}
	public int getDropOffOffice() {
		return dropOffOffice;
	}
	public void setDropOffOffice(int dropOffOffice) {
		this.dropOffOffice = dropOffOffice;
	}
	public LocalDateTime getPickUpDate() {
		return pickUpDate;
	}
	public void setPickUpDate(LocalDateTime pickUpDate) {
		this.pickUpDate = pickUpDate;
	}
	public LocalDateTime getDeliveryDueDate() {
		return deliveryDueDate;
	}
	public void setDeliveryDueDate(LocalDateTime deliveryDueDate) {
		this.deliveryDueDate = deliveryDueDate;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public int getStartMilage() {
		return startMilage;
	}
	public void setStartMilage(int startMilage) {
		this.startMilage = startMilage;
	}
	public int getEndMilage() {
		return endMilage;
	}
	public void setEndMilage(int endMilage) {
		this.endMilage = endMilage;
	}
	public LocalDateTime getDropOffDate() {
		return dropOffDate;
	}
	public void setDropOffDate(LocalDateTime dropOffDate) {
		this.dropOffDate = dropOffDate;
	}
	public boolean isPaid() {
		return isPaid;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
	
	
}
