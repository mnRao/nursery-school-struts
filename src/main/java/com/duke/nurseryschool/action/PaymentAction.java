package com.duke.nurseryschool.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.duke.nurseryschool.action.core.CoreAction;
import com.duke.nurseryschool.generated.I18N;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.PaymentTrigger;
import com.duke.nurseryschool.helper.comparator.StudentComparator;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.bean.Student;
import com.duke.nurseryschool.hibernate.dao.FeePolicyDAO;
import com.duke.nurseryschool.hibernate.dao.MixedDAO;
import com.duke.nurseryschool.hibernate.dao.PaymentDAO;
import com.duke.nurseryschool.hibernate.dao.StudentDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PaymentAction extends CoreAction implements ModelDriven<Payment>, Preparable {
	private static final long serialVersionUID = 8722338141340223665L;

	private Payment payment = new Payment();
	private List<Payment> payments = new ArrayList<Payment>();
	final private PaymentDAO dao = new PaymentDAO();

	final private FeePolicyDAO feePolicyDAO = new FeePolicyDAO();
	final private StudentDAO studentDAO = new StudentDAO();
	private final MixedDAO mixedDAO = new MixedDAO();

	private int feePolicyId;
	private int studentId;

	private List<Student> studentList;
	private List<FeePolicy> feePolicyList;
	private List<Payment> paymentList;

	@Override
	public Payment getModel() {
		return this.payment;
	}

	public String saveOrUpdate() {
		this.dao.getSession().evict(this.dao.getPayment(Integer.parseInt(this.request.getParameter("paymentId"))));

		FeePolicy feePolicy = this.feePolicyDAO.getFeePolicy(this.feePolicyId);
		Student student = this.studentDAO.getStudent(this.studentId);
		this.payment.setFeePolicy(feePolicy);
		this.payment.setStudent(student);

		this.dao.saveOrUpdatePayment(this.payment);
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String batchSaveOrUpdate() {
		FeePolicy feePolicy = this.feePolicyDAO.getFeePolicy(this.feePolicyId);
		for (Payment payment : this.paymentList) {
			this.dao.getSession().evict(this.dao.getPayment(payment.getPaymentId()));
			payment.setFeePolicy(feePolicy);
			this.dao.saveOrUpdatePayment(payment);
		}

		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String list() {
		return Action.SUCCESS;
	}

	@SkipValidation
	public String delete() {
		boolean isDeleted = this.dao.deletePayment(Integer.parseInt(this.request.getParameter("paymentId")));

		if (!isDeleted) {
			this.addActionError(this.getText(I18N.ERROR_DELETE_CHILDREN_FIRST));
			// Populate data
			this.populateData();

			return Action.SUCCESS;// Actually Error
		}

		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String edit() {
		this.payment = this.dao.getPayment(Integer.parseInt(this.request.getParameter("paymentId")));
		this.feePolicyId = this.payment.getFeePolicy().getFeePolicyId();
		this.studentId = this.payment.getStudent().getStudentId();

		return Action.SUCCESS;
	}

	@SkipValidation
	public String autoSetFeePolicy() {
		return this.list();
	}

	/**
	 * Batch create payments for all students related to a fee policy
	 */
	@SkipValidation
	public String autoBatchSetFeePolicy() {
		this.populateStudentList();

		this.paymentList = new ArrayList<Payment>();
		// For each student, check whether already created Payment
		// If not yet then create
		for (Student student : this.studentList) {
			Payment payment = this.mixedDAO.getPaymentByStudentIdAndFeePolicyId(student.getStudentId(), this.feePolicyId);
			if (payment == null) {
				payment = new Payment();
				payment.setStudent(student);
				payment.setFeePolicy(this.feePolicyDAO.getFeePolicy(this.feePolicyId));
			}
			this.paymentList.add(payment);
		}

		return Constant.ACTION_RESULT.BATCH_EDIT;
	}

	@Override
	public void validate() {
		if (this.studentId == 0) {
			this.addFieldError("payment.studentId", this.getText(I18N.ERROR_REQUIRED, new String[] {
				this.getText(I18N.LABEL_PAYMENT_STUDENTID)
			}));
		}
		if (this.feePolicyId == 0) {
			this.addFieldError("payment.feePolicyId", this.getText(I18N.ERROR_REQUIRED, new String[] {
				this.getText(I18N.LABEL_PAYMENT_FEEPOLICYID)
			}));
		}
		if (this.payment.getAbsenceCount() < 0) {
			this.addFieldError("payment.absenceCount", this.getText(I18N.ERROR_CONSTRAINT_PAYMENT_ABSENCECOUNT));
		}
		// Check for uniqueness
		if (this.dao.hasDuplicates(this.payment.getPaymentId(), this.studentId, this.feePolicyId)) {
			this.addFieldError("payment.studentId", this.getText(I18N.ERROR_DUPLICATION_PAYMENT));
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

	public void prepareBatchSaveOrUpdate() throws Exception {
		this.populateData();
	}

	public void prepareAutoSetFeePolicy() throws Exception {
		this.populateData();
	}

	public void prepareBatchAutoSetFeePolicy() throws Exception {
		this.populateData();
	}

	/* If fee policy is pre-specified, populate student list for that class only */
	private void populateData() {
		this.payments = this.dao.getPayments();
		this.feePolicyList = this.feePolicyDAO.getFeePolicies();

		this.populateStudentList();
	}

	private void populateStudentList() {
		if (this.feePolicyId != 0) {
			FeePolicy feePolicy = this.feePolicyDAO.getFeePolicy(this.feePolicyId);
			if (feePolicy != null) {
				this.studentList = new ArrayList<Student>();
				this.studentList.addAll(feePolicy.getAssociatedClass().getActiveStudents());
			}

			// Sort
			Collections.sort(this.studentList, new StudentComparator());
		}
		else {
			this.studentList = this.studentDAO.getActiveStudents();
		}
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

	public List<Payment> getPaymentList() {
		return this.paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}

}
