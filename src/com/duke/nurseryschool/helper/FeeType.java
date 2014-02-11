package com.duke.nurseryschool.helper;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public enum FeeType {
	STATIC(1, "Static"), ALL_EXCEPT_SELECTED(2, "All except selected"), SELECTED_ONLY(
			3, "Selected Only"), UNKNOWN(Integer.MIN_VALUE, "Unknown");

	private int type;
	private String name;

	private FeeType(int type, String name) {
		this.type = type;
		this.name = name;
	}

	public int getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Get all types except unknown one
	 */
	public static List<FeeType> getAll() {
		List<FeeType> allFeeTypes = new LinkedList<FeeType>(
				Arrays.asList(FeeType.values()));
		allFeeTypes.remove(UNKNOWN);

		return allFeeTypes;
	}

	public static FeeType parse(int type) {
		for (FeeType feeType : FeeType.values()) {
			if (feeType.getType() == type)
				return feeType;
		}

		return UNKNOWN;
	}
}
