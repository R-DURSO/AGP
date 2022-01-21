package business.data;

/**
 * Criteria filled by the user (from the web page) used to personalize the user's journey
 */
public class JourneyCritere {

    private int confortChoice;
    private int price;
    private int duration;
    private String keyWord;
    private int frequency ;
    private int effortLevel ;

	public JourneyCritere() {
    }

    public JourneyCritere(int confortChoice, int price, int duration, String keyWord, int frequency, int effortLevel) {
    	this.confortChoice = confortChoice;
    	this.price = price;
    	this.duration = duration;
    	this.effortLevel = effortLevel;
    	this.keyWord = keyWord;
    	this.frequency = frequency;
    }
    
    public int getConfortChoice() {
		return confortChoice;
	}

	public void setConfortChoice(int confortChoice) {
		this.confortChoice = confortChoice;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getEffortLevel() {
		return effortLevel;
	}

	public void setEffortLevel(int effortLevel) {
		this.effortLevel = effortLevel;
	}

}
