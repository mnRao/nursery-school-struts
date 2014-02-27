package com.duke.nurseryschool;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.classic.Session;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.PaymentTrigger;
import com.duke.nurseryschool.hibernate.HibernateUtil;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.dao.ClassesDAO;
import com.duke.nurseryschool.hibernate.dao.FeeMapDAO;
import com.duke.nurseryschool.hibernate.dao.FeePolicyDAO;
import com.duke.nurseryschool.hibernate.dao.MixedDAO;
import com.duke.nurseryschool.hibernate.dao.MonthDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class FeePolicyAction extends CoreAction implements
		ModelDriven<FeePolicy>, Preparable {

	private static final long serialVersionUID = -9145112354887960316L;

	private FeePolicy feePolicy = new FeePolicy();
	private List<FeePolicy> feePolicies = new ArrayList<FeePolicy>();
	private FeePolicyDAO dao = new FeePolicyDAO();
	private ClassesDAO classesDAO = new ClassesDAO();
	private MonthDAO monthDAO = new MonthDAO();
	private FeeMapDAO feeMapDAO = new FeeMapDAO();
	private MixedDAO mixedDAO = new MixedDAO();

	private int classId;
	private int monthId;

	private List<Classes> classList;
	private List<Month> monthList;

	@Override
	public FeePolicy getModel() {
		return this.feePolicy;
	}

	public String saveOrUpdate() {
		this.dao.getSession().evict(
				this.dao.getFeePolicy(Integer.parseInt(this.request
						.getParameter("feePolicyId"))));

		Classes associatedClass = this.classesDAO.getClasses(this.classId);
		Month month = this.monthDAO.getMonth(this.monthId);
		this.feePolicy.setAssociatedClass(associatedClass);
		this.feePolicy.setMonth(month);

		this.dao.saveOrUpdateFeePolicy(this.feePolicy);

		this.feePolicy = this.dao.getFeePolicy(Integer.parseInt(this.request
				.getParameter("feePolicyId")));
		// this.dao.getSession().refresh(this.feePolicy);
		// Load all payments for this fee policy
		// Session currentSession = HibernateUtil.getSessionFactory()
		// .openSession();
		List<Payment> relatedPayments = this.mixedDAO.getPaymentsByFeePolicy(
				this.mixedDAO.getSession(), this.feePolicy.getFeePolicyId());
		if (relatedPayments != null && relatedPayments.size() > 0) {
			// Set total fee save
			if (relatedPayments != null) {
				for (Payment payment : relatedPayments) {
					new PaymentTrigger(this.mixedDAO.getSession(), payment,
							this.feePolicy).calculateAndSetAll();
				}
			}
		}
		// this.dao.getTransaction().commit();

		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String list() {
		return Action.SUCCESS;
	}

	@SkipValidation
	public String delete() {
		String feePolicyId = this.request.getParameter("feePolicyId");
		boolean isDeleted = this.dao.deleteFeePolicy(Integer
				.parseInt(feePolicyId));
		if (!isDeleted) {
			this.addActionError(this
					.getText(Constant.I18N.ERROR_DELETE_CHILDREN_FIRST));
			return Action.SUCCESS;// Actually Error
		}

		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String deleteFeeMap() {
		this.feeMapDAO.deleteFeeMap(
				Integer.parseInt(this.request.getParameter("feeId")),
				Integer.parseInt(this.request.getParameter("feePolicyId")));
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String edit() {
		// Get params
		String feePolicyId = this.request.getParameter("feePolicyId");
		this.feePolicy = this.dao.getFeePolicy(Integer.parseInt(feePolicyId));

		this.classId = this.feePolicy.getAssociatedClass().getClassId();
		this.monthId = this.feePolicy.getMonth().getMonthId();
		return Action.SUCCESS;
	}

	/* Auto set classId (implied by getter/setter) */
	@SkipValidation
	public String autoSetClass() {
		return this.list();
	}

	@Override
	public void validate() {
		BigDecimal feePerNormalMeal = this.feePolicy.getFeePerNormalMeal();
		BigDecimal totalBreakfastFee = this.feePolicy.getTotalBreakfastFee();
		BigDecimal penaltyFeePerBreakfast = this.feePolicy
				.getPenaltyFeePerBreakfast();
		if (feePerNormalMeal == null) {
			this.addFieldError(
					"feePolicy.feePerNormalMeal",
					this.getText(Constant.I18N.ERROR_REQUIRED_FEEPOLICY_FEEPERNORMALMEAL));
		}
		if (totalBreakfastFee == null) {
			this.addFieldError(
					"feePolicy.totalBreakfastFee",
					this.getText(Constant.I18N.ERROR_REQUIRED_FEEPOLICY_TOTALBREAKFASTFEE));
		}
		if (penaltyFeePerBreakfast == null) {
			this.addFieldError(
					"feePolicy.penaltyFeePerBreakfast",
					this.getText(Constant.I18N.ERROR_REQUIRED_FEEPOLICY_PENALTYFEEPERBREAKFAST));
		}
		if (feePerNormalMeal != null && feePerNormalMeal.doubleValue() < 0) {
			this.addFieldError(
					"feePolicy.feePerNormalMeal",
					this.getText(Constant.I18N.ERROR_CONSTRAINT_FEEPOLICY_FEEPERNORMALMEAL));
		}
		if (totalBreakfastFee != null && totalBreakfastFee.doubleValue() < 0) {
			this.addFieldError(
					"feePolicy.totalBreakfastFee",
					this.getText(Constant.I18N.ERROR_CONSTRAINT_FEEPOLICY_TOTALBREAKFASTFEE));
		}
		if (penaltyFeePerBreakfast != null
				&& penaltyFeePerBreakfast.doubleValue() < 0) {
			this.addFieldError(
					"feePolicy.penaltyFeePerBreakfast",
					this.getText(Constant.I18N.ERROR_CONSTRAINT_FEEPOLICY_PENALTYFEEPERBREAKFAST));
		}
		if (this.feePolicy.getAvailableDays() <= 0) {
			this.addFieldError(
					"feePolicy",
					this.getText(Constant.I18N.ERROR_CONSTRAINT_FEEPOLICY_AVAILABLEDAYS));
		}
		// Check for uniqueness
		if (this.dao.hasDuplicates(this.feePolicy.getFeePolicyId(),
				this.classId, this.monthId)) {
			this.addFieldError("classId",
					this.getText(Constant.I18N.ERROR_DUPLICATION_FEEPOLICY));
		}

		super.validate();
	}

	@Override
	public void prepare() throws Exception {
		this.feePolicies = this.dao.getFeePolicies();
		this.classList = this.classesDAO.getClasses();
		this.monthList = this.monthDAO.getMonths();
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
	}

	public FeePolicy getFeePolicy() {
		return this.feePolicy;
	}

	public void setFeePolicy(FeePolicy feePolicy) {
		this.feePolicy = feePolicy;
	}

	public List<FeePolicy> getFeePolicies() {
		return this.feePolicies;
	}

	public void setFeePolicies(List<FeePolicy> feePolicies) {
		this.feePolicies = feePolicies;
	}

	public int getClassId() {
		return this.classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getMonthId() {
		return this.monthId;
	}

	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}

	public List<Classes> getClassList() {
		return this.classList;
	}

	public void setClassList(List<Classes> classList) {
		this.classList = classList;
	}

	public List<Month> getMonthList() {
		return this.monthList;
	}

	public void setMonthList(List<Month> monthList) {
		this.monthList = monthList;
	}

}
