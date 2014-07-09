package com.duke.nurseryschool.test;

import static org.junit.Assert.*;

import org.hibernate.Transaction;
import org.junit.Before;
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
		assertEquals(129, this.studentDAO.getStudents().size());
	}

}
