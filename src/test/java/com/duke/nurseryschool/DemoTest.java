package com.duke.nurseryschool;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.duke.nurseryschool.hibernate.dao.StudentDAO;

public class DemoTest extends AbstractTest {
	private StudentDAO studentDAO;

	@Override
	protected void doSetup() {
		this.studentDAO = new StudentDAO();
		this.studentDAO.setSession(this.session);
	}

	@Test
	public void test() {
		assertEquals(130, this.studentDAO.getStudents().size());
		assertEquals(129, this.studentDAO.getActiveStudents().size());
	}

}
