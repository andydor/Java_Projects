package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import bll.ReportBLL;
import connection.ConnectionFactory;
import model.Product;

/**
 * tabela ce implementeaza operatile pe tabela products
 */
public class ProductDAO {
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO products (name, quantity, price)" + " VALUES (?, ?, ?)";
	private final static String deleteStatementString = "DELETE FROM products where name = ?";
	private static final String productStatementString = "SELECT * FROM products";
	private static final String updateStatementString = "UPDATE products set quantity = (SELECT p.quantity from (SELECT * FROM products) AS p WHERE p.name = ?) + ?, price = ? WHERE name = ?";

	/**
	 * inserare -> apelata din bll
	 * @param product
	 */
	public static void insert(Product product) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, product.getName());
			insertStatement.setInt(2, product.getQuantity());
			insertStatement.setDouble(3, product.getPrice());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO: insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	/**
	 * update -> apelata din bll
	 * @param product
	 * @param price
	 */
	public static void update(Product product, double price) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, product.getName());
			updateStatement.setInt(2, product.getQuantity());
			updateStatement.setDouble(3, product.getPrice());
			updateStatement.setString(4, product.getName());
			updateStatement.setDouble(3, price);
			updateStatement.executeUpdate();
			System.out.println("Product exists, quantity (and price) updated");
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO: update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	/**
	 * delete -> apelata din bll
	 * @param name
	 */
	public static void delete(String name) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
			deleteStatement.setString(1, name);
			deleteStatement.execute();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO: delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	/**
	 * select -> apelata din bll pentru raport tabel
	 * @param a
	 */
	public static void select(String a) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement orderStatement = null;
		try {
			orderStatement = dbConnection.prepareStatement(productStatementString);
			ResultSet r1 = orderStatement.executeQuery();
			ReportBLL.generatePDFProduct(a, r1);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO: select " + e.getMessage());
		} finally {
			ConnectionFactory.close(orderStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
}