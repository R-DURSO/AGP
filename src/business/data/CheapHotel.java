package business.data;

public class CheapHotel extends Hotel{

	public CheapHotel(Position pos, String name) {
		super(1, 1, pos, name);
	}
	public CheapHotel(int comfortLevel, int priceLevel, Position position, String name) {
		super(comfortLevel, priceLevel, position, name);
	}
}
