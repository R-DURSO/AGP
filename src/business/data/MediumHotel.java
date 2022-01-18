package business.data;

public class MediumHotel extends Hotel {

	
	public MediumHotel(Position pos, String name) {
		super(2,2, pos, name);//mock
	}
	
	public MediumHotel(int comfortLevel, int priceLevel, Position position, String name) {
		super(comfortLevel, priceLevel, position, name);
	}
}
