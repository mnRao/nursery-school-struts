package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.ExtraFeeType;
import com.duke.nurseryschool.hibernate.dao.ExtraFeeTypeDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class ExtraFeeTypeAction extends CoreAction implements
		ModelDriven<ExtraFeeType> {

	private static final long serialVersionUID = 2143537118962764732L;

	private ExtraFeeType extraFeeType = new ExtraFeeType();
	private List<ExtraFeeType> extraFeeTypes = new ArrayList<ExtraFeeType>();
	private ExtraFeeTypeDAO dao = new ExtraFeeTypeDAO();

	@Override
	public ExtraFeeType getModel() {
		return this.extraFeeType;
	}

	public String saveOrUpdate() {
		this.dao.saveOrUpdateExtraFeeType(this.extraFeeType);
		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.extraFeeTypes = this.dao.getExtraFeeTypes();
		return Action.SUCCESS;
	}

	public String delete() {
		this.dao.deleteExtraFeeType(Integer.parseInt(this.request
				.getParameter("extraFeeTypeId")));
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		this.extraFeeType = this.dao.getExtraFeeType(Integer
				.parseInt(this.request.getParameter("extraFeeTypeId")));
		// Load all
		this.extraFeeTypes = this.dao.getExtraFeeTypes();
		return Action.SUCCESS;
	}

	public List<ExtraFeeType> getExtraFeeTypes() {
		return this.extraFeeTypes;
	}

	public void setExtraFeeTypes(List<ExtraFeeType> extraFeeTypes) {
		this.extraFeeTypes = extraFeeTypes;
	}

	public ExtraFeeType getExtraFeeType() {
		return this.extraFeeType;
	}

	public void setExtraFeeType(ExtraFeeType extraFeeType) {
		this.extraFeeType = extraFeeType;
	}

}
