package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import business.client.JourneyEntry;
import business.spring.SpringIoC;

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
	private JourneyEntry entry = new JourneyEntry();
    private String[] selectedOptions;

	public SelectBean() {
	}



	public JourneyEntry getEntry() {
		return entry;
	}

	public void setEntry(JourneyEntry entry) {
		this.entry = entry;
	}

	public int getSimulationDuration() {
		return entry.getSimulationDuration();
	}



	public int getCashierCount() {
		return entry.getCashierCount();
	}

	public void setCashierCount(int cashierCount) {
		entry.setCashierCount(cashierCount);
	}

	public int getMinServiceTime() {
		return entry.getMinServiceTime();
	}

	public void setMinServiceTime(int minServiceTime) {
		entry.setMinServiceTime(minServiceTime);
	}

	public int getMaxServiceTime() {
		return entry.getMaxServiceTime();
	}

	public void setMaxServiceTime(int maxServiceTime) {
		entry.setMaxServiceTime(maxServiceTime);
	}

	public int getClientArrivalInterval() {
		return entry.getClientArrivalInterval();
	}

	public void setClientArrivalInterval(int clientArrivalInterval) {
		entry.setClientArrivalInterval(clientArrivalInterval);
	}

	public double getPriorityClientRate() {
		return entry.getPriorityClientRate();
	}

	public void setPriorityClientRate(double priorityClientRate) {
		entry.setPriorityClientRate(priorityClientRate);
	}

	public int getClientPatienceTime() {
		return entry.getClientPatienceTime();
	}

	public void setClientPatienceTime(int clientPatienceTime) {
		entry.setClientPatienceTime(clientPatienceTime);
	}

	public void setConfortChoice(int confortChoice) {
		entry.setConfortChoice(confortChoice);
	}
	public int getprice() {
		return entry.getPrice();
	}
	public void setprice(int price) {
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
	public String getkeyWord() {
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
	public int geteffortLevel() {
		return entry.getEffortLevel();
	}
	public void seteffortLevel(int effortLevel) {
		entry.setEffortLevel(effortLevel);
	}
	
}
