package model;

public class Task {
	private int id;
	private int arrivalTime;
	private int serviceTime;
	private int exitTime;
	
	public Task(int id, int arrivalTime, int serviceTime) {
		this.id=id;
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public int getServiceTime() {
		return serviceTime;
	}
	
	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
	
	public int getTaskId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setexitTime(int exitTime){
		this.exitTime = exitTime;
	}
	
	public int getExitTime() {
		return exitTime;
	}
	
	public String toString() {
		return "(" + id + "," + arrivalTime + "," + serviceTime + ")";
	}
}
