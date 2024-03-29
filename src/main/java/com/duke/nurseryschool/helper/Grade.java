package com.duke.nurseryschool.helper;

public enum Grade {
	SECOND(2, "D"), THIRD(3, "C"), FOURTH(4, "B"), FIFTH(5, "A"), UNIDENTIFIED(0, "Z");

	private int grade;
	private String alphabeticalSymbol;

	private Grade(int grade, String label) {
		this.grade = grade;
		this.alphabeticalSymbol = label;
	}

	public int getGrade() {
		return this.grade;
	}

	public String getLabel() {
		return this.alphabeticalSymbol;
	}

	public String getOfficialLabel() {
		return this.alphabeticalSymbol + this.grade;
	}

}
