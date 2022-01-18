package persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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

import business.data.CheapHotel;
import business.data.Hotel;
import business.data.LuxuryHotel;
import business.data.MediumHotel;
import business.data.Position;

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
	public ResultSet getAllHotel(int comfort) {
		ResultSet result = null;
		int comfortLevel;
		int priceLevel;
		Position position = new Position(0,0);
		String name;
		ArrayList <Hotel> hotelList =  new ArrayList<Hotel>();
		try {
			
			String selectHotelQuery = "SELECT nom, ST_X(t.position) as x_coordinate, ST_Y(t.myPointColumn) as y_coordinate "
					+ ", prix, confort, FROM hotel WHERE confort = ?";
			
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectHotelQuery);
			
			preparedStatement.setInt(1,comfort);
			
			result = preparedStatement.executeQuery();
		
			name = result.getString("nom");
			position.setX(result.getFloat("x_coordinate"));
			position.setY(result.getFloat("y_coordinate"));
			comfortLevel = result.getInt("confort");
			priceLevel = result.getInt("prix");
			
			//Hotel hotel = new Hotel(comfortLevel,priceLevel,position,name);
			if(priceLevel<100) {
				Hotel hotel = new CheapHotel(comfortLevel, priceLevel, position, name);
				hotelList.add(hotel);
			}else if(priceLevel<150) {
				Hotel hotel = new MediumHotel(comfortLevel, priceLevel, position, name);
				hotelList.add(hotel);
			}else {
				Hotel hotel = new LuxuryHotel(comfortLevel, priceLevel, position, name);
				hotelList.add(hotel);
			}
			
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

	
	/**
	 * 
	 * Get all Tourist Attractions
	 * 
	 * @return an iterator with all the Hotel
	 * */
	private Iterator<ScoreDocName> luceneSearch (String motCle1,String motCle2) throws IOException, ParseException {
		int MAX_RESULTS = 100; //nombre max de réponses retournées
		ArrayList <ScoreDocName> scoreList = new ArrayList<ScoreDocName>();
		Iterator<ScoreDocName> scoreIterator = null;
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
	    File f = new File("src\\persistence\\jdbc\\site\\5.txt");
   		Document doc = new Document();
   		doc.add(new Field("nom", f.getName(), TextField.TYPE_STORED));
   		doc.add(new Field("contenu", new FileReader(f), TextField.TYPE_NOT_STORED));
   		w.addDocument(doc);
   		
	    File f2 = new File("src\\persistence\\jdbc\\site\\0.txt");
	    doc = new Document();
   		doc.add(new Field("nom", f2.getName(), TextField.TYPE_STORED));
   		doc.add(new Field("contenu", new FileReader(f2), TextField.TYPE_NOT_STORED));
   		w.addDocument(doc);
   		
	    File f3 = new File("src\\persistence\\jdbc\\site\\1.txt");
	    doc = new Document();
   		doc.add(new Field("nom", f3.getName(), TextField.TYPE_STORED));
   		doc.add(new Field("contenu", new FileReader(f3), TextField.TYPE_NOT_STORED));
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
	    	ScoreDocName scoreDocName = new ScoreDocName(d.get("nom"),resultats.scoreDocs[i].score);
	    	scoreList.add(scoreDocName);
	    	scoreIterator= scoreList.iterator();
	    	System.out.println(d.get("nom") + ": score " + resultats.scoreDocs[i].score);
	    }
	    // fermeture seulement quand il n'y a plus besoin d'acceder aux resultats
	    ireader.close();
	    index.deleteFile("_0.cfe");
	    index.deleteFile("_0.cfs");
	    index.deleteFile("_0.si");
	    index.deleteFile("segments_1");
	    index.deleteFile("write.lock");
	    return scoreIterator;
	  }

	
	/**
	 * 
	 * Get all Tourist Attractions name
	 * 
	 * @return an iterator with all the Hotel
	 * */
	private ResultSet getAllTouristAttractionsName() {
		ResultSet result = null;
		try {
			
			String selectTouristAttractionsQuery = "SELECT nom_site FROM site_touristique";
			
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectTouristAttractionsQuery);
			
			result = preparedStatement.executeQuery();
			
			preparedStatement.close();
			
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return result;
	}
}

