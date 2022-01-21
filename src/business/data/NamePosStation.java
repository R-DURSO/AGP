package business.data;

/**
 * Class use for store de position of all station contain in DB
 */
public class NamePosStation {
	String name;
	Position position;

	public NamePosStation(String name, Position position) {
		this.name = name;
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
