package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.generated.I18N;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.Helper;
import com.duke.nurseryschool.helper.StringUtil;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.Student;
import com.duke.nurseryschool.hibernate.dao.ClassesDAO;
import com.duke.nurseryschool.hibernate.dao.StudentDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class StudentAction extends CoreAction implements ModelDriven<Student>,
		Preparable {

	private static final long serialVersionUID = -3023527426370035860L;

	private Student student = new Student();
	private List<Student> students = new ArrayList<Student>();
	private final StudentDAO dao = new StudentDAO();
	private final ClassesDAO classesDAO = new ClassesDAO();

	private int classId;
	private List<Classes> classList;

	@Override
	public Student getModel() {
		return this.student;
	}

	public String saveOrUpdate() {
		this.dao.getSession().evict(
				this.dao.getStudent(Integer.parseInt(this.request
						.getParameter("studentId"))));

		// Set class based on ID
		Classes associatedClass = this.classesDAO.getClasses(this.classId);
		this.student.setAssociatedClass(associatedClass);

		this.dao.saveOrUpdateStudent(this.student);

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String list() {
		return Action.SUCCESS;
	}

	@SkipValidation
	public String delete() {
		boolean isDeleted = this.dao.deleteStudent(Integer
				.parseInt(this.request.getParameter("studentId")));
		if (!isDeleted) {
			this.addActionError(this.getText(I18N.ERROR_DELETE_CHILDREN_FIRST));
		}
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String deleteParentMap() {
		this.dao.deleteParentMap(
				Integer.parseInt(this.request.getParameter("studentId")),
				Integer.parseInt(this.request.getParameter("parentId")));

		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String edit() {
		this.student = this.dao.getStudent(Integer.parseInt(this.request
				.getParameter("studentId")));
		this.classId = this.student.getAssociatedClass().getClassId();

		return Action.SUCCESS;
	}

	@Override
	public void validate() {
		if (this.classId == 0) {
			this.addFieldError("student.classId",
					this.getText(I18N.ERROR_REQUIRED, new String[] {
						this.getText(I18N.LABEL_STUDENT_CLASSID)
					}));
		}
		if (StringUtil.isEmpty(this.student.getName().trim())) {
			this.addFieldError("student.name",
					this.getText(I18N.ERROR_REQUIRED, new String[] {
						this.getText(I18N.LABEL_STUDENT_NAME)
					}));
		}
		if (this.student.getDateOfBirth() != null
				&& (this.student.getDateOfBirth().getYear() + 1900) >= Helper
						.calculateCurrentYear()) {
			this.addFieldError("student.dateOfBirth",
					this.getText(I18N.ERROR_CONSTRAINT_STUDENT_DATEOFBIRTH));
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

	private void populateData() {
		// Populate class list
		this.classList = this.classesDAO.getClasses();
		this.students = this.dao.getStudents();
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public int getClassId() {
		return this.classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public List<Classes> getClassList() {
		return this.classList;
	}

	public void setClassList(List<Classes> classList) {
		this.classList = classList;
	}

}
