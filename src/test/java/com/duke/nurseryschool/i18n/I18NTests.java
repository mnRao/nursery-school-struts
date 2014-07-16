package com.duke.nurseryschool.i18n;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.duke.nurseryschool.action.PropertyLoader;

public class I18NTests {
	Properties globalENProperties;
	Properties globalVIProperties;

	Set<Object> enKeySet;
	Set<Object> viKeySet;

	@Before
	public void setUp() throws Exception {
		PropertyLoader enPropertyLoader = new PropertyLoader(
				"global.properties");
		this.globalENProperties = enPropertyLoader.getGlobalProperties();
		PropertyLoader viPropertyLoader = new PropertyLoader(
				"global_vi_VN.properties");
		this.globalVIProperties = viPropertyLoader.getGlobalProperties();

		this.enKeySet = this.globalENProperties.keySet();
		this.viKeySet = this.globalVIProperties.keySet();
	}

	/**
	 * Numbers of keys are equal
	 */
	@Test
	public void sizeEqualityCheck() {
		assertEquals(this.globalENProperties.size(),
				this.globalVIProperties.size());
	}

	/**
	 * At least the two have common keys
	 */
	@Test
	public void sameKeySetCheck() {
		boolean isSameKeySet = !Collections.disjoint(this.enKeySet,
				this.viKeySet);
		assertEquals(true, isSameKeySet);
	}

	/**
	 * Ensure no different keys among locales
	 */
	@Test
	public void noDifferentKeysCheck() {
		Set<Object> commonKeys = new HashSet<Object>(this.enKeySet);
		commonKeys.retainAll(this.viKeySet);
		int commonKeysCount = commonKeys.size();
		assertEquals(commonKeysCount, this.enKeySet.size());
		assertEquals(commonKeysCount, this.viKeySet.size());
	}

}
