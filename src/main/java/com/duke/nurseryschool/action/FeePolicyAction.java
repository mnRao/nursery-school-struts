package com.duke.nurseryschool.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.duke.nurseryschool.action.core.CoreAction;
import com.duke.nurseryschool.generated.I18N;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.PaymentTrigger;
import com.duke.nurseryschool.hibernate.bean.AlternativeFeeMap;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.FeeMap;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.dao.AlternativeFeeChargeMapDAO;
import com.duke.nurseryschool.hibernate.dao.ClassesDAO;
import com.duke.nurseryschool.hibernate.dao.FeeMapDAO;
import com.duke.nurseryschool.hibernate.dao.FeePolicyDAO;
import com.duke.nurseryschool.hibernate.dao.MixedDAO;
import com.duke.nurseryschool.hibernate.dao.MonthDAO;
import com.duke.nurseryschool.hibernate.dao.PaymentDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class FeePolicyAction extends CoreAction implements ModelDriven<FeePolicy>, Preparable {

	private static final long serialVersionUID = -9145112354887960316L;

	private FeePolicy feePolicy = new FeePolicy();
	private List<FeePolicy> feePolicies = new ArrayList<FeePolicy>();
	private final FeePolicyDAO dao = new FeePolicyDAO();
	private final ClassesDAO classesDAO = new ClassesDAO();
	private final MonthDAO monthDAO = new MonthDAO();
	private final FeeMapDAO feeMapDAO = new FeeMapDAO();
	private final PaymentDAO paymentDAO = new PaymentDAO();
	private final AlternativeFeeChargeMapDAO altFeeMapDAO = new AlternativeFeeChargeMapDAO();
	private final MixedDAO mixedDAO = new MixedDAO();

	private int classId;
	private int monthId;
	private int feePolicyId;

	private List<Classes> classList;
	private List<Month> monthList;
	private List<Fee> feeList;

	private int feePolicyIdToClone;

	@Override
	public FeePolicy getModel() {
		return this.feePolicy;
	}

	public String saveOrUpdate() {
		int feePolicyIdParam = Integer.parseInt(this.request.getParameter("feePolicyId"));
		this.dao.getSession().evict(this.dao.getFeePolicy(feePolicyIdParam));

		Classes associatedClass = this.classesDAO.getClasses(this.classId);
		Month month = this.monthDAO.getMonth(this.monthId);
		this.feePolicy.setAssociatedClass(associatedClass);
		this.feePolicy.setMonth(month);

		this.dao.saveOrUpdateFeePolicy(this.feePolicy);

		// If is update action
		if (feePolicyIdParam != 0) {
			// Update payment instantly
			this.feePolicy = this.dao.getFeePolicy(feePolicyIdParam);
			// Load all payments for this fee policy
			List<Payment> relatedPayments = this.mixedDAO.getPaymentsByFeePolicy(this.mixedDAO.getSession(), this.feePolicy.getFeePolicyId());
			if (relatedPayments != null && relatedPayments.size() > 0) {
				// Set total fee save
				for (Payment payment : relatedPayments) {
					new PaymentTrigger(this.mixedDAO.getSession(), payment, this.feePolicy).calculateAndSetAll();
				}
			}
		}

		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String list() {
		return Action.SUCCESS;
	}

	@SkipValidation
	public String delete() {
		String feePolicyId = this.request.getParameter("feePolicyId");
		boolean isDeleted = this.dao.deleteFeePolicy(Integer.parseInt(feePolicyId));
		if (!isDeleted) {
			this.addActionError(this.getText(I18N.ERROR_DELETE_CHILDREN_FIRST));
			// Populate data
			this.populateData();

			return Action.SUCCESS;// Actually Error
		}

		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String deleteFeeMap() {
		this.feeMapDAO.deleteFeeMap(Integer.parseInt(this.request.getParameter("feeId")), Integer.parseInt(this.request.getParameter("feePolicyId")));
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String edit() {
		this.feePolicy = this.dao.getFeePolicy(this.feePolicyId);

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
	@SkipValidation
	public String clone() {
		this.feePolicyIdToClone = Integer.parseInt(this.request.getParameter("feePolicyId"));

		return Constant.ACTION_RESULT.CLONE;
	}

	@SkipValidation
	public String cloneAll() {
		this.feePolicyIdToClone = Integer.parseInt(this.request.getParameter("feePolicyId"));

		// Only allow clone-all option for the same class (different month)
		FeePolicy feePolicy = this.dao.getFeePolicy(this.feePolicyIdToClone);
		List<Classes> classList = new ArrayList<Classes>();
		classList.add(this.classesDAO.getClasses(feePolicy.getAssociatedClass().getClassId()));
		this.classList = classList;

		return Constant.ACTION_RESULT.CLONE_ALL;
	}

	@SkipValidation
	public String performClone() throws CloneNotSupportedException {
		FeePolicy feePolicyToClone = this.dao.getFeePolicy(Integer.parseInt(this.request.getParameter("feePolicyIdToClone")));
		Classes newAssociatedClass = this.classesDAO.getClasses(this.classId);
		Month newMonth = this.monthDAO.getMonth(this.monthId);

		// TODO Clone
		FeePolicy newFeePolicy = feePolicyToClone.clone(newAssociatedClass, newMonth);
		Set<FeeMap> newFeeMaps = feePolicyToClone.cloneFeeMaps(newFeePolicy);

		// Validate then save
		boolean isUnique = this.checkUniqueness();
		if (!isUnique) {
			return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
		}

		this.dao.saveOrUpdateFeePolicy(newFeePolicy);
		this.dao.getSession().flush();
		for (FeeMap newFeeMap : newFeeMaps) {
			this.feeMapDAO.saveOrUpdateFeeMap(newFeeMap);
		}

		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String performCloneAll() throws CloneNotSupportedException {
		FeePolicy feePolicyToClone = this.dao.getFeePolicy(Integer.parseInt(this.request.getParameter("feePolicyIdToClone")));
		Classes newAssociatedClass = this.classesDAO.getClasses(this.classId);
		Month newMonth = this.monthDAO.getMonth(this.monthId);

		// TODO Clone
		FeePolicy newFeePolicy = feePolicyToClone.clone(newAssociatedClass, newMonth);
		// Validate & save fee policy
		boolean isUnique = this.checkUniqueness(newFeePolicy.getFeePolicyId(), newAssociatedClass.getClassId(), newMonth.getMonthId());
		if (!isUnique) {
			return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
		}

		this.dao.saveOrUpdateFeePolicy(newFeePolicy);
		this.dao.getSession().flush();

		Set<FeeMap> newFeeMaps = feePolicyToClone.cloneFeeMaps(newFeePolicy);
		// Save new fee maps
		for (FeeMap newFeeMap : newFeeMaps) {
			this.feeMapDAO.saveOrUpdateFeeMap(newFeeMap);
		}
		this.dao.getSession().flush();

		Set<Payment> newPayments = feePolicyToClone.clonePayments(newFeePolicy);
		// Combine alternative fee map sets
		Set<AlternativeFeeMap> allAlternativeFeeMaps = new HashSet<AlternativeFeeMap>();
		for (Payment newPayment : newPayments) {
			allAlternativeFeeMaps.addAll(newPayment.getAlternativeFeeMaps());
		}
		// Save payments
		for (Payment newPayment : newPayments) {
			// Set null to add later
			newPayment.setAlternativeFeeMaps(null);
			this.paymentDAO.saveOrUpdatePayment(newPayment);
			this.dao.getSession().flush();
		}
		this.dao.getSession().flush();

		// Save alternative fee maps
		for (AlternativeFeeMap newAltFeeMap : allAlternativeFeeMaps) {
			this.altFeeMapDAO.saveOrUpdateAlternativeFeeMap(newAltFeeMap);
		}

		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@Override
	public void validate() {
		BigDecimal feePerNormalMeal = this.feePolicy.getFeePerNormalMeal();
		BigDecimal totalBreakfastFee = this.feePolicy.getTotalBreakfastFee();
		BigDecimal penaltyFeePerBreakfast = this.feePolicy.getPenaltyFeePerBreakfast();
		if (this.classId == 0) {
			this.addFieldError("feePolicy.classId", this.getText(I18N.ERROR_REQUIRED, new String[] {
				this.getText(I18N.LABEL_FEEPOLICY_CLASSID)
			}));
		}
		if (this.monthId == 0) {
			this.addFieldError("feePolicy.monthId", this.getText(I18N.ERROR_REQUIRED, new String[] {
				this.getText(I18N.LABEL_FEEPOLICY_MONTHID)
			}));
		}
		if (feePerNormalMeal == null) {
			this.addFieldError("feePolicy.feePerNormalMeal", this.getText(I18N.ERROR_REQUIRED, new String[] {
				this.getText(I18N.LABEL_FEEPOLICY_FEEPERNORMALMEAL)
			}));
		}
		if (totalBreakfastFee == null) {
			this.addFieldError("feePolicy.totalBreakfastFee", this.getText(I18N.ERROR_REQUIRED, new String[] {
				this.getText(I18N.LABEL_FEEPOLICY_TOTALBREAKFASTFEE)
			}));
		}
		if (penaltyFeePerBreakfast == null) {
			this.addFieldError("feePolicy.penaltyFeePerBreakfast", this.getText(I18N.ERROR_REQUIRED, new String[] {
				this.getText(I18N.LABEL_FEEPOLICY_PENALTYFEEPERBREAKFAST)
			}));
		}
		if (feePerNormalMeal != null && feePerNormalMeal.doubleValue() < 0) {
			this.addFieldError("feePolicy.feePerNormalMeal", this.getText(I18N.ERROR_CONSTRAINT_FEEPOLICY_FEEPERNORMALMEAL));
		}
		if (totalBreakfastFee != null && totalBreakfastFee.doubleValue() < 0) {
			this.addFieldError("feePolicy.totalBreakfastFee", this.getText(I18N.ERROR_CONSTRAINT_FEEPOLICY_TOTALBREAKFASTFEE));
		}
		if (penaltyFeePerBreakfast != null && penaltyFeePerBreakfast.doubleValue() < 0) {
			this.addFieldError("feePolicy.penaltyFeePerBreakfast", this.getText(I18N.ERROR_CONSTRAINT_FEEPOLICY_PENALTYFEEPERBREAKFAST));
		}
		if (this.feePolicy.getAvailableDays() <= 0) {
			this.addFieldError("feePolicy", this.getText(I18N.ERROR_CONSTRAINT_FEEPOLICY_AVAILABLEDAYS));
		}
		// Check for uniqueness
		this.checkUniqueness();

		super.validate();
	}

	private boolean checkUniqueness(int feePolicyId, int classId, int monthId) {
		if (this.dao.hasDuplicates(feePolicyId, this.classId, this.monthId)) {
			this.addFieldError("classId", this.getText(I18N.ERROR_DUPLICATION_FEEPOLICY));
			return false;
		}

		return true;
	}

	private boolean checkUniqueness() {
		return this.checkUniqueness(this.feePolicy.getFeePolicyId(), this.classId, this.monthId);
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

	public void prepareAutoSetClass() throws Exception {
		this.populateData();
	}

	public void prepareClone() throws Exception {
		this.populateData();
	}

	public void prepareCloneAll() throws Exception {
		this.monthList = this.monthDAO.getMonths();
	}

	private void populateData() {
		this.feePolicies = this.dao.getFeePolicies();
		this.classList = this.classesDAO.getClasses();
		this.monthList = this.monthDAO.getMonths();
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

	public int getFeePolicyIdToClone() {
		return this.feePolicyIdToClone;
	}

	public void setFeePolicyIdToClone(int feePolicyIdToClone) {
		this.feePolicyIdToClone = feePolicyIdToClone;
	}

	public List<Fee> getFeeList() {
		return this.feeList;
	}

	public void setFeeList(List<Fee> feeList) {
		this.feeList = feeList;
	}

	public int getFeePolicyId() {
		return this.feePolicyId;
	}

	public void setFeePolicyId(int feePolicyId) {
		this.feePolicyId = feePolicyId;
	}

}
