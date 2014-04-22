package com.duke.nurseryschool.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
	private final Properties globalProperties = new Properties();

	public PropertyLoader(String filePath) {
		this.loadProperties(filePath);
	}

	public void loadProperties(String filePath) {
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream(filePath);
		try {
			this.globalProperties.load(in);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Properties getGlobalProperties() {
		return this.globalProperties;
	}

}
