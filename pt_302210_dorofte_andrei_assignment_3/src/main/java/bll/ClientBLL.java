package bll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import dao.ClientDAO;
import model.Client;

/**
 * Aceasta clasa serveste ca logica a operatiilor asupra unui client.
 */
public class ClientBLL {
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private final static String verifyStatementString = "SELECT name FROM client where name = ?";

	/**
	 * Metoda de logica a inserarii/update, se verifica daca exista deja in baza de date
	 * @param client
	 */
	public static void insert(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement verifyStatement = null;
		try {
			verifyStatement = dbConnection.prepareStatement(verifyStatementString);
			verifyStatement.setString(1, client.getName());
			ResultSet r4 = verifyStatement.executeQuery();
			String nume = "";
			if(r4.next()) {
				nume += r4.getString("name");
		    }
			/**
			 * client inexistent
			 */
			if(nume.contentEquals(client.getName()) == false) {
				ClientDAO.insert(client);
			}
			/**
			 * client existent
			 */
			else {
				System.out.println("Client already exists");
				ConnectionFactory.close(verifyStatement);
				ConnectionFactory.close(dbConnection);
				ConnectionFactory.close(r4);
				return;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientBLL: insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(verifyStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	/**
	 * Metoda de stergere, se verifica daca exista
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
				ClientDAO.delete(name);
			}
			/**
			 * daca nu exista
			 */
			else {
				System.out.println("Client already not in database");
				ConnectionFactory.close(verifyStatement);
				ConnectionFactory.close(dbConnection);
				return;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientBLL: insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(verifyStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
}