package bll;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * metoda de generare raport pentru tabelele din baza de date
 */
public class ReportBLL {
    /**
     * se apeleaza metoda din productdao care exectuta un query select*
     * @param a
     */
    public static void generateProduct(String a) {
        ProductDAO.select(a);
    }

    /**
     * variabile pentru generare rapoarte diferite
     */
    private static int i = 0;
    private static int i1 = 0;
    private static int i2 = 0;
    /**
     * metoda din productdao apeleaza metoda asta cu rezultatul
     * @param a
     * @param r1
     */
    public static void generatePDFProduct(String a, ResultSet r1) {
        if(a.contentEquals("product")) {
            try {
                Document document = new Document();
                PdfPTable tablesup= new PdfPTable(4);
                PdfPCell cell = new PdfPCell(new Paragraph("Products Information"));
                cell.setColspan(8);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.CYAN);
                tablesup.addCell(cell);
                tablesup.addCell("Product ID");
                tablesup.addCell("Product Name");
                tablesup.addCell("Product Price");
                tablesup.addCell("Product Quantity");
                try {
                    PdfWriter.getInstance(document, new FileOutputStream("Products_Report_" + i++ + ".pdf"));
                } catch (FileNotFoundException e) {
                    e.getMessage();
                } catch (DocumentException e) {
                    e.getMessage();
                }
                document.open();
                while(r1.next()) {
                    String v1 = r1.getString("id");
                    String v2 = r1.getString("name");
                    String v3 = r1.getString("price");
                    String v4 = r1.getString("quantity");
                    tablesup.addCell(v1);
                    tablesup.addCell(v2);
                    tablesup.addCell(v3);
                    tablesup.addCell(v4);
                }
                try {
                    document.add(tablesup);
                } catch (DocumentException e) {
                    e.getMessage();
                }
                document.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
    }

    /**
     * la fel pt order se apeleaza metoda care executa query din dao
     * @param a
     */
    public static void generateOrder(String a) {
        OrderDAO.select(a);
    }

    /**
     * metoda din dao apeleaza metoda asta cu rezultatul
     * @param a
     * @param r1
     */
    public static void generatePDFOrder(String a, ResultSet r1) {
        if(a.contentEquals("order")) {
            Document document = new Document();
            PdfPTable tablesup= new PdfPTable(5);
            PdfPCell cell = new PdfPCell(new Paragraph("Orders Information"));
            cell.setColspan(8);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.CYAN);
            tablesup.addCell(cell);
            tablesup.addCell("Order ID");
            tablesup.addCell("Client Name");
            tablesup.addCell("Product name");
            tablesup.addCell("Product quantity");
            tablesup.addCell("Total price");
            try {
                PdfWriter.getInstance(document, new FileOutputStream("Orders_Report_" + i1++ + ".pdf"));
            } catch (FileNotFoundException e) {
                e.getMessage();
            } catch (DocumentException e) {
                e.getMessage();
            }
            document.open();
            try {
                while(r1.next()) {
                    String v1 = r1.getString("id");
                    String v2 = r1.getString("name");
                    String v3 = r1.getString("product");
                    String v4 = r1.getString("quantity");
                    String v5 = r1.getString("final_price");
                    tablesup.addCell(v1);
                    tablesup.addCell(v2);
                    tablesup.addCell(v3);
                    tablesup.addCell(v4);
                    tablesup.addCell(v5);
                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                document.add(tablesup);
            } catch (DocumentException e) {
                e.getMessage();
            }
            document.close();
        }
    }

    /**
     * la fel pt clientdao, se apeleaza metoda care executa select*
     * @param a
     */
    public static void generateClient(String a) {
        ClientDAO.select(a);
    }

    /**
     * la fel se apeleaza cu rezultatul queryului, de tip resultset
     * @param a
     * @param r1
     */
    public static void generatePDFClient(String a, ResultSet r1) {
        if(a.contentEquals("client")) {
            try {
                Document document = new Document();
                PdfPTable tablesup= new PdfPTable(3);
                PdfPCell cell = new PdfPCell(new Paragraph("Clients Information"));
                cell.setColspan(8);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.CYAN);
                tablesup.addCell(cell);
                tablesup.addCell("Client ID");
                tablesup.addCell("Client Name");
                tablesup.addCell("Client Address");
                try {
                    PdfWriter.getInstance(document, new FileOutputStream("Clients_Report_" + i2++ + ".pdf"));
                } catch (FileNotFoundException e) {
                    e.getMessage();
                } catch (DocumentException e) {
                    e.getMessage();
                }
                document.open();
                while(r1.next()) {
                    String v1 = r1.getString("id");
                    String v2 = r1.getString("name");
                    String v3 = r1.getString("address");
                    tablesup.addCell(v1);
                    tablesup.addCell(v2);
                    tablesup.addCell(v3);
                }
                try {
                    document.add(tablesup);
                } catch (DocumentException e) {
                    e.getMessage();
                }
                document.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
    }
}