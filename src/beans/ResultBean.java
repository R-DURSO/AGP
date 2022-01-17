package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.chart.PieChartModel;


import dao.StatisticPersistence;

/**
 * 
 * Proxy design pattern is used for getting simulation results.
 *
 */
@ManagedBean
@RequestScoped
public class ResultBean {

	private PieChartModel pieModel1;

	@ManagedProperty(value = "#{entryBean}")
	private SelectBean entryBean;
	
	public ResultBean() {

	}


	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}

	public SelectBean getEntryBean() {
		return entryBean;
	}

	public void setEntryBean(SelectBean entryBean) {
		this.entryBean = entryBean;
	}


}
