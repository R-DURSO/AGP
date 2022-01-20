package persistence.jdbc;

import java.util.Comparator;

import business.data.Position;

public class SiteScore {
	private String name;
	private int price;
	private int effort;
	private String type; 
	private Position pos;
	private int duration;
	private float score;
	public SiteScore(String name, int price, int effort, String type, Position pos, int duration, float score) {
		this.name = name;
		this.price = price;
		this.effort = effort;
		this.type = type;
		this.pos = pos;
		this.setDuration(duration);
		this.score = score;
	}
	
    public static Comparator<SiteScore> scoreComparator = new Comparator<SiteScore>() {

	public int compare(SiteScore s1, SiteScore s2) {

	   float score1 = s1.getScore();
	   float score2 = s2.getScore();

	   return Float.compare(score2,score1);
   }};
   
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getEffort() {
		return effort;
	}
	public void setEffort(int effort) {
		this.effort = effort;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Position getPos() {
		return pos;
	}
	public void setPos(Position pos) {
		this.pos = pos;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
}
