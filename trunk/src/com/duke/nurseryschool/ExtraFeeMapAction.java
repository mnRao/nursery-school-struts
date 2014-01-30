package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.ExtraFeeMap;
import com.duke.nurseryschool.hibernate.bean.ExtraFeeType;
import com.duke.nurseryschool.hibernate.bean.FeeDetails;
import com.duke.nurseryschool.hibernate.bean.embedded.FeeDetailsExtraFee;
import com.duke.nurseryschool.hibernate.dao.ExtraFeeMapDAO;
import com.duke.nurseryschool.hibernate.dao.ExtraFeeTypeDAO;
import com.duke.nurseryschool.hibernate.dao.FeeDetailsDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class ExtraFeeMapAction extends CoreAction implements
		ModelDriven<ExtraFeeMap> {

	private ExtraFeeMap extraFeeMap = new ExtraFeeMap();
	private List<ExtraFeeMap> extraFeeMaps = new ArrayList<ExtraFeeMap>();
	private ExtraFeeMapDAO dao = new ExtraFeeMapDAO();

	private FeeDetailsDAO feeDetailsDAO = new FeeDetailsDAO();
	private ExtraFeeTypeDAO extraFeeTypeDAO = new ExtraFeeTypeDAO();

	private int feeDetailsId;
	private int extraFeeTypeId;

	private List<ExtraFeeType> extraFeeTypeList;
	private List<FeeDetails> feeDetailsList;

	@Override
	public ExtraFeeMap getModel() {
		return this.extraFeeMap;
	}

	public String saveOrUpdate() {
		FeeDetails feeDetails = this.feeDetailsDAO
				.getFeeDetails(this.feeDetailsId);
		ExtraFeeType extraFeeType = this.extraFeeTypeDAO
				.getExtraFeeType(this.extraFeeTypeId);
		this.extraFeeMap.setFeeDetailsExtraFee(new FeeDetailsExtraFee(
				feeDetails, extraFeeType));

		this.dao.saveOrUpdateExtraFeeMap(this.extraFeeMap);
		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.populateLists();

		this.extraFeeMaps = this.dao.getExtraFeeMaps();
		return Action.SUCCESS;
	}

	public String delete() {
		this.dao.deleteExtraFeeMap(this.extraFeeTypeId, this.feeDetailsId);

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		this.populateLists();

		this.extraFeeMap = this.dao.getExtraFeeMap(this.extraFeeTypeId,
				this.feeDetailsId);
		// Load all
		this.extraFeeMaps = this.dao.getExtraFeeMaps();
		return Action.SUCCESS;
	}

	private void populateLists() {
		// Populate class list
		this.extraFeeTypeList = this.extraFeeTypeDAO.getExtraFeeTypes();
		this.feeDetailsList = this.feeDetailsDAO.getFeeDetails();
	}

	public String autoSetFeeDetails() {
		return this.list();
	}

	public List<ExtraFeeMap> getExtraFeeMaps() {
		return this.extraFeeMaps;
	}

	public void setExtraFeeMaps(List<ExtraFeeMap> extraFeeMaps) {
		this.extraFeeMaps = extraFeeMaps;
	}

	public ExtraFeeMap getExtraFeeMap() {
		return this.extraFeeMap;
	}

	public void setExtraFeeMap(ExtraFeeMap extraFeeMap) {
		this.extraFeeMap = extraFeeMap;
	}

	public int getFeeDetailsId() {
		return this.feeDetailsId;
	}

	public void setFeeDetailsId(int feeDetailsId) {
		this.feeDetailsId = feeDetailsId;
	}

	public int getExtraId() {
		return this.extraFeeTypeId;
	}

	public void setExtraId(int extraId) {
		this.extraFeeTypeId = extraId;
	}

	public int getExtraFeeTypeId() {
		return this.extraFeeTypeId;
	}

	public void setExtraFeeTypeId(int extraFeeTypeId) {
		this.extraFeeTypeId = extraFeeTypeId;
	}

	public List<ExtraFeeType> getExtraFeeTypeList() {
		return this.extraFeeTypeList;
	}

	public void setExtraFeeTypeList(List<ExtraFeeType> extraFeeTypeList) {
		this.extraFeeTypeList = extraFeeTypeList;
	}

	public List<FeeDetails> getFeeDetailsList() {
		return this.feeDetailsList;
	}

	public void setFeeDetailsList(List<FeeDetails> feeDetailsList) {
		this.feeDetailsList = feeDetailsList;
	}

}
