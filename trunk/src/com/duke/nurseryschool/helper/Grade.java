package com.duke.nurseryschool.helper;

public enum Grade {
	SECOND(2, "D"), THIRD(3, "C"), FOURTH(4, "B"), FIFTH(5, "A");

	private int grade;
	private String alphabeticalSymbol;

	private Grade(int grade, String label) {
		this.grade = grade;
		this.alphabeticalSymbol = label;
	}

	public int getGrade() {
		return grade;
	}

	public String getLabel() {
		return alphabeticalSymbol;
	}

	public String getOfficialLabel() {
		return alphabeticalSymbol + grade;
	}
}
