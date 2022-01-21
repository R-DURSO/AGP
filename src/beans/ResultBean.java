package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.aspectj.apache.bcel.generic.RETURN;
import org.hibernate.mapping.Set;
import org.primefaces.model.chart.PieChartModel;

import business.core.Journey;
import business.data.Day;
import business.data.Excursion;



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
	public String ListofSejour(Day day) {
		
		return day.toString()+"\n";

	}



	public SelectBean getSelectBean() {
		return selectBean;
	}

	public void setSelectBean(SelectBean selectBean) {
		this.selectBean = selectBean;
	}
	
//	public String getJourney() {
//		Journey journey = selectBean.getJourney();
//		String allString = "";
//		List<Day> days = journey.getWeek();
//		for (Day day : days) {
//			allString = allString + ListofSejour(day)+"<br />";
//		}
//		return allString;
//	}
	
	public String getJourney() {
		Journey journey = selectBean.getJourney();
		String toReturn = "";
		List<Excursion> excursions = journey.getJourney();
		boolean morning = true;
		int i = 1;
		
		for(Excursion excursion : excursions) {
			if(morning) {
				toReturn += "====  Jour " + i + ":  ====<br/>";
				toReturn += "Matin: <br/>";
			} else {
				toReturn += "Après-midi: <br/>";
				i++;
			}
			toReturn += "-Hôtel de départ: " + excursion.getDepartureHotel().getName() + "<br/>";
			toReturn += "-Hôtel d'arrivée: " + excursion.getArrivalHotel().getName() + "<br/>";
			toReturn += "-Visites prévues:<br/>";
			toReturn += excursion.getSiteName() + "<br/>";
			
			morning = !morning;
		}
		
		return toReturn;
	}
	
	public void setJourney() {
		
	}
	public String getNewJourney() {
		Journey journey = selectBean.getJourney();
		journey.createJourney();
		String toReturn = "";
		List<Excursion> excursions = journey.getJourney();
		boolean morning = true;
		int i = 1;
		
		for(Excursion excursion : excursions) {
			if(morning) {
				toReturn += "====  Jour " + i + ":  ====<br/>";
				toReturn += "Matin: <br/>";
			} else {
				toReturn += "Après-midi: <br/>";
				i++;
			}
			toReturn += "-Hôtel de départ: " + excursion.getDepartureHotel().getName() + "<br/>";
			toReturn += "-Hôtel d'arrivée: " + excursion.getArrivalHotel().getName() + "<br/>";
			toReturn += "-Visites prévues:<br/>";
			toReturn += excursion.getSiteName() + "<br/>";
			
			morning = !morning;
		}
		
		return toReturn;
	}
	public void setNewJourney() {
		
	}

}
