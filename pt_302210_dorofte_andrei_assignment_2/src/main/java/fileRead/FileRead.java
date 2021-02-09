package fileRead;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public interface FileRead {
	public default ArrayList<String> readFile(File in, String x) throws IllegalArgumentException {
		ArrayList<String> rez = new ArrayList<String>();
		try {
			Scanner scanner;
			try {
				scanner = new Scanner(in);
			} catch(Exception e) {
				throw new IllegalArgumentException("File does not exist!");
			}
			while(scanner.hasNextLine()) {
				String nextLn = scanner.nextLine();
				String[] split = nextLn.split(",");
				for(String s : split) {
					if(s.matches("[1-9]{1}[0-9]*"))
						rez.add(s);
					else
						throw new IllegalArgumentException("The format is not ok");
				}
			}
			scanner.close();
		}
		catch(IllegalArgumentException e) {
			throw e;
		}
		return rez;
	}
}
