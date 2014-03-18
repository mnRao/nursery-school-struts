package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Parent;
import com.duke.nurseryschool.hibernate.bean.Student;
import com.duke.nurseryschool.hibernate.dao.ParentDAO;
import com.duke.nurseryschool.hibernate.dao.StudentDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ParentAction extends CoreAction implements ModelDriven<Parent>,
		Preparable {

	private Parent			parent		= new Parent();
	private List<Parent>	parents		= new ArrayList<Parent>();
	private ParentDAO		dao			= new ParentDAO();

	private int				parentId;
	private int				studentId;
	private List<Student>	studentList;
	private StudentDAO		studentDAO	= new StudentDAO();

	@Override
	public Parent getModel() {
		return this.parent;
	}

	public String saveOrUpdate() {
		this.dao.getSession().evict(
				this.dao.getParent(Integer.parseInt(this.request
						.getParameter("parentId"))));

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

		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String list() {
		return Action.SUCCESS;
	}

	@SkipValidation
	public String delete() {
		boolean isDeleted = this.dao.deleteParent(Integer.parseInt(this.request
				.getParameter("parentId")));
		if (!isDeleted) {
			this.addActionError(this
					.getText(Constant.I18N.ERROR_DELETE_CHILDREN_FIRST));
		}
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String edit() {
		this.parent = this.dao.getParent(Integer.parseInt(this.request
				.getParameter("parentId")));
		return Action.SUCCESS;
	}

	@SkipValidation
	public String autoSetStudent() {
		return Action.SUCCESS;
	}

	@Override
	public void validate() {
		// if (StringUtil.isEmpty(this.parent.getName().trim())) {
		// this.addFieldError("student.name",
		// this.getText(Constant.I18N.ERROR_REQUIRED_STUDENT_NAME));
		// }

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

	public void prepareAutoSetStudent() throws Exception {
		this.populateData();
	}

	private void populateData() {
		this.parents = this.dao.getParents();
		this.studentList = this.studentDAO.getStudents();
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

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}
