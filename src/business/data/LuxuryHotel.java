package business.data;

/**
 * Data class for a type of hotel
 */
public class LuxuryHotel extends Hotel {
	public LuxuryHotel(Position pos, String name) {
		super(3,3, pos, name);//mock to show somthg
	}
	public LuxuryHotel(int comfortLevel, int priceLevel, Position position, String name) {
		super(comfortLevel, priceLevel, position, name);
	}
}
