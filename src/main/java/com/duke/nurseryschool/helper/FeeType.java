package com.duke.nurseryschool.helper;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.duke.nurseryschool.generated.I18N;

public enum FeeType {
	STATIC(1, I18N.LABEL_FEETYPE_STATIC), ALL_EXCEPT_SELECTED(2,
			I18N.LABEL_FEETYPE_ALLEXCEPTSELECTED), SELECTED_ONLY(3,
			I18N.LABEL_FEETYPE_SELECTEDONLY), UNKNOWN(Integer.MIN_VALUE,
			I18N.LABEL_FEETYPE_UNKNOWN);

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
		return Helper.getI18N(this.name);
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
