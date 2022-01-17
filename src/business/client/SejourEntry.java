package business.client;

/**
 * This class encapsulates simulation entry parameters.
 */
/*
 * modifier les constante qui seront utiliser 
 */
public class SejourEntry {
    private int simulationDuration;
    private int cashierCount;
    private int minServiceTime;
    private int maxServiceTime;
    private int clientArrivalInterval;
    private double priorityClientRate;
    private int clientPatienceTime;
    //
    private int confortChoice;
    private int price;
    private int duration;
    private String keyWord;
    private int frequency ;
    private int effortLevel ;
    

	public int getConfortChoice() {
		return confortChoice;
	}

	public void setConfortChoice(int confortChoice) {
		confortChoice = confortChoice;
	}

	public SejourEntry() {
    }

    public SejourEntry(int simulationDuration, int cashierCount,
            int minServiceTime, int maxServiceTime, int clientArrivalInterval,
            double priorityClientRate, int clientPatienceTime) {
        this.simulationDuration = simulationDuration;
        this.cashierCount = cashierCount;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.clientArrivalInterval = clientArrivalInterval;
        this.priorityClientRate = priorityClientRate;
        this.clientPatienceTime = clientPatienceTime;
    }

    public int getSimulationDuration() {
        return simulationDuration;
    }

    public void setSimulationDuration(int simulationDuration) {
        this.simulationDuration = simulationDuration;
    }

    public int getCashierCount() {
        return cashierCount;
    }

    public void setCashierCount(int cashierCount) {
        this.cashierCount = cashierCount;
    }

    public int getMinServiceTime() {
        return minServiceTime;
    }

    public void setMinServiceTime(int minServiceTime) {
        this.minServiceTime = minServiceTime;
    }

    public int getMaxServiceTime() {
        return maxServiceTime;
    }

    public void setMaxServiceTime(int maxServiceTime) {
        this.maxServiceTime = maxServiceTime;
    }

    public int getClientArrivalInterval() {
        return clientArrivalInterval;
    }

    public void setClientArrivalInterval(int clientArrivalInterval) {
        this.clientArrivalInterval = clientArrivalInterval;
    }

    public double getPriorityClientRate() {
        return priorityClientRate;
    }

    public void setPriorityClientRate(double priorityClientRate) {
        this.priorityClientRate = priorityClientRate;
    }

    public int getClientPatienceTime() {
        return clientPatienceTime;
    }

    public void setClientPatienceTime(int clientPatienceTime) {
        this.clientPatienceTime = clientPatienceTime;
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
