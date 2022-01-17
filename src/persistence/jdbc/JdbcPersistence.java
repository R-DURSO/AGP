package persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.*;
import java.nio.file.*;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.*;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.*;

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
	
	public void luceneSearch(String motCle1,String motCle2) throws IOException, ParseException {
		int MAX_RESULTS = 100; //nombre max de réponses retournées
	    // 1. Specifier l'analyseur pour le texte.
	    //    Le même analyseur est utilisé pour l'indexation et la recherche
	    Analyzer analyseur = new StandardAnalyzer();

	    // 2. Creation de l'index
        //Directory index = new RAMDirectory();  //création index en mémoire
	    Path indexpath = FileSystems.getDefault().getPath("src\\persistence\\jdbc\\site\\index"); //localisation index
	    Directory index = FSDirectory.open(indexpath);  //création index sur disque
	    IndexWriterConfig config = new IndexWriterConfig(analyseur);
	    IndexWriter w = new IndexWriter(index, config);
	    
	    // 3. Indexation des documents
	    //    Ici on indexe seulement un fichier
	    File f = new File("src\\persistence\\jdbc\\site\\Cascade_Jacqueline.txt");
   		Document doc = new Document();
   		doc.add(new Field("nom", f.getName(), TextField.TYPE_STORED));
   		doc.add(new Field("contenu", new FileReader(f), TextField.TYPE_NOT_STORED));
   		w.addDocument(doc);
   		//indexer les autres documents de la même façon
   		
   		w.close(); //on ferme le index writer après l'indexation de tous les documents

    	// 4. Interroger l'index
	    DirectoryReader ireader = DirectoryReader.open(index);
	    IndexSearcher searcher = new IndexSearcher(ireader); //l'objet qui fait la recherche dans l'index
	    String reqstr = motCle1 + " " + motCle2;
	    	
	    //Parsing de la requete en un objet Query
	    //  "contenu" est le champ interrogé par defaut si aucun champ n'est precisé
	    QueryParser qp = new QueryParser("contenu", analyseur); 
	    Query req = qp.parse(reqstr);

	    TopDocs resultats = searcher.search(req, MAX_RESULTS); //recherche

	    // 6. Affichage resultats
	    System.out.println(resultats.totalHits + " documents correspondent");
	    for(int i=0; i<resultats.scoreDocs.length; i++) {
	    	int docId = resultats.scoreDocs[i].doc;
	    	Document d = searcher.doc(docId);
	    	System.out.println(d.get("nom") + ": score " + resultats.scoreDocs[i].score);
	    }
	    
	    // fermeture seulement quand il n'y a plus besoin d'acceder aux resultats
	    ireader.close();
	  }
}
