package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.bean.embedded.ClassMonth;
import com.duke.nurseryschool.hibernate.dao.ClassesDAO;
import com.duke.nurseryschool.hibernate.dao.FeePolicyDAO;
import com.duke.nurseryschool.hibernate.dao.MonthDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class FeePolicyAction extends CoreAction implements
		ModelDriven<FeePolicy> {

	private static final long serialVersionUID = -9145112354887960316L;

	private FeePolicy feePolicy = new FeePolicy();
	private List<FeePolicy> feePolicies = new ArrayList<FeePolicy>();
	private FeePolicyDAO dao = new FeePolicyDAO();
	private ClassesDAO classesDAO = new ClassesDAO();
	private MonthDAO monthDAO = new MonthDAO();

	private int classId;
	private int monthId;

	private List<Classes> classList;
	private List<Month> monthList;

	@Override
	public FeePolicy getModel() {
		return this.feePolicy;
	}

	public String saveOrUpdate() {
		Classes associatedClass = this.classesDAO.getClasses(this.classId);
		Month month = this.monthDAO.getMonth(this.monthId);
		this.feePolicy.setClassMonth(new ClassMonth(associatedClass, month));

		this.dao.saveOrUpdateFeePolicy(this.feePolicy);
		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.populateClassAndMonthLists();

		this.feePolicies = this.dao.getFeePolicies();
		return Action.SUCCESS;
	}

	public String delete() {
		// Get params
		String classId = this.request.getParameter("classId");
		String monthId = this.request.getParameter("monthId");

		this.dao.deleteFeePolicy(Integer.parseInt(classId),
				Integer.parseInt(monthId));
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		this.populateClassAndMonthLists();

		// Get params
		String classId = this.request.getParameter("classId");
		String monthId = this.request.getParameter("monthId");
		this.feePolicy = this.dao.getFeePolicy(Integer.parseInt(classId),
				Integer.parseInt(monthId));

		// Load all
		this.feePolicies = this.dao.getFeePolicies();
		return Action.SUCCESS;
	}

	private void populateClassAndMonthLists() {
		// Populate class list
		this.classList = this.classesDAO.getClasses();
		this.monthList = this.monthDAO.getMonths();
	}

	/* Auto set classId (implied by getter/setter) */
	public String autoSetClass() {
		return this.list();
	}

	public List<FeePolicy> getFeePolicies() {
		return this.feePolicies;
	}

	public void setFeePolicies(List<FeePolicy> feePolicys) {
		this.feePolicies = feePolicys;
	}

	public FeePolicy getFeePolicy() {
		return this.feePolicy;
	}

	public void setFeePolicy(FeePolicy feePolicy) {
		this.feePolicy = feePolicy;
	}

	public ClassMonth getClassMonth() {
		return this.feePolicy.getClassMonth();
	}

	public void setClassMonth(ClassMonth classMonth) {
		this.feePolicy.setClassMonth(classMonth);
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
