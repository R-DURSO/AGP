package business.data;

/**
 * 
 * Class use for store the lucene return
 *
 */
public class ScoreDocName {
	private String docName;
	private float score;

	public ScoreDocName(String docName, float score) {
		this.docName = docName;
		this.score = score;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
}