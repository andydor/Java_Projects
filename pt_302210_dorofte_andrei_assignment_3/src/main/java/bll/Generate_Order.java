package bll;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import model.Order;

/**
 * Clasa de generare pdf in cazul unei comenzi
 */
public class Generate_Order {
	/**
	 * variabile pentru generare comanzi diferite
	 */
	private static int i = 0;
	private static int i1 = 0;
	/**
	 * Generare pdf pentru comanda valida
	 * @param order
	 * @param price
	 * @param id
	 */
	public  static void generate_Order(Order order, double price, int id) {
		/**
		 * cate un string pe cate un paragraf pentru a evita suprapunerea scrisului
		 */
		String out = "Order number: " + id;
		String out1 = "Name: " + order.getName();
		String out2 = "Product: " + order.getProduct();
		String out3 = "Quantity: " + order.getQuantity();
		String out4 = "Price per item: " + price;
		String out5 = "Total price: " + order.getFinal_price();
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("Order_" + i++ + ".pdf"));
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (DocumentException e) {
			e.getMessage();
		}
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		try {
			document.add(new Paragraph(new Chunk(out, font)));
			document.add(new Paragraph(new Chunk(out1, font)));
			document.add(new Paragraph(new Chunk(out2, font)));
			document.add(new Paragraph(new Chunk(out3, font)));
			document.add(new Paragraph(new Chunk(out4, font)));
			document.add(new Paragraph(new Chunk(out5, font)));
		} catch (DocumentException e) {
			e.getMessage();
		}
		document.close();
	}

	/**
	 * Generare PDF pentru stoc insuficient, comanda invalida
	 */
	public  static void generate_not_Order(Order order) {
		String out1 = "Name: " + order.getName();
		String out2 = "Product: " + order.getProduct();
		String out3 = "Quantity: " + order.getQuantity();
		String out4 = "Insufficient stock! Order not created!";
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("Order_Failed_" + i1++ + ".pdf"));
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (DocumentException e) {
			e.getMessage();
		}
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		try {
			document.add(new Paragraph(new Chunk(out1, font)));
			document.add(new Paragraph(new Chunk(out2, font)));
			document.add(new Paragraph(new Chunk(out3, font)));
			document.add(new Paragraph(new Chunk(out4, font)));
		} catch (DocumentException e) {
			e.getMessage();
		}
		document.close();
	}
}
