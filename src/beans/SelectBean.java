package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import business.data.SejourCritere;

/**
 * Simulation bean controller used to collect simulation entry parameters and to start the simulation.
 * 
 * The proxy design pattern is used for avoiding redundant code copy.
 */
@ManagedBean
@SessionScoped
public class SelectBean {

	/**
	 * Proxy encapsulated object. All get/set of parameters work on this proxy object.
	 */
	private SejourCritere entry = new SejourCritere();
    //private String[] selectedOptions;
    private List<Integer> scale;

	public SelectBean() {
	}
	
	@PostConstruct
	public void init() {
		scale = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            scale.add(i);
        }
	}
	
	public String findSejour() {
		System.out.println("test");
		return "result";
	}


	public SejourCritere getEntry() {
		return entry;
	}

	public void setEntry(SejourCritere entry) {
		this.entry = entry;
	}

	
	public void setConfortChoice(int confortChoice) {
		entry.setConfortChoice(confortChoice);
	}
	
	public int getPrice() {
		return entry.getPrice();
	}
	
	public void setPrice(int price) {
		entry.setPrice(price);
	}
	
	public int getConfortChoice() {
		return entry.getConfortChoice();
	}
	
	public void setDuration(int duration) {
		entry.setDuration(duration);
	}
	
	public int getDuration() {
		return entry.getDuration();
	}
	
	public String getKeyWord() {
		return entry.getKeyWord();
	}
	
	public void setKeyWord(String keyWord) {
		entry.setKeyWord(keyWord);
	}
	
	public void setFrequency( int frequency) {
		entry.setFrequency(frequency);
	}
	
	public int getFrequency() {
		return entry.getFrequency();
	}
	
	public int getEffortLevel() {
		return entry.getEffortLevel();
	}
	
	public void setEffortLevel(int effortLevel) {
		entry.setEffortLevel(effortLevel);
	}
	
	public List<Integer> getScale() {
		return scale;
	}

	public void setScale(List<Integer> scale) {
		this.scale = scale;
	}

}
