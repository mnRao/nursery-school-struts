package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.FeeDetails;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.bean.Student;
import com.duke.nurseryschool.hibernate.bean.Subject;
import com.duke.nurseryschool.hibernate.bean.embedded.StudentFeeDetails;
import com.duke.nurseryschool.hibernate.bean.embedded.SubjectFee;
import com.duke.nurseryschool.hibernate.dao.FeeDetailsDAO;
import com.duke.nurseryschool.hibernate.dao.PaymentDAO;
import com.duke.nurseryschool.hibernate.dao.StudentDAO;
import com.duke.nurseryschool.hibernate.dao.SubjectDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class PaymentAction extends CoreAction implements ModelDriven<Payment> {

	private Payment payment = new Payment();
	private List<Payment> payments = new ArrayList<Payment>();
	private PaymentDAO dao = new PaymentDAO();

	private FeeDetailsDAO feeDetailsDAO = new FeeDetailsDAO();
	private StudentDAO studentDAO = new StudentDAO();

	private int feeDetailsId;
	private int studentId;

	private List<Student> studentList;
	private List<FeeDetails> feeDetailsList;

	@Override
	public Payment getModel() {
		return this.payment;
	}

	public String saveOrUpdate() {
		FeeDetails feeDetails = this.feeDetailsDAO
				.getFeeDetails(this.feeDetailsId);
		Student student = this.studentDAO.getStudent(this.studentId);
		this.payment.setStudentFeeDetails(new StudentFeeDetails(student,
				feeDetails));

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
		this.populateLists();

		this.payment = this.dao.getPayment(this.studentId, this.feeDetailsId);
		// Load all
		this.payments = this.dao.getPayments();
		return Action.SUCCESS;
	}

	private void populateLists() {
		// Populate class list
		this.studentList = this.studentDAO.getStudents();
		this.feeDetailsList = this.feeDetailsDAO.getFeeDetails();
	}

	public String generateExcel() {
		// TODO

		return this.list();
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

	public int getFeeDetailsId() {
		return this.feeDetailsId;
	}

	public void setFeeDetailsId(int feeDetailsId) {
		this.feeDetailsId = feeDetailsId;
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

	public List<FeeDetails> getFeeDetailsList() {
		return this.feeDetailsList;
	}

	public void setFeeDetailsList(List<FeeDetails> feeDetailsList) {
		this.feeDetailsList = feeDetailsList;
	}

}
