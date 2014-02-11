package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.FeeMap;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.embedded.FeePolicyFee;
import com.duke.nurseryschool.hibernate.dao.FeeDAO;
import com.duke.nurseryschool.hibernate.dao.FeeMapDAO;
import com.duke.nurseryschool.hibernate.dao.FeePolicyDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class FeeMapAction extends CoreAction implements ModelDriven<FeeMap> {

	private FeeMap feeMap = new FeeMap();
	private List<FeeMap> feeMaps = new ArrayList<FeeMap>();
	final private FeeMapDAO dao = new FeeMapDAO();

	final private FeePolicyDAO feePolicyDAO = new FeePolicyDAO();
	final private FeeDAO feeDAO = new FeeDAO();

	private int feePolicyId;
	private int feeId;

	private List<Fee> feeList;
	private List<FeePolicy> feePolicyList;

	@Override
	public FeeMap getModel() {
		return this.feeMap;
	}

	public String saveOrUpdate() {
		FeePolicy feePolicy = this.feePolicyDAO.getFeePolicy(this.feePolicyId);
		Fee fee = this.feeDAO.getFee(this.feeId);
		this.feeMap.setFeePolicyFee(new FeePolicyFee(feePolicy, fee));

		this.dao.saveOrUpdateFeeMap(this.feeMap);
		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.populateLists();

		this.feeMaps = this.dao.getFeeMaps();
		return Action.SUCCESS;
	}

	public String delete() {
		this.dao.deleteFeeMap(this.feeId, this.feePolicyId);

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		this.populateLists();

		this.feeMap = this.dao.getFeeMap(this.feeId, this.feePolicyId);
		// Load all
		this.feeMaps = this.dao.getFeeMaps();
		return Action.SUCCESS;
	}

	private void populateLists() {
		// Populate class list
		this.feeList = this.feeDAO.getFees();
		this.feePolicyList = this.feePolicyDAO.getFeePolicies();
	}

	public String autoSetFeePolicy() {
		return this.list();
	}

	public FeeMap getFeeMap() {
		return this.feeMap;
	}

	public void setFeeMap(FeeMap feeMap) {
		this.feeMap = feeMap;
	}

	public List<FeeMap> getFeeMaps() {
		return this.feeMaps;
	}

	public void setFeeMaps(List<FeeMap> feeMaps) {
		this.feeMaps = feeMaps;
	}

	public int getFeePolicyId() {
		return this.feePolicyId;
	}

	public void setFeePolicyId(int feePolicyId) {
		this.feePolicyId = feePolicyId;
	}

	public int getFeeId() {
		return this.feeId;
	}

	public void setFeeId(int feeId) {
		this.feeId = feeId;
	}

	public List<Fee> getFeeList() {
		return this.feeList;
	}

	public void setFeeList(List<Fee> feeList) {
		this.feeList = feeList;
	}

	public List<FeePolicy> getFeePolicyList() {
		return this.feePolicyList;
	}

	public void setFeePolicyList(List<FeePolicy> feePolicyList) {
		this.feePolicyList = feePolicyList;
	}

}
