package business.data;

import java.util.List;

public class Day {
	private boolean restDay;
	private List<Excursion> excursionList;

	public List<Excursion> getExcursionList() {
		return excursionList;
	}
	public boolean isRestDay() {
		return restDay;
	}
}
