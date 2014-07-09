package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.comparator.StudentComparator;
import com.duke.nurseryschool.hibernate.bean.Parent;
import com.duke.nurseryschool.hibernate.bean.Student;

public class StudentDAO extends CoreDAO {

	@SuppressWarnings("unchecked")
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<Student>();
		try {
			students = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_STUDENTS).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		// Sort
		Collections.sort(students, new StudentComparator());

		return students;
	}

	@SuppressWarnings({
			"deprecation", "unchecked"
	})
	public List<Student> getActiveStudents() {
		Criteria criteria = this.session.createCriteria(Student.class);
		criteria.add(Expression.eq("isActive", true));
		return criteria.list();
	}

	public Student getStudent(int studentId) {
		Student student = null;
		try {
			student = (Student) this.session.get(Student.class, studentId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	public void saveOrUpdateStudent(Student student) {
		try {
			this.session.saveOrUpdate(student);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}

	public boolean deleteStudent(int studentId) {
		try {
			Student student = (Student) this.session.get(Student.class,
					studentId);
			this.session.delete(student);
			this.session.flush();
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public void deleteParentMap(int studentId, int parentId) {
		Student student = this.getStudent(studentId);

		Set<Parent> parents = student.getParents();
		Iterator<Parent> iterator = parents.iterator();
		while (iterator.hasNext()) {
			Parent parent = iterator.next();
			if (parent.getParentId() == parentId) {
				iterator.remove();
			}
		}

		student.setParents(parents);
		this.saveOrUpdateStudent(student);
	}
}
