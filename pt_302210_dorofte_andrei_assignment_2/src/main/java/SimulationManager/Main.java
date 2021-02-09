package SimulationManager;

import java.io.File;

public class Main {
	public static void main(String args[]) {
		try {
	        SimulationManager sim = new SimulationManager(new File(args[0]), new File(args[1]));
	        Thread t = new Thread(sim);
	        t.start();
	        try {
	        	t.join();
	        } catch(Exception e) {}
		} catch(Exception e){}
	}
}