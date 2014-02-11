package com.duke.nurseryschool.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.duke.nurseryschool.helper.BusinessLogicSolver;
import com.duke.nurseryschool.helper.Grade;

public class TestHelper {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAcademicYearCalculation() {
		assertEquals("[2012-2013]",
				BusinessLogicSolver.calculateCurrentAcademicYear(2, 2013));
		assertEquals("[2013-2014]",
				BusinessLogicSolver.calculateCurrentAcademicYear(9, 2013));
		assertEquals("[2013-2014]",
				BusinessLogicSolver.calculateCurrentAcademicYear(12, 2013));
	}

	/**
	 * This test passes ONLY in 2014. For other years, consider +- DIFF_YEAR for
	 * both startYear and endYear
	 */
	@Test
	public void testGradeCalculation() {
		assertEquals(Grade.GRADUATED,
				BusinessLogicSolver.calculateGrade(2009, 2013));
		assertEquals(Grade.FIFTH,
				BusinessLogicSolver.calculateGrade(2010, 2014));
		assertEquals(Grade.FOURTH,
				BusinessLogicSolver.calculateGrade(2011, 2015));
		assertEquals(Grade.THIRD,
				BusinessLogicSolver.calculateGrade(2012, 2016));
		assertEquals(Grade.SECOND,
				BusinessLogicSolver.calculateGrade(2013, 2017));
		;
	}

	// @Test
	// public void testStandardMonthCalculation() {
	// BusinessLogicSolver.getStandardMonthName(0);
	// }
}
