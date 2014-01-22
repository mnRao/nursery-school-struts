package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.dao.PaymentDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class PaymentAction extends CoreAction implements ModelDriven<Payment> {

	private Payment payment = new Payment();
	private List<Payment> payments = new ArrayList<Payment>();
	private PaymentDAO dao = new PaymentDAO();

	@Override
	public Payment getModel() {
		return this.payment;
	}

	public String saveOrUpdate() {
		this.dao.saveOrUpdatePayment(this.payment);
		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.payments = this.dao.getPayments();
		return Action.SUCCESS;
	}

	public String delete() {
		this.dao.deletePayment(Integer.parseInt(this.request
				.getParameter("paymentId")));
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		this.payment = this.dao.getPayment(Integer.parseInt(this.request
				.getParameter("paymentId")));
		// Load all
		this.payments = this.dao.getPayments();
		return Action.SUCCESS;
	}

	public List<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
