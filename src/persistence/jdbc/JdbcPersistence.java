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
import business.data.Site;

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
	public Iterator<Hotel> getAllHotel(int comfort) {
		int comfortLevel;
		int priceLevel;
		Position position = new Position(0,0);
		String name;
		ArrayList <Hotel> hotelList =  new ArrayList<Hotel>();
		Iterator<Hotel> hotelIterator = null;
		try {
			
			String selectHotelQuery = "SELECT nom, ST_X(t.position) as x_coordinate, ST_Y(t.myPointColumn) as y_coordinate "
					+ ", prix, confort FROM hotel WHERE confort = ?";
			
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectHotelQuery);
			
			//preparedStatement.setInt(1,comfort);
			
			ResultSet result = preparedStatement.executeQuery();
	
			
			while (result.next()) {
				name = result.getString("nom");
				position.setX(result.getFloat("x_coordinate"));
				position.setY(result.getFloat("y_coordinate"));
				comfortLevel = result.getInt("confort");
				priceLevel = result.getInt("prix");
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
			}
			preparedStatement.close();
			
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		hotelIterator = hotelList.iterator();
		return hotelIterator;
	}
	
	/**
	 * 
	 * Get all Tourist Attractions
	 * 
	 * @return an iterator with all the TouristAttractions
	 * */
	private Iterator<Site> allTouristAttractions() {
		String name;
		int price;
		int effort;
		String type;
		Position position = new Position(0,0);
		int duration;
		ArrayList <Site> SiteList =  new ArrayList<Site>();
		Iterator<Site> SiteIterator = null;
		try {
			
			String selectTouristAttractionsQuery = "SELECT nom_site, type_lieux, niveau_effort, "
					+ "duree_activite, prix,"
					+ "ST_X(position_site) as x_coordinate, ST_Y(position_site) as y_coordinate"
					+ " FROM site_touristique";
			
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectTouristAttractionsQuery);
			
			ResultSet result = preparedStatement.executeQuery();
					
			while (result.next()) {
				name = result.getString("nom_site");
				position.setX(result.getFloat("x_coordinate"));
				position.setY(result.getFloat("y_coordinate"));
				type = result.getString("type_lieux");
				effort = result.getInt("niveau_effort");
				duration = result.getInt("duree_activite");
				price = result.getInt("prix");
				Site site = new Site(name,price,effort,type,position,duration);
				SiteList.add(site);
			}
			
			preparedStatement.close();
			
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		SiteIterator = SiteList.iterator();
		return SiteIterator;
	}

	/**
	 * This method compute the mean price of the luxurious hotel
	 * 
	 * @return mean of the price of the luxurious hotel
	 * @throws SQLException
	 */
	public float luxuriousHotelMean() throws SQLException {
		float meanHotelPrice = 0;
		String selectluxuriousHotelMeanQuery = "SELECT AVG(prix) FROM hotel WHERE prix >= 150 ";
		try {
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectluxuriousHotelMeanQuery);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			meanHotelPrice = result.getFloat(1);
			preparedStatement.close();
			
		} catch (SQLException se) {
			System.err.println("le message d'erreur est : "+se.getMessage());
		}
		return meanHotelPrice;
	}
	
	
	/**
	 * This method compute the mean price of the average hotel
	 * 
	 * @return mean of the price of the average hotel
	 * @throws SQLException
	 */
	public float averageHotelMean() throws SQLException {
		ResultSet result = null;
		float meanHotelPrice = 0;
		try {
			
			String selectAverageHotelMeanQuery = "SELECT AVG(prix) FROM hotel WHERE prix >= 100 AND prix < 150";
			
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectAverageHotelMeanQuery);
			
			result = preparedStatement.executeQuery();
			result.next();
			meanHotelPrice = result.getFloat(1);
			preparedStatement.close();
			
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return meanHotelPrice;
	}
	
	/**
	 * This method compute the mean price of the eco hotel
	 * 
	 * @return mean of the price of the eco hotel
	 * @throws SQLException
	 */
	public float ecoHotelMean() throws SQLException {
		ResultSet result = null;
		float meanHotelPrice = 0;
		try {
			
			String selectEcoHotelMeanQuery = "SELECT AVG(prix) FROM hotel WHERE prix < 100";
			
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectEcoHotelMeanQuery);
			
			result = preparedStatement.executeQuery();
			result.next();
			meanHotelPrice = result.getFloat(1);
			preparedStatement.close();
			
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return meanHotelPrice;
	}
	
	
	/**
	 * 
	 * Get all Tourist Attractions
	 * 
	 * @return an iterator with all the Hotel
	 * */
	private Iterator<ScoreDocName> luceneSearch (String keyWord1,String keyWord2) throws IOException, ParseException {
		int MAX_RESULTS = 100; //nombre max de réponses retournées
		ArrayList <ScoreDocName> scoreList = new ArrayList<ScoreDocName>();
		Iterator<ScoreDocName> scoreIterator = null;
		Iterator <String> iteratorIdList = getAllTouristAttractionsId();
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
	    while(iteratorIdList.hasNext()) {
	    	String fileName = iteratorIdList.next()+".txt";
	    	//System.out.println("nom du fichier : "+fileName);
		    File f = new File("src\\persistence\\jdbc\\site\\"+fileName);
	   		Document doc = new Document();
	   		doc.add(new Field("nom", f.getName(), TextField.TYPE_STORED));
	   		doc.add(new Field("contenu", new FileReader(f), TextField.TYPE_NOT_STORED));
	   		w.addDocument(doc);
	    }		
   		w.close();

    	// 4. Interroger l'index
	    DirectoryReader ireader = DirectoryReader.open(index);
	    IndexSearcher searcher = new IndexSearcher(ireader); //l'objet qui fait la recherche dans l'index
	    String reqstr = keyWord1 + " " + keyWord2;
	    	
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
	    	System.out.println(d.get("nom") + ": score " + resultats.scoreDocs[i].score);
	    }
	    // fermeture seulement quand il n'y a plus besoin d'acceder aux resultats
	    
	    scoreIterator= scoreList.iterator(); 
	    
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
	 * @return a iterator with all the tourist attractions id
	 * */
	private Iterator <String> getAllTouristAttractionsId() {
		ArrayList <String> idList = new ArrayList<String>();
		try {
			
			String selectTouristAttractionsQuery = "SELECT id_site FROM site_touristique";
			
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectTouristAttractionsQuery);
			
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				idList.add(result.getString(1));
			}
			preparedStatement.close();
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		Iterator <String> iteratorIdList = idList.iterator();
		return iteratorIdList;
	}
}

