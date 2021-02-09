package start;

import presentation.Controller;

/**
 * clasa Main
 */
public class Start {
	/**
	 * Metoda main, se apeleaza metoda read din controller, cu calea spre fisier ca parametru
	 * @param args
	 */
	public static void main(String[] args) {
		Controller.read(args[0]);
		//"D:/PT2020/pt_302210_dorofte_andrei_assignment_3/in.txt"
	}
}