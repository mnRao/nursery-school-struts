package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.FeeType;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.FeeGroup;
import com.duke.nurseryschool.hibernate.bean.Student;
import com.duke.nurseryschool.hibernate.dao.FeeDAO;
import com.duke.nurseryschool.hibernate.dao.FeeGroupDAO;
import com.duke.nurseryschool.hibernate.dao.StudentDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class FeeAction extends CoreAction implements ModelDriven<Fee> {

	private static final long serialVersionUID = -559372069321576936L;

	private Fee fee = new Fee();
	private List<Fee> fees = new ArrayList<Fee>();
	final private FeeDAO dao = new FeeDAO();

	private int feeGroupId;
	private List<FeeGroup> feeGroupList;
	final private FeeGroupDAO feeGroupDAO = new FeeGroupDAO();

	private int feeTypeId;
	private List<FeeType> feeTypeList;

	@Override
	public Fee getModel() {
		return this.fee;
	}

	public String saveOrUpdate() {
		//
		this.fee.setType(FeeType.parse(this.feeTypeId));
		FeeGroup feeGroup = this.feeGroupDAO.getFeeGroup(this.feeGroupId);
		this.fee.setFeeGroup(feeGroup);

		this.dao.saveOrUpdateFee(this.fee);
		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.populateLists();
		this.fees = this.dao.getFees();
		return Action.SUCCESS;
	}

	public String delete() {
		this.dao.deleteFee(Integer.parseInt(this.request.getParameter("feeId")));
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		this.fee = this.dao.getFee(Integer.parseInt(this.request
				.getParameter("feeId")));
		this.populateLists();
		// Load all
		this.fees = this.dao.getFees();
		return Action.SUCCESS;
	}

	private void populateLists() {
		this.feeGroupList = this.feeGroupDAO.getFeeGroups();
		this.feeTypeList = FeeType.getAll();

		if (this.fee.getFeeGroup() != null)
			this.feeGroupId = this.fee.getFeeGroup().getFeeGroupId();
		if (this.fee.getType() != null)
			this.feeTypeId = this.fee.getType().getType();
	}

	public Fee getFee() {
		return this.fee;
	}

	public void setFee(Fee fee) {
		this.fee = fee;
	}

	public List<Fee> getFees() {
		return this.fees;
	}

	public void setFees(List<Fee> fees) {
		this.fees = fees;
	}

	public int getFeeGroupId() {
		return this.feeGroupId;
	}

	public void setFeeGroupId(int feeGroupId) {
		this.feeGroupId = feeGroupId;
	}

	public List<FeeGroup> getFeeGroupList() {
		return this.feeGroupList;
	}

	public void setFeeGroupList(List<FeeGroup> feeGroupList) {
		this.feeGroupList = feeGroupList;
	}

	public int getFeeTypeId() {
		return this.feeTypeId;
	}

	public void setFeeTypeId(int feeTypeId) {
		this.feeTypeId = feeTypeId;
	}

	public List<FeeType> getFeeTypeList() {
		return this.feeTypeList;
	}

	public void setFeeTypeList(List<FeeType> feeTypeList) {
		this.feeTypeList = feeTypeList;
	}

}
