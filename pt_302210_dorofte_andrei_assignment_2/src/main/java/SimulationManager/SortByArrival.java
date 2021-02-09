package SimulationManager;

import java.util.Comparator;

import model.Task;

class SortByArrival implements Comparator<Task> {
    public int compare(Task x, Task y) { 
        return x.getArrivalTime() - y.getArrivalTime(); 
    }
} 
