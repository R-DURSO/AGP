package persistence.jdbc;

import business.data.Position;
import business.data.Site;

public class MockQuery {

	
	// (String name, String description, int price, int effort, boolean type, Position pos)
	public Site getActivity() {
		return new Site("cascade","blabla",150,5,true,new Position(0.0f,1.0f));
		
	}
}
