package carRental;

import java.util.Date;

public class Reservation {

	private int reservationId; 
	private Customer customer;
	private int pickUpOffice;
	private int dropOffOffice;
	private Date startTime;
	private Date endTime;
	private Car car;
	private int startMilage;
	private int endMilage;
	private Date dropOffDate;
	private boolean isPaid;
	
	
	
	
	public Reservation(int reservationId, Customer customer, int pickUpOffice, int dropOffOffice, Date startTime,
			Date endTime, Car car, int startMilage, int endMilage, Date dropOffDate, boolean isPaid) {
		super();
		this.reservationId = reservationId;
		this.customer = customer;
		this.pickUpOffice = pickUpOffice;
		this.dropOffOffice = dropOffOffice;
		this.startTime = startTime;
		this.endTime = endTime;
		this.car = car;
		this.startMilage = startMilage;
		this.endMilage = endMilage;
		this.dropOffDate = dropOffDate;
		this.isPaid = isPaid;
	}
	
	public Reservation() {
		
	}
	
	public boolean activatePickUp(int creditCard) {
		//TODO
		return true;
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	public Date getDropOffDate() {
		return dropOffDate;
	}
	public void setDropOffDate(Date dropOffDate) {
		this.dropOffDate = dropOffDate;
	}
	public boolean isPaid() {
		return isPaid;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
	
	
}
