package com.duke.nurseryschool.helper;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.duke.nurseryschool.BaseTestCase;
import com.duke.nurseryschool.helper.BusinessLogicSolver;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.Grade;
import com.duke.nurseryschool.helper.date.CurrentCalendar;

public class BusinessLogicSolverTests extends BaseTestCase {

	private BusinessLogicSolver businessLogicSolver;

	@Mock
	private CurrentCalendar currentCalendarStub;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.businessLogicSolver = BusinessLogicSolver.getInstance();
	}

	@Override
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void calculateCurrentAcademicYear_earlierInYear_oldAcademicYear() {
		assertEquals("[2012-2013]",
				this.businessLogicSolver.calculateCurrentAcademicYear(1, 2013));
	}

	@Test
	public void calculateCurrentAcademicYear_betweenAcademicYears_newAcademicYear() {
		assertEquals("[2013-2014]",
				this.businessLogicSolver.calculateCurrentAcademicYear(7, 2013));
	}

	@Test
	public void calculateCurrentAcademicYear_laterInYears_newAcademicYear() {
		assertEquals("[2013-2014]",
				this.businessLogicSolver.calculateCurrentAcademicYear(12, 2013));
	}

	@Test
	public void calculateGrade_currentDateIsWithinCurrentAcademicYear() {
		// [2-2014]
		Mockito.when(this.currentCalendarStub.getCurrentMonth()).thenReturn(2);
		Mockito.when(this.currentCalendarStub.getCurrentYear())
				.thenReturn(2014);
		this.businessLogicSolver.setCurrentCalendar(this.currentCalendarStub);

		assertEquals(Grade.UNIDENTIFIED,
				this.businessLogicSolver.calculateGrade(2009, 2013));
		assertEquals(Grade.FIFTH,
				this.businessLogicSolver.calculateGrade(2010, 2014));
		assertEquals(Grade.FOURTH,
				this.businessLogicSolver.calculateGrade(2011, 2015));
		assertEquals(Grade.THIRD,
				this.businessLogicSolver.calculateGrade(2012, 2016));
		assertEquals(Grade.SECOND,
				this.businessLogicSolver.calculateGrade(2013, 2017));
		assertEquals(Grade.UNIDENTIFIED,
				this.businessLogicSolver.calculateGrade(2014, 2018));
	}

	@Test
	public void calculateGrade_currentDateIsBetweenAcademicYears() {
		// [7-2014]
		Mockito.when(this.currentCalendarStub.getCurrentMonth()).thenReturn(7);
		Mockito.when(this.currentCalendarStub.getCurrentYear())
				.thenReturn(2014);
		this.businessLogicSolver.setCurrentCalendar(this.currentCalendarStub);

		// Tests at future time (from September of endYear to ever after)
		assertEquals(Grade.UNIDENTIFIED,
				this.businessLogicSolver.calculateGrade(2010, 2014));
		assertEquals(Grade.FIFTH,
				this.businessLogicSolver.calculateGrade(2011, 2015));
		assertEquals(Grade.FOURTH,
				this.businessLogicSolver.calculateGrade(2012, 2016));
		assertEquals(Grade.THIRD,
				this.businessLogicSolver.calculateGrade(2013, 2017));
		assertEquals(Grade.SECOND,
				this.businessLogicSolver.calculateGrade(2014, 2018));
		assertEquals(Grade.UNIDENTIFIED,
				this.businessLogicSolver.calculateGrade(2015, 2019));
	}

	@Test
	public void calculateGrade_currentDateIsInTheFarPast_UnidentifiedGrade() {
		// [7-2010]
		Mockito.when(this.currentCalendarStub.getCurrentMonth()).thenReturn(7);
		Mockito.when(this.currentCalendarStub.getCurrentYear())
				.thenReturn(2010);
		this.businessLogicSolver.setCurrentCalendar(this.currentCalendarStub);

		// Tests at future time (from September of endYear to ever after)
		assertEquals(Grade.SECOND,
				this.businessLogicSolver.calculateGrade(2010, 2014));
		assertEquals(Grade.UNIDENTIFIED,
				this.businessLogicSolver.calculateGrade(2011, 2015));
		assertEquals(Grade.UNIDENTIFIED,
				this.businessLogicSolver.calculateGrade(2012, 2016));
		assertEquals(Grade.UNIDENTIFIED,
				this.businessLogicSolver.calculateGrade(2013, 2017));
		assertEquals(Grade.UNIDENTIFIED,
				this.businessLogicSolver.calculateGrade(2014, 2018));
		assertEquals(Grade.UNIDENTIFIED,
				this.businessLogicSolver.calculateGrade(2015, 2019));
	}

	@Test
	public void calculateGrade_currentDateIsInTheFarFuture_UnidentifiedGrade() {
		// [2-2019]
		Mockito.when(this.currentCalendarStub.getCurrentMonth()).thenReturn(2);
		Mockito.when(this.currentCalendarStub.getCurrentYear())
				.thenReturn(2019);
		this.businessLogicSolver.setCurrentCalendar(this.currentCalendarStub);

		// Tests at future time (from September of endYear to ever after)
		assertEquals(Grade.UNIDENTIFIED,
				this.businessLogicSolver.calculateGrade(2010, 2014));
		assertEquals(Grade.UNIDENTIFIED,
				this.businessLogicSolver.calculateGrade(2011, 2015));
		assertEquals(Grade.UNIDENTIFIED,
				this.businessLogicSolver.calculateGrade(2012, 2016));
		assertEquals(Grade.UNIDENTIFIED,
				this.businessLogicSolver.calculateGrade(2013, 2017));
		assertEquals(Grade.UNIDENTIFIED,
				this.businessLogicSolver.calculateGrade(2014, 2018));
		assertEquals(Grade.FIFTH,
				this.businessLogicSolver.calculateGrade(2015, 2019));
	}

	@Test(expected = NullPointerException.class)
	public void calculateGenderText_Both0And1_NullPointerException()
			throws InstantiationException, IllegalAccessException {
		assertEquals(Constant.BUSINESS_LOGIC.FEMALE,
				this.businessLogicSolver.calculateGenderText(0));
		assertEquals(Constant.BUSINESS_LOGIC.MALE,
				this.businessLogicSolver.calculateGenderText(1));
	}

	@Test
	public void getStandardMonthName_between1And9_AddExtraZero() {
		assertEquals("01", this.businessLogicSolver.getStandardMonthName(1));
		assertEquals("02", this.businessLogicSolver.getStandardMonthName(2));
		assertEquals("03", this.businessLogicSolver.getStandardMonthName(3));
		assertEquals("04", this.businessLogicSolver.getStandardMonthName(4));
		assertEquals("05", this.businessLogicSolver.getStandardMonthName(5));
		assertEquals("06", this.businessLogicSolver.getStandardMonthName(6));
		assertEquals("07", this.businessLogicSolver.getStandardMonthName(7));
		assertEquals("08", this.businessLogicSolver.getStandardMonthName(8));
		assertEquals("09", this.businessLogicSolver.getStandardMonthName(9));
	}

	@Test
	public void getStandardMonthName_largerThan9_normal() {
		assertEquals("10", this.businessLogicSolver.getStandardMonthName(10));
		assertEquals("11", this.businessLogicSolver.getStandardMonthName(11));
		assertEquals("12", this.businessLogicSolver.getStandardMonthName(12));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getStandardMonthName_monthLargerThan12_IllegalArgumentException() {
		this.businessLogicSolver.getStandardMonthName(13);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getStandardMonthName_monthSmallerThan1_IllegalArgumentException() {
		this.businessLogicSolver.getStandardMonthName(0);
	}
}
