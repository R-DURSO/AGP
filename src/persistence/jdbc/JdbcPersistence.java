package persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import business.client.JourneyEntry;
import dao.StatisticPersistence;

public class JdbcPersistence  {
	
	public void dataInit() {
		System.err.println("Please don't forget to create tables manually by importing creation.sql in your database !");
	}

	private int persistEntry(JourneyEntry simulationEntry) {
		int idEntry = 0;
		try {

			String insertAddressQuery = "INSERT INTO entry (simulation_duration, cashier_count, "
					+ "min_service_time, max_service_time, client_arrival_interval, priority_client_rate, client_patience_time"
					+ ") VALUES (?,?,?,?,?,?,?)";

			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(insertAddressQuery);

			// Set values of parameters in the query.
			preparedStatement.setInt(1, simulationEntry.getSimulationDuration());
			preparedStatement.setInt(2, simulationEntry.getCashierCount());
			preparedStatement.setInt(3, simulationEntry.getMinServiceTime());
			preparedStatement.setInt(4, simulationEntry.getMaxServiceTime());
			preparedStatement.setInt(5, simulationEntry.getClientArrivalInterval());
			preparedStatement.setDouble(6, simulationEntry.getPriorityClientRate());
			preparedStatement.setInt(7, simulationEntry.getClientPatienceTime());

			preparedStatement.executeUpdate();

			ResultSet keys = preparedStatement.getGeneratedKeys();
			keys.next();
			idEntry = keys.getInt(1);

			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return idEntry;
	}

	public int nonServedClientCount(int simulationEntryId) {
		int count = 0;
		try {

			String selectAddressQuery = "SELECT count(*) AS co FROM client AS c WHERE c.entry_id = ? AND c.is_served = false";

			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectAddressQuery);

			preparedStatement.setInt(1, simulationEntryId);

			ResultSet result = preparedStatement.executeQuery();

			result.next();
			count = result.getInt("co");

			preparedStatement.close();

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		
		return count;
	}






}
