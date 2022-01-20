package test;

import java.io.IOException;
import java.util.Iterator;

import org.apache.lucene.queryparser.classic.ParseException;

import persistence.jdbc.JdbcPersistence;
import persistence.jdbc.ScoreDocName;

public class testLucene {
	public static void main(String[] args) throws IOException, ParseException {
		JdbcPersistence persistence = new JdbcPersistence();
		Iterator<ScoreDocName> scoreIterator = null;
		ScoreDocName scoreDocName = null;
		scoreIterator = persistence.luceneSearch("Cascades", "rivière");
		while(scoreIterator.hasNext()) {
			scoreDocName = scoreIterator.next();
			System.out.println("Nom du document "+scoreDocName.getDocName()+" "
					+ "score de pertinence "+scoreDocName.getScore());
		}
		
	}
}