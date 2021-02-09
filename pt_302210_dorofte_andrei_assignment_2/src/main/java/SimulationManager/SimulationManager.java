package SimulationManager;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Strategy.SelectionPolicy;
import fileRead.FileRead;
import model.Scheduler;
import model.Task;

public class SimulationManager implements Runnable, FileRead{
	public int timeLimit; //max processing time
	public int maxProcessingTime;
	public int minProcessingTime;
	public int minArrivalTime;
	public int maxArrivalTime;
	public int numberOfServers;
	public int numberOfClients;
	public int maxTime;
	public double avg =0;
	private File out;
	public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
	public List<Task> generatedTasks;
	
	private Scheduler scheduler;
	
	public SimulationManager(File in, File out) {
		List<String> rez = new ArrayList<String>();
		try {
			rez = readFile(in, "[1-9]{1}[0-9]*");
			this.numberOfClients = Integer.parseInt(rez.get(0));
			this.numberOfServers = Integer.parseInt(rez.get(1));
			this.maxTime = Integer.parseInt(rez.get(2));
			this.minArrivalTime = Integer.parseInt(rez.get(3));
			this.maxArrivalTime = Integer.parseInt(rez.get(4));
			this.minProcessingTime =Integer.parseInt(rez.get(5));
			this.maxProcessingTime= Integer.parseInt(rez.get(6));
			this.avg = 0;
			this.out = out;
			try {
				this.out.createNewFile();
			}
			catch(Exception e) {
				System.out.println("File not created");
				return;
			}
		}
		catch(Exception e) {
			return;
		}
		generateNRandomTasks();
		scheduler = new Scheduler(numberOfServers, numberOfClients);		
	}

	private void generateNRandomTasks() {
		generatedTasks = new ArrayList<Task>(numberOfClients);
		for (int i = 0; i < numberOfClients; i++) {
			Task t = new Task (i, (minArrivalTime + (int) (Math.random() * (maxArrivalTime - minArrivalTime))), minProcessingTime + (int) (Math.random() * (maxProcessingTime - minProcessingTime)));
			generatedTasks.add(t);
		}
		Collections.sort(generatedTasks, new SortByArrival());
	}
	
	private int getMaxWaitTime(int maxWaitTime) {
		if(generatedTasks.isEmpty() && maxWaitTime <= 0)
			maxWaitTime = scheduler.getMaxWait();
		else
			maxWaitTime--;
		return maxWaitTime;
	}
		
	public void run() {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(this.out.toString());
		} catch (Exception e){
			return;
		}
		int currentTime = 0;
		int wait_max = 0;
		int processedClients = 0;
		while(currentTime < maxTime && (!generatedTasks.isEmpty() || wait_max > 0))
		{
			while(!generatedTasks.isEmpty() && generatedTasks.get(0).getArrivalTime() == currentTime)
			{
				scheduler.dispatchTask(generatedTasks.get(0));
				if(currentTime + generatedTasks.get(0).getServiceTime() < maxTime)
				{
					processedClients++;
					avg += generatedTasks.get(0).getServiceTime() + currentTime - generatedTasks.get(0).getArrivalTime();
				}
				generatedTasks.remove(0);
			}
			String rez = getRez(currentTime);
			try {
				fileWriter.write(rez);
			} catch(Exception e) {}
			wait_max = getMaxWaitTime(wait_max);
			currentTime++;
			try {
				Thread.sleep(1000);
			} catch(Exception e) {}
		}
		scheduler.stop();
		try {
			fileWriter.write("Average waiting time: " + avg / processedClients);
		} catch(Exception e) {}
		try {
			fileWriter.close();
		} catch(Exception e) {}
	}
	
	private String getRez(int currentTime) {
		String rez = "\nTime: " + currentTime + "\n";
		rez = rez + "Waiting tasks: ";
		for (Task i: generatedTasks) {
			rez = rez + i.toString();
		}
		rez = rez + "\n" + scheduler.toString();
		return rez;
	}
}
