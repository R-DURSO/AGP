package business.data;

import java.util.List;

/**
 * Entry parameters selected by the user
 */
public class VisitEntry {
	private List<Site> selectedSites;
	// confortLevel is used to choose the type of transports, etc...
	private int confortLevel;
	private double minPrice;
	private double maxPrice;
	
	public VisitEntry() {	
	}

	public VisitEntry(List<Site> selectedSites, int confortLevel, double minPrice, double maxPrice) {
		this.selectedSites = selectedSites;
		this.confortLevel = confortLevel;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public List<Site> getSelectedSites() {
		return selectedSites;
	}

	public void setSelectedSites(List<Site> selectedSites) {
		this.selectedSites = selectedSites;
	}

	public int getConfortLevel() {
		return confortLevel;
	}

	public void setConfortLevel(int confortLevel) {
		this.confortLevel = confortLevel;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	
}
