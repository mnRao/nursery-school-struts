package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.AlternativeFeeMap;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.bean.embedded.PaymentFee;
import com.duke.nurseryschool.hibernate.dao.AlternativeFeeChargeMapDAO;
import com.duke.nurseryschool.hibernate.dao.FeeDAO;
import com.duke.nurseryschool.hibernate.dao.PaymentDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class AlternativeFeeMapAction extends CoreAction implements
		ModelDriven<AlternativeFeeMap> {

	private static final long serialVersionUID = 9152332966087222436L;

	private AlternativeFeeMap alternativeFeeMap = new AlternativeFeeMap();
	private List<AlternativeFeeMap> alternativeFeeMaps = new ArrayList<AlternativeFeeMap>();
	private AlternativeFeeChargeMapDAO dao = new AlternativeFeeChargeMapDAO();

	private PaymentDAO paymentDAO = new PaymentDAO();
	private FeeDAO feeDAO = new FeeDAO();

	private int paymentId;
	private int feeId;

	private List<Fee> feeList;
	private List<Payment> paymentList;

	@Override
	public AlternativeFeeMap getModel() {
		return this.alternativeFeeMap;
	}

	public String saveOrUpdate() {
		Payment payment = this.paymentDAO.getPayment(this.paymentId);
		Fee fee = this.feeDAO.getFee(this.feeId);
		this.alternativeFeeMap.setPaymentFee(new PaymentFee(payment, fee));

		this.dao.saveOrUpdateAlternativeFeeMap(this.alternativeFeeMap);
		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.populateLists();

		this.alternativeFeeMaps = this.dao.getAlternativeFeeMaps();
		return Action.SUCCESS;
	}

	public String delete() {
		this.dao.deleteAlternativeFeeMap(this.paymentId, this.feeId);

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		this.populateLists();

		this.alternativeFeeMap = this.dao.getAlternativeFeeMap(this.paymentId,
				this.feeId);
		// Load all
		this.alternativeFeeMaps = this.dao.getAlternativeFeeMaps();
		return Action.SUCCESS;
	}

	private void populateLists() {
		// Populate class list
		this.feeList = this.feeDAO.getFees();
		this.paymentList = this.paymentDAO.getPayments();
	}

	public String autoSetPayment() {
		return this.list();
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

}
