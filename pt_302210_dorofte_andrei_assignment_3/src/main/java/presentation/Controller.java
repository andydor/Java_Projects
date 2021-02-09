package presentation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import bll.*;
import model.Client;
import model.Order;
import model.Product;

/**
 * clasa ce implementeaza citirea din fisier cu BufferedReader
 */
public class Controller {
	/**
	 * metoda de citire prin split la fiecare linie si se apeleaza metoda aferenta
	 * @param path
	 */
	public static void read(String path) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			while (line != null) {
				//System.out.println(line);
				if(line.contains("Insert client") || line.contains("Insert Client")) {
					String[] nume = line.split(":");
					String[] nume2 = nume[nume.length - 1].split(",");
					String nume_final = nume2[nume2.length - 2].split(" ")[1] + " " + nume2[nume2.length - 2].split(" ")[2];
					String localitate = nume2[nume2.length - 1].split(" ")[1];
					ClientBLL.insert(new Client(nume_final, localitate));
				}
				/**
				 * stergere cu sau fara localitate
				 */
				if(line.contains("Delete client") || line.contains("Delete Client")) {
					String[] nume = line.split(":");
					if(nume[1].split(",").length == 2) {
						String nume_final1[] = nume[1].split(",")[0].split(" ");
						String nume_final = nume_final1[1] + " " + nume_final1[2];
						ClientBLL.delete(nume_final);
					}
					if(nume[1].split(",").length == 1) {
						String nume_final1[] = nume[1].split(" ");
						String nume_final = nume_final1[1] + " " + nume_final1[2];
						ClientBLL.delete(nume_final);
					}
				}
				if(line.contains("Insert product") || line.contains("Insert Product")) {
					String[] nume = line.split(":");
					String nume_final = nume[1].split(",")[0].split(" ")[1];
					int quantity = Integer.parseInt(nume[1].split(",")[1].split(" ")[1]);
					double price = Double.parseDouble(nume[1].split(",")[2].split(" ")[1]);
					ProductBLL.insert(new Product(nume_final, price, quantity));
				}
				if(line.contains("Delete product") || line.contains("Delete Product")) {
					String[] nume = line.split(":");
					String nume_final = nume[1].split(" ")[1];
					ProductBLL.delete(nume_final);
				}
				if(line.contains("Order")) {
					String[] nume = line.split(":");
					String[] nume2 = nume[1].split(",");
					String nume_final = nume2[0].split(" ")[1] + " " + nume2[0].split(" ")[2];
					String product = nume2[1].split(" ")[1];
					int quantity = Integer.parseInt(nume2[2].split(" ")[1]);
					OrderBLL.insert(new Order(nume_final, product, quantity));
				}
				if(line.contains("Report")) {
					String[] nume = line.split(" ");
					ReportBLL.generateClient(nume[1]);
					ReportBLL.generateProduct(nume[1]);
					ReportBLL.generateOrder(nume[1]);
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}