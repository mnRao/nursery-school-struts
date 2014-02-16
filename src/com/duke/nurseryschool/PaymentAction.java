package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.comparator.StudentComparator;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.bean.Student;
import com.duke.nurseryschool.hibernate.dao.FeePolicyDAO;
import com.duke.nurseryschool.hibernate.dao.PaymentDAO;
import com.duke.nurseryschool.hibernate.dao.StudentDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class PaymentAction extends CoreAction implements ModelDriven<Payment> {

	private Payment payment = new Payment();
	private List<Payment> payments = new ArrayList<Payment>();
	final private PaymentDAO dao = new PaymentDAO();

	final private FeePolicyDAO feePolicyDAO = new FeePolicyDAO();
	final private StudentDAO studentDAO = new StudentDAO();

	private int feePolicyId;
	private int studentId;

	private List<Student> studentList;
	private List<FeePolicy> feePolicyList;

	@Override
	public Payment getModel() {
		return this.payment;
	}

	public String saveOrUpdate() {
		FeePolicy feePolicy = this.feePolicyDAO.getFeePolicy(this.feePolicyId);
		Student student = this.studentDAO.getStudent(this.studentId);
		this.payment.setFeePolicy(feePolicy);
		this.payment.setStudent(student);

		this.dao.saveOrUpdatePayment(this.payment);
		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.populateLists();

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
		this.feePolicyId = this.payment.getFeePolicy().getFeePolicyId();
		this.studentId = this.payment.getStudent().getStudentId();

		this.populateLists();
		// Load all
		this.payments = this.dao.getPayments();
		return Action.SUCCESS;
	}

	private void populateLists() {
		// String feePolicyIdText = this.request.getParameter("feePolicyId");
		// if (feePolicyIdText != null) {
		// this.feePolicyId = Integer.parseInt(feePolicyIdText);
		// }
		// else {
		// this.feePolicyId = 0;
		// }

		// Populate class list
		this.feePolicyList = this.feePolicyDAO.getFeePolicies();

		if (this.feePolicyId != 0) {
			FeePolicy feePolicy = this.feePolicyDAO
					.getFeePolicy(this.feePolicyId);
			if (feePolicy != null)
				this.studentList = new ArrayList<Student>();
			this.studentList.addAll(feePolicy.getAssociatedClass()
					.getStudents());

			// Sort
			Collections.sort(this.studentList, new StudentComparator());
		}
		else {
			this.studentList = this.studentDAO.getStudents();
		}
	}

	public String autoSetFeePolicy() {
		return this.list();
	}

	public String generateExcel() {
		// TODO

		return this.list();
	}

	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public List<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public int getFeePolicyId() {
		return this.feePolicyId;
	}

	public void setFeePolicyId(int feePolicyId) {
		this.feePolicyId = feePolicyId;
	}

	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public List<Student> getStudentList() {
		return this.studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<FeePolicy> getFeePolicyList() {
		return this.feePolicyList;
	}

	public void setFeePolicyList(List<FeePolicy> feePolicyList) {
		this.feePolicyList = feePolicyList;
	}

}