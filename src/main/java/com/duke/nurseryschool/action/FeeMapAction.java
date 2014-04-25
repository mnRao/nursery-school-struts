package com.duke.nurseryschool.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.duke.nurseryschool.action.core.CoreAction;
import com.duke.nurseryschool.generated.I18N;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.PaymentTrigger;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.FeeMap;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.bean.embedded.FeePolicyFee;
import com.duke.nurseryschool.hibernate.dao.FeeDAO;
import com.duke.nurseryschool.hibernate.dao.FeeMapDAO;
import com.duke.nurseryschool.hibernate.dao.FeePolicyDAO;
import com.duke.nurseryschool.hibernate.dao.MixedDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class FeeMapAction extends CoreAction implements ModelDriven<FeeMap>,
		Preparable {

	private static final long serialVersionUID = 4032871480596984037L;

	private FeeMap feeMap = new FeeMap();
	private List<FeeMap> feeMaps = new ArrayList<FeeMap>();
	final private FeeMapDAO dao = new FeeMapDAO();

	final private FeePolicyDAO feePolicyDAO = new FeePolicyDAO();
	final private FeeDAO feeDAO = new FeeDAO();
	private final MixedDAO mixedDAO = new MixedDAO();

	private int feePolicyId;
	private int feeId;

	private List<Fee> feeList;
	private List<FeePolicy> feePolicyList;
	private List<FeeMap> feeMapList;

	private String[] selectFeeMap;

	@Override
	public FeeMap getModel() {
		return this.feeMap;
	}

	public String saveOrUpdate() {
		this.dao.getSession().evict(
				this.dao.getFeeMap(Integer.parseInt(this.request
						.getParameter("feeId")), Integer.parseInt(this.request
						.getParameter("feePolicyId"))));

		FeePolicy feePolicy = this.feePolicyDAO.getFeePolicy(this.feePolicyId);
		Fee fee = this.feeDAO.getFee(this.feeId);
		this.feeMap.setFeePolicyFee(new FeePolicyFee(feePolicy, fee));

		this.dao.saveOrUpdateFeeMap(this.feeMap);
		// Recalculate payment
		this.triggerPaymentRecalculation();

		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String list() {
		return Action.SUCCESS;
	}

	@SkipValidation
	public String delete() {
		this.dao.deleteFeeMap(this.feeId, this.feePolicyId);
		// Recalculate payment
		this.triggerPaymentRecalculation();

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String edit() {
		this.feeMap = this.dao.getFeeMap(this.feeId, this.feePolicyId);
		return Action.SUCCESS;
	}

	@SkipValidation
	public String autoSetFeePolicy() {
		return Action.SUCCESS;
	}

	@SkipValidation
	public String batchSaveOrUpdate() {
		FeePolicy feePolicy = this.feePolicyDAO.getFeePolicy(this.feePolicyId);
		for (FeeMap feeMap : this.feeMapList) {
			int feeId = feeMap.getFeePolicyFee().getFee().getFeeId();

			// If is checked then save
			if (Arrays.asList(this.selectFeeMap).contains(
					Integer.toString(feeId))) {
				this.dao.getSession().evict(
						this.dao.getFeeMap(feeId, this.feePolicyId));

				FeePolicyFee feePolicyFee = new FeePolicyFee(feePolicy,
						this.feeDAO.getFee(feeId));
				feeMap.setFeePolicyFee(feePolicyFee);
				this.dao.saveOrUpdateFeeMap(feeMap);
			}
			else {// Else delete (if exists)
				this.dao.deleteFeeMap(feeId, this.feePolicyId);
			}

			// Recalculate payment
			this.triggerPaymentRecalculation();
		}

		return Constant.ACTION_RESULT.BATCH_EDIT;
	}

	@SkipValidation
	public String autoBatchSetFeePolicy() {
		this.feeList = this.feeDAO.getFees();
		this.feeMapList = new ArrayList<FeeMap>();

		// For each fee, check whether already created fee map
		for (Fee fee : this.feeList) {
			FeeMap feeMap = this.mixedDAO.getFeeMapByFeeIdAndFeePolicyId(
					fee.getFeeId(), this.feePolicyId);
			if (feeMap == null) {
				feeMap = new FeeMap();

				FeePolicyFee feePolicyFee = new FeePolicyFee();
				feePolicyFee.setFee(fee);
				feePolicyFee.setFeePolicy(this.feePolicyDAO
						.getFeePolicy(this.feePolicyId));

				feeMap.setFeePolicyFee(feePolicyFee);
			}
			this.feeMapList.add(feeMap);
		}

		return Constant.ACTION_RESULT.BATCH_EDIT;
	}

	@Override
	public void validate() {
		BigDecimal amount = this.feeMap.getAmount();
		if (this.feeId == 0) {
			this.addFieldError("feeMap.feeId",
					this.getText(I18N.ERROR_REQUIRED, new String[] {
						this.getText(I18N.LABEL_FEEMAP_FEEID)
					}));
		}
		if (this.feePolicyId == 0) {
			this.addFieldError("feeMap.feePolicyId",
					this.getText(I18N.ERROR_REQUIRED, new String[] {
						this.getText(I18N.LABEL_FEEMAP_FEEPOLICYID)
					}));
		}
		if (amount == null) {
			this.addFieldError("feeMap.amount",
					this.getText(I18N.ERROR_REQUIRED, new String[] {
						this.getText(I18N.LABEL_FEEMAP_AMOUNT)
					}));
		}
		if (amount != null && amount.doubleValue() < 0) {
			this.addFieldError("feeMap.amount",
					this.getText(I18N.ERROR_CONSTRAINT_FEEMAP_AMOUNT));
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

	public void prepareAutoSetFeePolicy() throws Exception {
		this.populateData();
	}

	private void populateData() {
		this.feeMaps = this.dao.getFeeMaps();
		this.feeList = this.feeDAO.getFees();
		this.feePolicyList = this.feePolicyDAO.getFeePolicies();
	}

	private void triggerPaymentRecalculation() {
		this.dao.getSession().flush();
		// Load all payments for this fee policy
		List<Payment> relatedPayments = this.mixedDAO.getPaymentsByFeePolicy(
				this.dao.getSession(), this.feePolicyId);
		if (relatedPayments != null && relatedPayments.size() > 0) {
			// Set total fee save
			if (relatedPayments != null) {
				for (Payment payment : relatedPayments) {
					new PaymentTrigger(this.dao.getSession(), payment)
							.calculateAndSetTotalFee();
				}
			}
		}
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

	public List<FeeMap> getFeeMapList() {
		return this.feeMapList;
	}

	public void setFeeMapList(List<FeeMap> feeMapList) {
		this.feeMapList = feeMapList;
	}

	public String[] getSelectFeeMap() {
		return this.selectFeeMap;
	}

	public void setSelectFeeMap(String[] selectFeeMap) {
		this.selectFeeMap = selectFeeMap;
	}
}
