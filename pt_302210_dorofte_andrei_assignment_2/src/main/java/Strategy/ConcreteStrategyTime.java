package Strategy;

import java.util.List;

import model.Server;
import model.Task;

public class ConcreteStrategyTime implements Strategy {
	public void addTask(List<Server> servers, Task t) {
		double minTime = Integer.MAX_VALUE;
		int id = 0;;
		for(Server i : servers) {
			if(i.getWaitingPeriod() < minTime)
				{minTime = i.getWaitingPeriod();
				id = i.getServerId();
				}
		}
		for(Server i : servers) {
			if(i.getServerId() == id)
				i.addTask(t);
		}
	}
}
