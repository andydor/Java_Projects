package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server implements Runnable {
	private BlockingQueue<Task> tasks;
	private int waitingPeriod;
	private int waitingTasks=0;
	private boolean active = true;
	private int id;
	private int tasks_done = 0;
	private int waitingTime = 0;
	
	public Server(int id) {
		this.id = id;
		this.tasks = new LinkedBlockingQueue<Task>();
		this.waitingPeriod=0;	
	}
	
	public void addTask(Task t) {
		tasks.add(t);
		waitingPeriod += t.getServiceTime();
	}
	
	public int getServerId() {
		return id;
	}
	public int getWaitingTasks() {
		return waitingTasks;
	}
	
	public int getWaitingPeriod() {
		return waitingPeriod;
	}

	public void setWaitingPeriod(int waitingPeriod) {
		this.waitingPeriod = waitingPeriod;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean x) {
		this.active = x;
	}
	
	public double getAverageTime() {
		return waitingTime / tasks_done;
	}
	
	public int getTasks_Done() {
		return tasks_done;
	}
	
	public int getWaitingTime() {
		return waitingTime;
	}

	public BlockingQueue<Task> getTasks() {
		return tasks;	
	}

	public void run() {
		while(this.active) {
			while(tasks.peek() != null) {
				try {
					int st = tasks.peek().getServiceTime();
					Thread.sleep(1000);
					waitingPeriod--;
					st = st - 1;
					tasks.peek().setServiceTime(st);
					if(st == 0) {
						tasks.peek().setServiceTime(0);
						tasks.poll();
					}
				} catch(Exception e) {}
			}
			setActive(false);
		}
	}

	public String toString() {
		String rez;
		if(getWaitingPeriod() == 0 || tasks.peek() == null || tasks.peek().getServiceTime() == 0)
			rez = "closed";
		else
			rez = tasks.toString();
		return rez;
	}
}