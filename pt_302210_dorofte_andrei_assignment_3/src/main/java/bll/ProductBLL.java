package bll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import dao.ClientDAO;
import dao.ProductDAO;
import model.Product;

/**
 * Clasa de logica pentru operatiile pe produse
 */
public class ProductBLL {
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String verifyStatementString = "SELECT name, price FROM products WHERE name = ?";

	/**
	 * Metoda de inserare/update stoc, se verifica daca exista deja in baza de date
	 * @param product
	 */
	public static void insert(Product product) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement verifyStatement = null;
		try {
			verifyStatement = dbConnection.prepareStatement(verifyStatementString);
			verifyStatement.setString(1, product.getName());
			ResultSet r4 = verifyStatement.executeQuery();
			String nume = "";
			double price = 0;
			if(r4.next()) {
				nume += r4.getString("name");
				price = r4.getDouble("price");
		    }
			/**
			 * inserare
			 */
			if(nume.contentEquals(product.getName()) == false) {
				ProductDAO.insert(product);
			}
			/**
			 * update
			 */
			else {
				ProductDAO.update(product, price);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductBLL: insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(verifyStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	/**
	 * metoda de stergere, se verifica daca exista
	 * @param name
	 */
	public static void delete(String name) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement verifyStatement = null;
		try {
			verifyStatement = dbConnection.prepareStatement(verifyStatementString);
			verifyStatement.setString(1, name);
			ResultSet r4 = verifyStatement.executeQuery();
			String nume = "";
			if(r4.next()) {
				nume += r4.getString("name");
		    }
			if(nume.contentEquals(name) == true) {
				ProductDAO.delete(name);
			}
			/**
			 * produs inexistent
			 */
			else {
				System.out.println("Product already not in database");
				ConnectionFactory.close(verifyStatement);
				ConnectionFactory.close(dbConnection);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductBLL: delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(verifyStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
}