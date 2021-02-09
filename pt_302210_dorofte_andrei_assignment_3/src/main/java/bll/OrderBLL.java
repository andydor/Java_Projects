package bll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import dao.ClientDAO;
import dao.OrderDAO;
import model.Order;

/**
 * Clasa de logica pentru generarea unei comenzi
 */
public class OrderBLL {
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String verifyStatementString = "SELECT quantity FROM products WHERE name = ?";
	private static final String verify1StatementString = "SELECT name FROM client WHERE name = ?";
	private static final String priceStatementString = "SELECT price FROM products WHERE name = ?";
	private static final String numberStatementString = "SELECT id FROM orders WHERE name = ?";

	/**
	 * metoda de inserare comanda/afisare mesaje de eroare/pdf eroare, se compara cantitatea de pe stoc cu cea din comanda, se verifica daca clientul este in baza de date
	 * @param order
	 */
	public static void insert(Order order) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement verifyStatement = null;
		PreparedStatement verify1Statement = null;
		PreparedStatement priceStatement = null;
		PreparedStatement numberStatement = null;
		try {
			verifyStatement = dbConnection.prepareStatement(verifyStatementString);
			verifyStatement.setString(1, order.getProduct());
			ResultSet r1 = verifyStatement.executeQuery();
			int quantity = 0;
			if(r1.next()) {
				quantity =  Integer.parseInt(r1.getString("quantity"));
		    }
			verify1Statement = dbConnection.prepareStatement(verify1StatementString);
			verify1Statement.setString(1, order.getName());
			ResultSet r4 = verify1Statement.executeQuery();
			String nume = "";
			if(r4.next()) {
				nume += r4.getString("name");
		    }
			//System.out.println(nume);
			/**
			 * stoc suficient, client existent, comanda valida
			 */
			if(quantity > order.getQuantity() && nume.contentEquals(order.getName()) == true) {
				priceStatement = dbConnection.prepareStatement(priceStatementString);
				priceStatement.setString(1, order.getProduct());
				ResultSet r3 = priceStatement.executeQuery();
				double price = 0;
				if(r3.next()) {
					price =  Double.parseDouble(r3.getString("price"));
			    }
				numberStatement = dbConnection.prepareStatement(numberStatementString);
				numberStatement.setString(1, order.getName());
				ResultSet r2 = numberStatement.executeQuery();
				int id = 0;
				if(r2.next()) {
					id =  Integer.parseInt(r2.getString("id"));
			    }
				OrderDAO.insert(order, price, id);
			}
			/**
			 * Stoc insuficient, comanda invalida
			 */
			else if(quantity < order.getQuantity() && nume.contentEquals(order.getName()) == true){
					Generate_Order.generate_not_Order(order);
					ConnectionFactory.close(verifyStatement);
					ConnectionFactory.close(verify1Statement);
					ConnectionFactory.close(dbConnection);
				}
			/**
			 * Client inexistent, comanda invalida
			 */
				else {
					System.out.println("Client not in database");
					ConnectionFactory.close(verifyStatement);
					ConnectionFactory.close(verify1Statement);
					ConnectionFactory.close(dbConnection);
				}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderBLL: insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(verifyStatement);
			ConnectionFactory.close(verify1Statement);
			ConnectionFactory.close(dbConnection);
		}
	}
}
