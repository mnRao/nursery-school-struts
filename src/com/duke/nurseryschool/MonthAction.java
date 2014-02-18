package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.dao.MonthDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class MonthAction extends CoreAction implements ModelDriven<Month>,
		Preparable {

	private static final long serialVersionUID = -6627850634722739973L;

	private Month month = new Month();
	private List<Month> months = new ArrayList<Month>();
	private final MonthDAO dao = new MonthDAO();

	@Override
	public Month getModel() {
		return this.month;
	}

	public String saveOrUpdate() {
		this.dao.getSession().evict(
				this.dao.getMonth(Integer.parseInt(this.request
						.getParameter("monthId"))));

		this.dao.saveOrUpdateMonth(this.month);

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String list() {
		return Action.SUCCESS;
	}

	@SkipValidation
	public String delete() {
		this.dao.deleteMonth(Integer.parseInt(this.request
				.getParameter("monthId")));
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String edit() {
		this.month = this.dao.getMonth(Integer.parseInt(this.request
				.getParameter("monthId")));
		// Load all
		return Action.SUCCESS;
	}

	@Override
	public void validate() {
		if (this.month.getMonthName() > 12 || this.month.getMonthName() < 1) {
			this.addFieldError("month.monthName", this
					.getText(Constant.I18N.ERROR_CONSTRAINT_MONTH_MONTHNAME));
		}
		if (this.month.getYear() <= 0) {
			this.addFieldError("month.year",
					this.getText(Constant.I18N.ERROR_CONSTRAINT_MONTH_YEAR));
		}

		super.validate();
	}

	@Override
	public void prepare() throws Exception {
	}

	public void prepareList() throws Exception {
		this.populateData();
	}

	public void prepareEdit() throws Exception {
		this.populateData();
	}

	public void prepareSaveOrUpdate() throws Exception {
		this.populateData();
	}

	private void populateData() {
		this.months = this.dao.getMonths();
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
