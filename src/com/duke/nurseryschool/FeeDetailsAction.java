package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.Helper;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.FeeDetails;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.bean.embedded.ClassMonth;
import com.duke.nurseryschool.hibernate.dao.ClassesDAO;
import com.duke.nurseryschool.hibernate.dao.FeeDetailsDAO;
import com.duke.nurseryschool.hibernate.dao.MonthDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class FeeDetailsAction extends CoreAction implements
		ModelDriven<FeeDetails> {

	private static final long serialVersionUID = -9145112354887960316L;

	private FeeDetails feeDetail = new FeeDetails();
	private List<FeeDetails> feeDetails = new ArrayList<FeeDetails>();
	private FeeDetailsDAO dao = new FeeDetailsDAO();
	private ClassesDAO classesDAO = new ClassesDAO();
	private MonthDAO monthDAO = new MonthDAO();

	private int classId;
	private int monthId;

	@Override
	public FeeDetails getModel() {
		return this.feeDetail;
	}

	public String saveOrUpdate() {
		// Set class and month based on IDs
		Classes associatedClass = this.classesDAO.getClasses(this.classId);
		Month month = this.monthDAO.getMonth(this.monthId);
		this.feeDetail.setAssociatedClass(associatedClass);
		this.feeDetail.setMonth(month);

		this.dao.saveOrUpdateFeeDetails(this.feeDetail);
		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.feeDetails = this.dao.getFeeDetails();
		return Action.SUCCESS;
	}

	public String delete() {
		// Get params
		String feeDetailsId = this.request.getParameter("feeDetailsId");

		this.dao.deleteFeeDetails(Integer.parseInt(feeDetailsId));
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		// Get params
		String feeDetailsId = this.request.getParameter("feeDetailsId");
		this.feeDetail = this.dao.getFeeDetails(Integer.parseInt(feeDetailsId));
		this.classId = this.feeDetail.getAssociatedClass().getClassId();
		this.monthId = this.feeDetail.getMonth().getMonthId();

		// Load all
		this.feeDetails = this.dao.getFeeDetails();
		return Action.SUCCESS;
	}

	public List<FeeDetails> getFeeDetails() {
		return this.feeDetails;
	}

	public void setFeeDetails(List<FeeDetails> feeDetailss) {
		this.feeDetails = feeDetailss;
	}

	public FeeDetails getFeeDetail() {
		return this.feeDetail;
	}

	public void setFeeDetails(FeeDetails feeDetail) {
		this.feeDetail = feeDetail;
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

}
