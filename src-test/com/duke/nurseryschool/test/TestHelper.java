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
				BusinessLogicSolver.calculateCurrentAcademicYear(1, 2013));
		assertEquals("[2013-2014]",
				BusinessLogicSolver.calculateCurrentAcademicYear(9, 2013));
		assertEquals("[2013-2014]",
				BusinessLogicSolver.calculateCurrentAcademicYear(12, 2013));
	}

	/**
	 * This test passes ONLY in Feb, 2014. For other years, consider +-
	 * DIFF_YEAR for both startYear and endYear
	 */
	@Test
	public void testGradeCalculation() {
		// TODO TEST ALL YEARS (FOR LOOP)

		// Tests at current time
		assertEquals(Grade.UNIDENTIFIED,
				BusinessLogicSolver.calculateGrade(2009, 2013, 2, 2014));
		assertEquals(Grade.FIFTH,
				BusinessLogicSolver.calculateGrade(2010, 2014, 2, 2014));
		assertEquals(Grade.FOURTH,
				BusinessLogicSolver.calculateGrade(2011, 2015, 2, 2014));
		assertEquals(Grade.THIRD,
				BusinessLogicSolver.calculateGrade(2012, 2016, 2, 2014));
		assertEquals(Grade.SECOND,
				BusinessLogicSolver.calculateGrade(2013, 2017, 2, 2014));
		assertEquals(Grade.UNIDENTIFIED,
				BusinessLogicSolver.calculateGrade(2014, 2018, 2, 2014));
		// Tests at past time

		// Tests at future time (from September of endYear to ever after)
		assertEquals(Grade.UNIDENTIFIED,
				BusinessLogicSolver.calculateGrade(2010, 2014, 9, 2014));
		assertEquals(Grade.FIFTH,
				BusinessLogicSolver.calculateGrade(2011, 2015, 9, 2014));
		assertEquals(Grade.FOURTH,
				BusinessLogicSolver.calculateGrade(2012, 2016, 9, 2014));
		assertEquals(Grade.THIRD,
				BusinessLogicSolver.calculateGrade(2013, 2017, 9, 2014));
		assertEquals(Grade.SECOND,
				BusinessLogicSolver.calculateGrade(2014, 2018, 9, 2014));
		assertEquals(Grade.UNIDENTIFIED,
				BusinessLogicSolver.calculateGrade(2015, 2019, 9, 2014));
	}

}
