package com.duke.nurseryschool.test;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.duke.nurseryschool.action.PropertyLoader;

public class TestMessages {
	Properties globalENProperties;
	Properties globalVIProperties;

	@Before
	public void setUp() throws Exception {
		PropertyLoader enPropertyLoader = new PropertyLoader(
				"global.properties");
		this.globalENProperties = enPropertyLoader.getGlobalProperties();
		PropertyLoader viPropertyLoader = new PropertyLoader(
				"global_vi_VN.properties");
		this.globalVIProperties = viPropertyLoader.getGlobalProperties();
	}

	@Test
	public void test() {
		// Numbers of keys are equal
		assertEquals(this.globalENProperties.size(),
				this.globalVIProperties.size());

		Set<Object> enKeySet = this.globalENProperties.keySet();
		Set<Object> viKeySet = this.globalVIProperties.keySet();
		// At least the two have common keys
		boolean isSameKeySet = !Collections.disjoint(enKeySet, viKeySet);
		assertEquals(true, isSameKeySet);

		Set<Object> commonKeys = new HashSet<Object>(enKeySet);
		commonKeys.retainAll(viKeySet);
		int commonKeysCount = commonKeys.size();
		// Ensure no different keys
		assertEquals(commonKeysCount, enKeySet.size());
		assertEquals(commonKeysCount, viKeySet.size());
	}

}
