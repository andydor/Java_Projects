package model;

import java.util.ArrayList;
import java.util.List;
import Strategy.ConcreteStrategyQueue;
import Strategy.ConcreteStrategyTime;
import Strategy.SelectionPolicy;

public class Scheduler {
	private List<Server> servers;
	private int maxNoServers;
	private int maxTasksPerServer;
	private List<Thread> threads;
	
	public Scheduler(int nrServers, int nrTasks) {
		this.servers = new ArrayList<Server>(nrServers);
		this.threads = new ArrayList<Thread>(nrServers);
		this.maxNoServers = nrServers;
		this.maxTasksPerServer = nrTasks;
		for (int i = 0; i < nrServers; i++) {
			servers.add(new Server(i));
			threads.add(new Thread(servers.get(i)));
			threads.get(i).start();
		}
	}
	
	public int getMaxNoServers() {
		return maxNoServers;
	}
	
	public void setMaxNoServers(int maxNoServers) {
		this.maxNoServers = maxNoServers;
	}
	
	public int getMaxTasks() {
		return maxTasksPerServer;
	}
	
	public void setMaxTasks(int maxTasksPerServer) {
		this.maxTasksPerServer = maxTasksPerServer;
	}
	
	public void changeStrategy(SelectionPolicy policy) {
		if(policy == SelectionPolicy.SHORTEST_QUEUE)
			new ConcreteStrategyQueue();
		else if(policy == SelectionPolicy.SHORTEST_TIME)
			new ConcreteStrategyTime();
	}
	
	private int minTimeId() {
		int min = Integer.MAX_VALUE;
		int id = 0;
		for(Server i : servers) {
			if(i.getWaitingPeriod() < min) {
				min = i.getWaitingPeriod();
				id = i.getServerId();
			}
			if(min == 0)
				break;
		}
		return id;
	}
	
	public void dispatchTask(Task t) {
		servers.get(minTimeId()).addTask(t);
		if(servers.get(minTimeId()).isActive() != true) {
			servers.get(minTimeId()).setActive(true);
			threads.set(minTimeId(), new Thread(servers.get(minTimeId())));
			threads.get(minTimeId()).start();
		}
	}
	
	public void stop() {
		for(Server i : servers) {
			i.setActive(false);
		}
	}
	
	public int getMaxWait() {
		int max = Integer.MIN_VALUE;
		for(Server i : servers) {
			if (i.getWaitingPeriod() > max)
				max = i.getWaitingPeriod();
		}
		return max;
	}
	
	public List<Server> getServers() {
		return servers;
	}
	
	public String toString() {
		String rez = "";
		for (Server i: servers) {
			rez += "Server " + i.getServerId() + ": " + i.toString() + "\n";
		}
		return rez;
	}
	
}