package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.dao.MonthDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class MonthAction extends CoreAction implements ModelDriven<Month> {

	private static final long serialVersionUID = -6627850634722739973L;

	private Month month = new Month();
	private List<Month> months = new ArrayList<Month>();
	private final MonthDAO dao = new MonthDAO();

	@Override
	public Month getModel() {
		return this.month;
	}

	public String saveOrUpdate() {
		this.dao.saveOrUpdateMonth(this.month);
		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.months = this.dao.getMonths();
		return Action.SUCCESS;
	}

	public String delete() {
		this.dao.deleteMonth(Integer.parseInt(this.request
				.getParameter("monthId")));
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		this.month = this.dao.getMonth(Integer.parseInt(this.request
				.getParameter("monthId")));
		// Load all
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
