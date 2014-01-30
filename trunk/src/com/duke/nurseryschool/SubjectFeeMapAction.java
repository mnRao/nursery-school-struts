package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.BusinessLogicSolver;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.FeeDetails;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.bean.Subject;
import com.duke.nurseryschool.hibernate.bean.SubjectFeeMap;
import com.duke.nurseryschool.hibernate.bean.embedded.ClassMonth;
import com.duke.nurseryschool.hibernate.bean.embedded.SubjectFee;
import com.duke.nurseryschool.hibernate.dao.FeeDetailsDAO;
import com.duke.nurseryschool.hibernate.dao.SubjectDAO;
import com.duke.nurseryschool.hibernate.dao.SubjectFeeMapDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class SubjectFeeMapAction extends CoreAction implements
		ModelDriven<SubjectFeeMap> {

	private SubjectFeeMap subjectFeeMap = new SubjectFeeMap();
	private List<SubjectFeeMap> subjectFeeMaps = new ArrayList<SubjectFeeMap>();
	private SubjectFeeMapDAO dao = new SubjectFeeMapDAO();

	private FeeDetailsDAO feeDetailsDAO = new FeeDetailsDAO();
	private SubjectDAO subjectDAO = new SubjectDAO();

	private int feeDetailsId;
	private int subjectId;

	private List<Subject> subjectList;
	private List<FeeDetails> feeDetailsList;

	@Override
	public SubjectFeeMap getModel() {
		return this.subjectFeeMap;
	}

	public String saveOrUpdate() {
		FeeDetails feeDetails = this.feeDetailsDAO
				.getFeeDetails(this.feeDetailsId);
		Subject subject = this.subjectDAO.getSubject(this.subjectId);
		this.subjectFeeMap.setSubjectFee(new SubjectFee(feeDetails, subject));

		this.dao.saveOrUpdateSubjectFeeMap(this.subjectFeeMap);
		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.populateLists();

		this.subjectFeeMaps = this.dao.getSubjectFeeMaps();
		return Action.SUCCESS;
	}

	public String delete() {
		this.dao.deleteSubjectFeeMap(this.subjectId, this.feeDetailsId);

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		this.populateLists();

		this.subjectFeeMap = this.dao.getSubjectFeeMap(this.subjectId,
				this.feeDetailsId);
		// Load all
		this.subjectFeeMaps = this.dao.getSubjectFeeMaps();
		return Action.SUCCESS;
	}

	private void populateLists() {
		// Populate class list
		this.subjectList = this.subjectDAO.getSubjects();
		this.feeDetailsList = this.feeDetailsDAO.getFeeDetails();
	}

	public String autoSetFeeDetails() {
		return this.list();
	}

	public List<SubjectFeeMap> getSubjectFeeMaps() {
		return this.subjectFeeMaps;
	}

	public void setSubjectFeeMaps(List<SubjectFeeMap> subjectFeeMaps) {
		this.subjectFeeMaps = subjectFeeMaps;
	}

	public SubjectFeeMap getSubjectFeeMap() {
		return this.subjectFeeMap;
	}

	public void setSubjectFeeMap(SubjectFeeMap subjectFeeMap) {
		this.subjectFeeMap = subjectFeeMap;
	}

	public int getFeeDetailsId() {
		return this.feeDetailsId;
	}

	public void setFeeDetailsId(int feeDetailsId) {
		this.feeDetailsId = feeDetailsId;
	}

	public int getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public List<Subject> getSubjectList() {
		return this.subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<FeeDetails> getFeeDetailsList() {
		return this.feeDetailsList;
	}

	public void setFeeDetailsList(List<FeeDetails> feeDetailsList) {
		this.feeDetailsList = feeDetailsList;
	}

}
