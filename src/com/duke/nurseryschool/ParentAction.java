package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.bean.Parent;
import com.duke.nurseryschool.hibernate.bean.Student;
import com.duke.nurseryschool.hibernate.dao.ParentDAO;
import com.duke.nurseryschool.hibernate.dao.StudentDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class ParentAction extends CoreAction implements ModelDriven<Parent> {

	private Parent parent = new Parent();
	private List<Parent> parents = new ArrayList<Parent>();
	private ParentDAO dao = new ParentDAO();

	private int studentId;
	private List<Student> studentList;
	private StudentDAO studentDAO = new StudentDAO();

	@Override
	public Parent getModel() {
		return this.parent;
	}

	public String saveOrUpdate() {
		// If chosen from drop-down list
		// if (this.parentId != 0) {
		// this.parent = this.dao.getParent(this.parentId);
		// }
		this.dao.saveOrUpdateParent(this.parent);
		// Set student based on studentId
		if (this.studentId != 0) {
			Student student = this.studentDAO.getStudent(this.studentId);
			student.getParents().add(this.parent);
			this.studentDAO.saveOrUpdateStudent(student);
		}

		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.populateLists();

		this.parents = this.dao.getParents();
		return Action.SUCCESS;
	}

	public String delete() {
		this.dao.deleteParent(Integer.parseInt(this.request
				.getParameter("parentId")));
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		this.populateLists();

		this.parent = this.dao.getParent(Integer.parseInt(this.request
				.getParameter("parentId")));
		// Load all
		this.parents = this.dao.getParents();
		return Action.SUCCESS;
	}

	private void populateLists() {
		this.studentList = this.studentDAO.getStudents();
	}

	public String autoSetStudent() {
		return this.list();
	}

	public List<Parent> getParents() {
		return this.parents;
	}

	public void setParents(List<Parent> parents) {
		this.parents = parents;
	}

	public Parent getParent() {
		return this.parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
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

	// public int getParentId() {
	// return this.parentId;
	// }
	//
	// public void setParentId(int parentId) {
	// this.parentId = parentId;
	// }

}
