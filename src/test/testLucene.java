package test;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;

import persistence.jdbc.JdbcPersistence;

public class testLucene {
	public static void main(String[] args) throws IOException, ParseException {
		JdbcPersistence persistence = new JdbcPersistence();
		persistence.luceneSearch("cascade", "rivière");
	}
}