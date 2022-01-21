package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import business.core.Journey;

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

	public ResultBean() {

	}

	@PostConstruct


	public SelectBean getSelectBean() {
		return selectBean;
	}

	public void setSelectBean(SelectBean selectBean) {
		this.selectBean = selectBean;
	}

	public String getJourney() {
		Journey journey = selectBean.getJourney();
		String toReturn = "";
		List<Excursion> excursions = journey.getJourney();
		boolean morning = true;
		int i = 1;

		for (Excursion excursion : excursions) {
			if (morning) {
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
		toReturn = toReturn + " Votre voyage vous coutera la somme de :" + journey.getPrice() + " €";
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

		for (Excursion excursion : excursions) {
			if (morning) {
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
		toReturn = toReturn + " Votre voyage vous coutera la somme de :" + journey.getPrice() + " €";
		return toReturn;
	}

	public void setNewJourney() {

	}

}
