package Strategy;

import java.util.List;

import model.Server;
import model.Task;

public class ConcreteStrategyQueue implements Strategy {
	public void addTask (List<Server> servers, Task t) {
		int minTime = Integer.MAX_VALUE;
		int id = 0;
		for(Server i : servers)
			if(i.getWaitingTasks() < minTime) {
				minTime = i.getWaitingTasks();
				id = i.getServerId();
			}
		for(Server i : servers) {
			if(i.getServerId() == id) {
				i.setActive(true);
				i.addTask(t);
			}
		}
	}

}

