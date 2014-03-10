package com.duke.nurseryschool.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
	private Properties	globalProperties	= new Properties();

	public PropertyLoader(String filePath) {
		this.loadProperties(filePath);
	}

	public void loadProperties(String filePath) {
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream(filePath);
		try {
			globalProperties.load(in);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Properties getGlobalProperties() {
		return globalProperties;
	}

}
