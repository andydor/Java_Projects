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
import model.Client;

/**
 * clasa ce implementeaza operatiile pe tabela client
 */
public class ClientDAO {
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO client (name, address)" + " VALUES (?, ?)";
	private final static String deleteStatementString = "DELETE FROM client where name = ?";
	private static final String clientStatementString = "SELECT * FROM client";

	/**
	 * inserare -> apelata din bll, se executa statementul
	 * @param client
	 */
	public static void insert(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, client.getName());
			insertStatement.setString(2, client.getAddress());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO: insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	/**
	 * stergere -> apelata din bll, se executa statementul
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
			LOGGER.log(Level.WARNING, "ClientDAO: delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	/**
	 * select -> apelata din bll pentru raport tabel, se executa statemenul
	 * @param a
	 */
	public static void select(String a) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement orderStatement = null;
		try {
			orderStatement = dbConnection.prepareStatement(clientStatementString);
			ResultSet r1 = orderStatement.executeQuery();
			ReportBLL.generatePDFClient(a, r1);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO: select " + e.getMessage());
		} finally {
			ConnectionFactory.close(orderStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
}