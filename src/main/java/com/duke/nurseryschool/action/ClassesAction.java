package com.duke.nurseryschool.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.duke.nurseryschool.action.core.CoreAction;
import com.duke.nurseryschool.generated.I18N;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.StringUtil;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.Course;
import com.duke.nurseryschool.hibernate.dao.ClassesDAO;
import com.duke.nurseryschool.hibernate.dao.CourseDAO;
import com.duke.nurseryschool.hibernate.dao.FeePolicyDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ClassesAction extends CoreAction implements ModelDriven<Classes>,
		Preparable {

	private static final long serialVersionUID = 8744171277878043481L;

	private Classes classes = new Classes();
	private List<Classes> allClasses = new ArrayList<Classes>();
	private final ClassesDAO dao = new ClassesDAO();

	private final FeePolicyDAO feePolicyDAO = new FeePolicyDAO();
	private final CourseDAO courseDAO = new CourseDAO();
	private int courseId;
	private List<Course> courseList;

	@Override
	public Classes getModel() {
		return this.classes;
	}

	public String saveOrUpdate() {
		// 1st solution: this.dao.getSession().clear();
		// => clear all in session
		// 2nd solution: evict() the previous one in session
		this.dao.getSession().evict(
				this.dao.getClasses(Integer.parseInt(this.request
						.getParameter("classId"))));
		// Save course based on ID
		Course course = this.courseDAO
				.getCourse(Integer.valueOf(this.courseId));
		this.classes.setCourse(course);

		this.dao.saveOrUpdateClasses(this.classes);
		this.addActionMessage(this.getText(I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String list() {
		return Action.SUCCESS;
	}

	@SkipValidation
	public String delete() {
		boolean isDeleted = this.dao.deleteClasses(Integer
				.parseInt(this.request.getParameter("classId")));
		if (!isDeleted) {
			this.addActionError(this.getText(I18N.ERROR_DELETE_CHILDREN_FIRST));
			// Populate data
			this.populateData();

			return Action.SUCCESS;// Actually Error
		}
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String deleteFeePolicyMap() {
		this.feePolicyDAO.deleteFeePolicy(Integer.parseInt(this.request
				.getParameter("feePolicyId")));
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	@SkipValidation
	public String edit() {
		this.classes = this.dao.getClasses(Integer.parseInt(this.request
				.getParameter("classId")));
		// Load courseId
		this.courseId = this.classes.getCourse().getCourseId();

		return Action.SUCCESS;
	}

	@SkipValidation
	public String autoSetCourse() {
		return this.list();
	}

	@Override
	public void validate() {
		if (this.courseId <= 0) {
			this.addFieldError("courseId",
					this.getText(I18N.ERROR_REQUIRED, new String[] {
						this.getText(I18N.LABEL_CLASSES_COURSEID)
					}));
		}
		if (StringUtil.isEmpty(this.classes.getCode())) {
			this.addFieldError("classes.code",
					this.getText(I18N.ERROR_REQUIRED, new String[] {
						this.getText(I18N.LABEL_CLASSES_CODE)
					}));
		}
		else {
			int codeLength = this.classes.getCode().length();
			if (codeLength > 20 || codeLength < 2) {
				this.addFieldError("classes.code",
						this.getText(I18N.ERROR_CONSTRAINT_CLASSES_CODE));
			}
		}

		// Check for uniqueness
		if (this.dao.hasDuplicates(this.classes.getClassId(), this.courseId,
				this.classes.getCode())) {
			this.addFieldError("classes.code",
					this.getText(I18N.ERROR_DUPLICATION_CLASS));
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

	public void prepareAutoSetCourse() throws Exception {
		this.populateData();
	}

	private void populateData() {
		this.allClasses = this.dao.getClasses();
		// Populate class list
		this.courseList = this.courseDAO.getCourses();
	}

	public List<Classes> getAllClasses() {
		return this.allClasses;
	}

	public void setAllClasses(List<Classes> allClasses) {
		this.allClasses = allClasses;
	}

	public Classes getClasses() {
		return this.classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public int getCourseId() {
		return this.courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public List<Course> getCourseList() {
		return this.courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

}