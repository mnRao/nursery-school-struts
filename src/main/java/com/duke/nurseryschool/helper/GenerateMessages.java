package com.duke.nurseryschool.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import com.duke.nurseryschool.PropertyLoader;

public class GenerateMessages {
	private static final String CLASS_NAME = "I18N";
	private static final String MESSAGE_FILE_PATH = "../java/com/duke/nurseryschool/generated/"
			+ CLASS_NAME + ".java";
	private static final String PACKAGE = "com.duke.nurseryschool.generated";
	private static final String PROPERTY_FILE_PATH = "global.properties";

	public static void main(String[] args) {
		System.out.println("Writing message class ...");
		writeMessageClass();
		System.out.println("Finished");
	}

	@SuppressWarnings("unchecked")
	private static void writeMessageClass() {
		List<String> sortedEnKeySet = sortKeys();
		System.out.println(sortedEnKeySet.size() + " keys in total");

		try {
			File file = new File(MESSAGE_FILE_PATH);
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			output.write("package " + PACKAGE + ";");
			output.newLine();
			output.newLine();
			output.write("public class " + CLASS_NAME + " {");
			output.newLine();
			output.newLine();

			for (String key : sortedEnKeySet) {
				output.write("\tpublic static final String "
						+ transformKey(key.toString()) + " = \""
						+ key.toString() + "\";");
				output.newLine();
			}

			output.newLine();
			output.write("}");
			output.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<String> sortKeys() {
		PropertyLoader enPropertyLoader = new PropertyLoader(PROPERTY_FILE_PATH);
		Properties globalENProperties = enPropertyLoader.getGlobalProperties();
		Set<Object> enKeySet = globalENProperties.keySet();
		// Sort
		List<String> sortedEnKeySet = new ArrayList<String>(new TreeSet(
				enKeySet));
		return sortedEnKeySet;
	}

	/**
	 * Transform key into appropriate format for constant
	 */
	private static String transformKey(String key) {
		String transformedKey = key.trim().toUpperCase();
		transformedKey = transformedKey.replace(".", "_");

		return transformedKey;
	}
}
