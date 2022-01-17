package persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import business.client.JourneyEntry;

public class JdbcPersistence  {
	
	public void dataInit() {
		System.err.println("Please don't forget to create tables manually by importing script.sql in your database !");
	}
	
	/**
	 * 
	 * This method get all Hotel
	 * 
	 * @return an iterator with all the Hotel
	 * */
	public ResultSet getAllHotel() {
		ResultSet result = null;
		try {
			
			String selectHotelQuery = "SELECT *  FROM hotel";
			
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectHotelQuery);
			
			result = preparedStatement.executeQuery();
			
			preparedStatement.close();
			
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return result;
	}
	
	/**
	 * 
	 * Get all Tourist Attractions
	 * 
	 * @return an iterator with all the Hotel
	 * */
	public ResultSet allTouristAttractions() {
		ResultSet result = null;
		try {
			
			String selectTouristAttractionsQuery = "SELECT * FROM site_touristique";
			
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectTouristAttractionsQuery);
			
			result = preparedStatement.executeQuery();
			
			preparedStatement.close();
			
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return result;
	}
	
	/*
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
	*/
}
