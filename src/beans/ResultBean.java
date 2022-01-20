package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.aspectj.apache.bcel.generic.RETURN;
import org.primefaces.model.chart.PieChartModel;

import business.core.Journey;
import business.data.Day;



/**
 * 
 * Proxy design pattern is used for getting simulation results.
 *
 */
@ManagedBean
@RequestScoped
public class ResultBean {



	@ManagedProperty(value = "#{selectBean}")
	private SelectBean selectBean;
	
	public  ResultBean() {

	}

	@PostConstruct
	public String  ListofSejour(Day day) {
		
			return day.toString()+"\n";

	}



	public SelectBean getSelectBean() {
		return selectBean;
	}

	public void setSelectBean(SelectBean selectBean) {
		this.selectBean = selectBean;
	}
	public String getHolliday() {
		Journey hollidayJourney = selectBean.getJourney();
		String allString = "";
		List<Day>  days = hollidayJourney.getWeek();
		for (Day day : days) {
			allString = allString + ListofSejour(day)+"<br />";
		}
		return allString;
	}
	public void setHolliday() {
		
	}
	

}
