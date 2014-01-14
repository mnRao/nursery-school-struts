package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.dao.MonthDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MonthAction extends CoreAction implements ModelDriven<Month> {

	private Month month = new Month();
	private List<Month> months = new ArrayList<Month>();
	private final MonthDAO dao = new MonthDAO();

	@Override
	public Month getModel() {
		return this.month;
	}

	@Override
	public String execute() {
		this.dao.addMonth(this.month);
		return Action.SUCCESS;
	}

	public String listMonths() {
		this.months = this.dao.getMonths();
		return Action.SUCCESS;
	}

	public List<Month> getMonths() {
		return this.months;
	}

	public void setMonths(List<Month> months) {
		this.months = months;
	}

	public Month getMonth() {
		return this.month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

}
