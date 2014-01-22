package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Subject;
import com.duke.nurseryschool.hibernate.dao.SubjectDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class SubjectAction extends CoreAction implements ModelDriven<Subject> {

	private static final long serialVersionUID = -559372069321576936L;

	private Subject subject = new Subject();
	private List<Subject> subjects = new ArrayList<Subject>();
	private SubjectDAO dao = new SubjectDAO();

	@Override
	public Subject getModel() {
		return this.subject;
	}

	public String saveOrUpdate() {
		this.dao.saveOrUpdateSubject(this.subject);
		this.addActionMessage(this.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.subjects = this.dao.getSubjects();
		return Action.SUCCESS;
	}

	public String delete() {
		this.dao.deleteSubject(Integer.parseInt(this.request
				.getParameter("subjectId")));
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		this.subject = this.dao.getSubject(Integer.parseInt(this.request
				.getParameter("subjectId")));
		// Load all
		this.subjects = this.dao.getSubjects();
		return Action.SUCCESS;
	}

	public List<Subject> getSubjects() {
		return this.subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}
