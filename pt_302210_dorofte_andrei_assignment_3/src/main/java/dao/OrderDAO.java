package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import bll.Generate_Order;
import bll.ReportBLL;
import connection.ConnectionFactory;
import model.Order;

/**
 * clasa ce implementeaza operatiile pe tabela order
 */
public class OrderDAO {
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO orders (name, product, quantity, final_price)" + " VALUES (?, ?, ?, ?)";
	private static final String orderStatementString = "SELECT * FROM orders";
	private static final String updateStatementString = "UPDATE products set quantity = (SELECT p.quantity from (SELECT * FROM products) AS p WHERE p.name = ?) - ? WHERE name = ?";

	/**
	 * inserare -> apelata din bll, se executa statementul
	 * @param order
	 * @param price
	 * @param id
	 */
	public static void insert(Order order, double price, int id) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		PreparedStatement updateStatement = null;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, order.getName());
			insertStatement.setString(2, order.getProduct());
			insertStatement.setInt(3, order.getQuantity());
			insertStatement.setDouble(4, order.getQuantity() * price);
			/**
			 * calcul pret final
			 */
			order.setFinal_price(order.getQuantity() * price);
			insertStatement.executeUpdate();
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, order.getProduct());
			updateStatement.setInt(2, order.getQuantity());
			updateStatement.setString(3, order.getProduct());
			updateStatement.executeUpdate();
			Generate_Order.generate_Order(order, price, id);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO: insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
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
			orderStatement = dbConnection.prepareStatement(orderStatementString);
			ResultSet r1 = orderStatement.executeQuery();
			ReportBLL.generatePDFOrder(a, r1);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO: select " + e.getMessage());
		} finally {
			ConnectionFactory.close(orderStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
}