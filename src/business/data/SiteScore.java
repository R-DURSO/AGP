package business.data;

import java.util.Comparator;
/**
 * 
 * This class will be use for store the site  and the score create with lucene 
 *
 */
public class SiteScore extends Site{
	private float score;
	public SiteScore(String idSite, String name, int price, int effort, String type, Position pos, int duration, float score) {
		super(idSite, name, price, effort, type, pos, duration);
		this.score = score;
	}
	
    public static Comparator<SiteScore> scoreComparator = new Comparator<SiteScore>() {

	public int compare(SiteScore s1, SiteScore s2) {

	   float score1 = s1.getScore();
	   float score2 = s2.getScore();

	   return Float.compare(score2,score1);
   }};
   
	public float getScore() {
		return score;
	}
	
	public void setScore(float score) {
		this.score = score;
	}
}
