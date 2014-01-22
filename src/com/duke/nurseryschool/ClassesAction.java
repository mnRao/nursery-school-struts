package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.dao.ClassesDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class ClassesAction extends CoreAction implements ModelDriven<Classes> {

	private Classes classes = new Classes();
	private List<Classes> classess = new ArrayList<Classes>();
	private ClassesDAO dao = new ClassesDAO();

	@Override
	public Classes getModel() {
		return this.classes;
	}

	public String saveOrUpdate() {
		this.dao.saveOrUpdateClasses(this.classes);
		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.classess = this.dao.getClasses();
		return Action.SUCCESS;
	}

	public String delete() {
		this.dao.deleteClasses(Integer.parseInt(this.request
				.getParameter("classesId")));
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		this.classes = this.dao.getClasses(Integer.parseInt(this.request
				.getParameter("classesId")));
		// Load all
		this.classess = this.dao.getClasses();
		return Action.SUCCESS;
	}

	public List<Classes> getClassess() {
		return this.classess;
	}

	public void setClassess(List<Classes> classess) {
		this.classess = classess;
	}

	public Classes getClasses() {
		return this.classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

}