package business.data;

import java.util.List;

public class Day {
	private boolean restDay;
	private List<Excursion> excursionList;

	public Day() {
		this.restDay=true;
		this.excursionList=null;
	}
	
	public Day(boolean isRestDay, List<Excursion> excursionList) {
		this.restDay=isRestDay;
		this.excursionList=excursionList;
	}
	
	public List<Excursion> getExcursionList() {
		return excursionList;
	}
	public boolean isRestDay() {
		return restDay;
	}
	
}
