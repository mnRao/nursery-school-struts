package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.StringUtil;
import com.duke.nurseryschool.hibernate.bean.FeeGroup;
import com.duke.nurseryschool.hibernate.dao.FeeDAO;
import com.duke.nurseryschool.hibernate.dao.FeeGroupDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class FeeGroupAction extends CoreAction implements
		ModelDriven<FeeGroup>, Preparable {

	private static final long	serialVersionUID	= 2143537118962764732L;

	private FeeDAO				feeDAO				= new FeeDAO();
	private FeeGroup			feeGroup			= new FeeGroup();
	private List<FeeGroup>		feeGroups			= new ArrayList<FeeGroup>();
	final private FeeGroupDAO	dao					= new FeeGroupDAO();

	@Override
	public FeeGroup getModel() {
		return this.feeGroup;
	}

	public String saveOrUpdate() {
		this.dao.getSession().evict(
				this.dao.getFeeGroup(Integer.parseInt(this.request
						.getParameter("feeGroupId"))));

		this.dao.saveOrUpdateFeeGroup(this.feeGroup);
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String list() {
		return Action.SUCCESS;
	}

	@SkipValidation
	public String delete() {
		boolean isDeleted = this.dao.deleteFeeGroup(Integer
				.parseInt(this.request.getParameter("feeGroupId")));
		if (!isDeleted) {
			this.addActionError(this
					.getText(Constant.I18N.ERROR_DELETE_CHILDREN_FIRST));
		}
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String deleteFeeMap() {
		this.feeDAO.deleteFee(Integer.parseInt(this.request
				.getParameter("feeId")));
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String edit() {
		this.feeGroup = this.dao.getFeeGroup(Integer.parseInt(this.request
				.getParameter("feeGroupId")));
		return Action.SUCCESS;
	}

	@Override
	public void validate() {
		if (StringUtil.isEmpty(this.feeGroup.getName())) {
			this.addFieldError("feeGroup.name",
					this.getText(Constant.I18N.ERROR_REQUIRED_FEEGROUP_NAME));
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
		this.feeGroups = this.dao.getFeeGroups();
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
