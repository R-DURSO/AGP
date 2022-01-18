package test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.lucene.queryparser.classic.ParseException;

import persistence.jdbc.JdbcPersistence;

public class testQuery {
	public static void main(String[] args) throws IOException, ParseException, SQLException {
		JdbcPersistence persistence = new JdbcPersistence();
		ResultSet queryResultSet = persistence.allTouristAttractions();

		if(queryResultSet != null) {
			System.out.println(queryResultSet.getInt(1));
		}
}
}