package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.FeeGroup;
import com.duke.nurseryschool.hibernate.dao.FeeGroupDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class FeeGroupAction extends CoreAction implements ModelDriven<FeeGroup> {

	private static final long serialVersionUID = 2143537118962764732L;

	private FeeGroup feeGroup = new FeeGroup();
	private List<FeeGroup> feeGroups = new ArrayList<FeeGroup>();
	final private FeeGroupDAO dao = new FeeGroupDAO();

	@Override
	public FeeGroup getModel() {
		return this.feeGroup;
	}

	public String saveOrUpdate() {
		this.dao.saveOrUpdateFeeGroup(this.feeGroup);
		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.feeGroups = this.dao.getFeeGroups();
		return Action.SUCCESS;
	}

	public String delete() {
		this.dao.deleteFeeGroup(Integer.parseInt(this.request
				.getParameter("feeGroupId")));
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		this.feeGroup = this.dao.getFeeGroup(Integer.parseInt(this.request
				.getParameter("feeGroupId")));
		// Load all
		this.feeGroups = this.dao.getFeeGroups();
		return Action.SUCCESS;
	}

	public FeeGroup getFeeGroup() {
		return this.feeGroup;
	}

	public void setFeeGroup(FeeGroup feeGroup) {
		this.feeGroup = feeGroup;
	}

	public List<FeeGroup> getFeeGroups() {
		return this.feeGroups;
	}

	public void setFeeGroups(List<FeeGroup> feeGroups) {
		this.feeGroups = feeGroups;
	}

}
