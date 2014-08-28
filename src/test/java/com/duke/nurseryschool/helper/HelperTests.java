package com.duke.nurseryschool.helper;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.duke.nurseryschool.helper.Helper;

public class HelperTests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void calculateTotalDaysInMonth_normalYear_februaryHas28Days() {
		assertEquals(31, Helper.calculateTotalDaysInMonth(2014, 1));
		assertEquals(28, Helper.calculateTotalDaysInMonth(2014, 2));
		assertEquals(31, Helper.calculateTotalDaysInMonth(2014, 3));
		assertEquals(30, Helper.calculateTotalDaysInMonth(2014, 4));
		assertEquals(31, Helper.calculateTotalDaysInMonth(2014, 5));
		assertEquals(30, Helper.calculateTotalDaysInMonth(2014, 6));
		assertEquals(31, Helper.calculateTotalDaysInMonth(2014, 7));
		assertEquals(31, Helper.calculateTotalDaysInMonth(2014, 8));
		assertEquals(30, Helper.calculateTotalDaysInMonth(2014, 9));
		assertEquals(31, Helper.calculateTotalDaysInMonth(2014, 10));
		assertEquals(30, Helper.calculateTotalDaysInMonth(2014, 11));
		assertEquals(31, Helper.calculateTotalDaysInMonth(2014, 12));
	}

	@Test
	public void calculateTotalDaysInMonth_lunarYear_onlyFebruaryHas29Days() {
		assertEquals(31, Helper.calculateTotalDaysInMonth(2012, 1));
		assertEquals(29, Helper.calculateTotalDaysInMonth(2012, 2));
		assertEquals(31, Helper.calculateTotalDaysInMonth(2012, 3));
		assertEquals(30, Helper.calculateTotalDaysInMonth(2012, 4));
		assertEquals(31, Helper.calculateTotalDaysInMonth(2012, 5));
		assertEquals(30, Helper.calculateTotalDaysInMonth(2012, 6));
		assertEquals(31, Helper.calculateTotalDaysInMonth(2012, 7));
		assertEquals(31, Helper.calculateTotalDaysInMonth(2012, 8));
		assertEquals(30, Helper.calculateTotalDaysInMonth(2012, 9));
		assertEquals(31, Helper.calculateTotalDaysInMonth(2012, 10));
		assertEquals(30, Helper.calculateTotalDaysInMonth(2012, 11));
		assertEquals(31, Helper.calculateTotalDaysInMonth(2012, 12));
	}

	@Test
	public void extractLastWord_normalName_ReturnsLastWord() {
		assertEquals("Levitt", Helper.extractLastWord("Joseph Leonard Gordon Levitt"));
		assertEquals("Willis", Helper.extractLastWord("Bruce Willis"));
	}

	@Test
	public void extractLastWord_oneWordName_ReturnsItself() {
		assertEquals("Bruce", Helper.extractLastWord("Bruce"));
	}

	@Test
	public void extractLastWord_oneWordNameWithSpaceAsPrefix_ReturnsItself() {
		assertEquals("Bruce", Helper.extractLastWord(" Bruce"));
	}

	@Test
	public void extractLastWord_oneWordNameWithSpaceAsSuffix_ReturnsItself() {
		assertEquals("Bruce", Helper.extractLastWord("Bruce "));
	}

	@Test
	public void extractLastWord_normalNameWithSpaceAsPrefix_ReturnsItself() {
		assertEquals("Willis", Helper.extractLastWord(" Bruce Willis"));
		assertEquals("Levitt", Helper.extractLastWord(" Joseph Leonard Gordon Levitt"));
	}

	@Test
	public void extractLastWord_normalNameWithSpaceAsSuffix_ReturnsItself() {
		assertEquals("Willis", Helper.extractLastWord("Bruce Willis "));
		assertEquals("Levitt", Helper.extractLastWord("Joseph Leonard Gordon Levitt "));
	}

}
