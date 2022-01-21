package business.data;

public class Position {
	private double x;
	private double y;

	public Position(double f, double g) {
		this.x = f;
		this.y = g;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
