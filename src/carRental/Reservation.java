package carRental;

import java.time.LocalDateTime;
import java.util.Random;

public class Reservation { 

	private int reservationId; 
	private Customer customer;
	private int pickUpOffice;
	private int dropOffOffice;
	private LocalDateTime pickUpDate;
	private LocalDateTime deliveryDueDate;
	private Car car;
	private int startMileage;
	private int endMileage;
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
	 * Set correct mileage and update the cars mileage by an random amount up to 500 
	 * @param creditCard
	 */
	public void activatePickUp() {
			 
		startMileage = car.getMilage();
		
		Random random = new Random();
		
		car.setMilage(car.getMilage() + 1+random.nextInt(499));
	}
	/**
	 * Updates information about mileage, drop off date and drop off office 
	 * @param dropOffDate
	 * @param dropOffOffice
	 */
	public void returnCar(LocalDateTime dropOffDate, int dropOffOffice) {
		
		setEndMilage(car.getMilage());
		setDropOffOffice(dropOffOffice);
		setDropOffDate(dropOffDate);
		
		
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
		return startMileage;
	}
	public void setStartMilage(int startMilage) {
		this.startMileage = startMilage;
	}
	public int getEndMilage() {
		return endMileage;
	}
	public void setEndMilage(int endMilage) {
		this.endMileage = endMilage;
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

	@Override
	public String toString() {
		return "Reservation{" +
				"reservationId=" + reservationId +
				", customer=" + customer +
				", pickUpOffice=" + pickUpOffice +
				", dropOffOffice=" + dropOffOffice +
				", pickUpDate=" + pickUpDate +
				", deliveryDueDate=" + deliveryDueDate +
				", car=" + car +
				", startMilage=" + startMileage +
				", endMilage=" + endMileage +
				", dropOffDate=" + dropOffDate +
				", isPaid=" + isPaid +
				'}';
	}
}
