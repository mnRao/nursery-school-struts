package com.duke.nurseryschool.helper;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.StringUtil;

public class StringUtilTests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void isEmpty_nullAndBlankAndSpace_ReturnsTrue() {
		assertEquals(true, StringUtil.isEmpty(null));
		assertEquals(true, StringUtil.isEmpty(Constant.EMPTY_STRING));
		assertEquals(true, StringUtil.isEmpty(Constant.SPACE));
	}

	@Test
	public void isPhysicallyEmpty_nullAndBlankAndSpace_ReturnsTrueExceptForSpace() {
		assertEquals(true, StringUtil.isPhysicallyEmpty(null));
		assertEquals(true, StringUtil.isPhysicallyEmpty(Constant.EMPTY_STRING));
		assertEquals(false, StringUtil.isPhysicallyEmpty(Constant.SPACE));

	}

}
