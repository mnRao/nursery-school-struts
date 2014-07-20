package com.duke.nurseryschool.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.duke.nurseryschool.action.core.CoreAction;
import com.duke.nurseryschool.generated.I18N;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.PaymentTrigger;
import com.duke.nurseryschool.hibernate.bean.AlternativeFeeMap;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.FeeMap;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.bean.embedded.PaymentFee;
import com.duke.nurseryschool.hibernate.dao.AlternativeFeeChargeMapDAO;
import com.duke.nurseryschool.hibernate.dao.FeeDAO;
import com.duke.nurseryschool.hibernate.dao.FeeMapDAO;
import com.duke.nurseryschool.hibernate.dao.MixedDAO;
import com.duke.nurseryschool.hibernate.dao.PaymentDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class AlternativeFeeMapAction extends CoreAction implements
		ModelDriven<AlternativeFeeMap>, Preparable {

	private static final long serialVersionUID = 9152332966087222436L;

	private AlternativeFeeMap alternativeFeeMap = new AlternativeFeeMap();
	private List<AlternativeFeeMap> alternativeFeeMaps = new ArrayList<AlternativeFeeMap>();
	private AlternativeFeeChargeMapDAO dao = new AlternativeFeeChargeMapDAO();

	private final PaymentDAO paymentDAO = new PaymentDAO();
	private final FeeDAO feeDAO = new FeeDAO();
	private final FeeMapDAO feeMapDAO = new FeeMapDAO();
	private final MixedDAO mixedDAO = new MixedDAO();

	private int paymentId;
	private int feeId;

	private List<Fee> feeList;
	private List<Payment> paymentList;
	private List<AlternativeFeeMap> alternativeFeeMapList;

	private String[] selectAlternativeFeeMap;

	@Override
	public AlternativeFeeMap getModel() {
		return this.alternativeFeeMap;
	}

	public String saveOrUpdate() {
		this.dao.getSession().evict(
				this.dao.getAlternativeFeeMap(Integer.parseInt(this.request
						.getParameter("paymentId")), Integer
						.parseInt(this.request.getParameter("feeId"))));

		Payment payment = this.paymentDAO.getPayment(this.paymentId);
		Fee fee = this.feeDAO.getFee(this.feeId);
		this.alternativeFeeMap.setPaymentFee(new PaymentFee(payment, fee));

		this.dao.saveOrUpdateAlternativeFeeMap(this.alternativeFeeMap);

		// Auto update payment's total fee
		this.triggerPaymentRecalculation();

		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String list() {
		return Action.SUCCESS;
	}

	@SkipValidation
	public String delete() {
		this.dao.deleteAlternativeFeeMap(this.paymentId, this.feeId);
		// Auto update payment's total fee
		this.triggerPaymentRecalculation();

		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	private void triggerPaymentRecalculation() {
		this.dao.getSession().flush();
		new PaymentTrigger(this.dao.getSession(),
				this.paymentDAO.getPayment(this.paymentId))
				.calculateAndSetTotalFee();
	}

	@SkipValidation
	public String edit() {
		this.alternativeFeeMap = this.dao.getAlternativeFeeMap(this.paymentId,
				this.feeId);
		return Action.SUCCESS;
	}

	@SkipValidation
	public String autoSetPayment() {
		return Action.SUCCESS;
	}

	@SkipValidation
	public String batchSaveOrUpdate() {
		Payment payment = this.paymentDAO.getPayment(this.paymentId);
		for (AlternativeFeeMap alternativeFeeMap : this.alternativeFeeMapList) {
			int feeId = alternativeFeeMap.getPaymentFee().getFee().getFeeId();

			// If is checked then save
			if (this.selectAlternativeFeeMap != null &&
					Arrays.asList(this.selectAlternativeFeeMap).contains(
					Integer.toString(feeId))) {
				this.dao.getSession().evict(
						this.dao.getAlternativeFeeMap(this.paymentId, feeId));

				PaymentFee paymentFee = new PaymentFee(payment,
						this.feeDAO.getFee(feeId));
				alternativeFeeMap.setPaymentFee(paymentFee);
				this.dao.saveOrUpdateAlternativeFeeMap(alternativeFeeMap);
			}
			else {// Else delete (if exists)
				this.dao.deleteAlternativeFeeMap(this.paymentId, feeId);
			}

			// Recalculate payment
			this.triggerPaymentRecalculation();
		}

		return Constant.ACTION_RESULT.BATCH_EDIT;
	}

	@SkipValidation
	public String autoBatchSetPayment() {
		this.feeList = this.feeDAO.getFees();
		this.alternativeFeeMapList = new ArrayList<AlternativeFeeMap>();

		Payment payment = this.paymentDAO.getPayment(this.paymentId);
		int feePolicyId = payment.getFeePolicy().getFeePolicyId();

		List<String> selectAlternativeFeeMapList = new ArrayList<String>();

		// For each fee, check whether already created alternative fee map
		Iterator<Fee> iterator = this.feeList.iterator();
		while (iterator.hasNext()) {
			Fee fee = iterator.next();
			// Choose only fee with existing fee map
			FeeMap feeMap = this.feeMapDAO.getFeeMap(fee.getFeeId(),
					feePolicyId);
			if (feeMap == null) {
				iterator.remove();
				continue;
			}

			AlternativeFeeMap alternativeFeeMap = this.mixedDAO
					.getAlternativeFeeMapByFeeIdAndFeePolicyId(fee.getFeeId(),
							this.paymentId);
			if (alternativeFeeMap == null) {
				alternativeFeeMap = new AlternativeFeeMap();

				PaymentFee paymentFee = new PaymentFee();
				paymentFee.setFee(fee);
				paymentFee.setPayment(this.paymentDAO
						.getPayment(this.paymentId));

				alternativeFeeMap.setPaymentFee(paymentFee);
			}
			else {
				selectAlternativeFeeMapList
						.add(Integer.toString(fee.getFeeId()));
			}
			this.alternativeFeeMapList.add(alternativeFeeMap);
		}
		// Cast list to string
		this.selectAlternativeFeeMap = new String[selectAlternativeFeeMapList
				.size()];
		for (int i = 0; i < selectAlternativeFeeMapList.size(); i++) {
			this.selectAlternativeFeeMap[i] = selectAlternativeFeeMapList
					.get(i);
		}

		return Constant.ACTION_RESULT.BATCH_EDIT;
	}

	@Override
	public void validate() {
		BigDecimal alternativeAmount = this.alternativeFeeMap
				.getAlternativeAmount();
		if (this.feeId == 0) {
			this.addFieldError("alternativeFeeMap.feeId",
					this.getText(I18N.ERROR_REQUIRED, new String[] {
						this.getText(I18N.LABEL_ALTERNATIVEFEEMAP_FEEID)
					}));
		}
		if (this.paymentId == 0) {
			this.addFieldError("alternativeFeeMap.paymentId",
					this.getText(I18N.ERROR_REQUIRED, new String[] {
						this.getText(I18N.LABEL_ALTERNATIVEFEEMAP_PAYMENTID)
					}));
		}
		// if (alternativeAmount != null && alternativeAmount.doubleValue() < 0)
		// {
		// this.addFieldError(
		// "alternativeFeeMap.alternativeAmount",
		// this.getText(I18N.ERROR_CONSTRAINT_ALTERNATIVEFEEMAP_ALTERNATIVEAMOUNT));
		// }

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

	public void prepareAutoSetPayment() throws Exception {
		this.populateData();
	}

	private void populateData() {
		this.alternativeFeeMaps = this.dao.getAlternativeFeeMaps();
		this.feeList = this.feeDAO.getFees();
		this.paymentList = this.paymentDAO.getPayments();
	}

	public AlternativeFeeMap getAlternativeFeeMap() {
		return this.alternativeFeeMap;
	}

	public void setAlternativeFeeMap(AlternativeFeeMap alternativeFeeMap) {
		this.alternativeFeeMap = alternativeFeeMap;
	}

	public List<AlternativeFeeMap> getAlternativeFeeMaps() {
		return this.alternativeFeeMaps;
	}

	public void setAlternativeFeeMaps(List<AlternativeFeeMap> alternativeFeeMaps) {
		this.alternativeFeeMaps = alternativeFeeMaps;
	}

	public AlternativeFeeChargeMapDAO getDao() {
		return this.dao;
	}

	public void setDao(AlternativeFeeChargeMapDAO dao) {
		this.dao = dao;
	}

	public int getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
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

	public List<Payment> getPaymentList() {
		return this.paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}

	public List<AlternativeFeeMap> getAlternativeFeeMapList() {
		return this.alternativeFeeMapList;
	}

	public void setAlternativeFeeMapList(
			List<AlternativeFeeMap> alternativeFeeMapList) {
		this.alternativeFeeMapList = alternativeFeeMapList;
	}

	public String[] getSelectAlternativeFeeMap() {
		return this.selectAlternativeFeeMap;
	}

	public void setSelectAlternativeFeeMap(String[] selectAlternativeFeeMap) {
		this.selectAlternativeFeeMap = selectAlternativeFeeMap;
	}

}
