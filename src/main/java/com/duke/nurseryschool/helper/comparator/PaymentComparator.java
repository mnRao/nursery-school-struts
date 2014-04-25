package com.duke.nurseryschool.helper.comparator;

import java.util.Comparator;

import com.duke.nurseryschool.helper.Helper;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.bean.Student;

public class PaymentComparator implements Comparator<Payment> {

	@Override
	public int compare(Payment payment1, Payment payment2) {
		return this.sortAscending(payment1.getStudent(), payment2.getStudent());
	}

	private int sortAscending(Student student1, Student student2) {
		return Helper.extractLastWord(student1.getName()).compareTo(
				Helper.extractLastWord(student2.getName()));
	}

	private int sortDescending(Student student1, Student student2) {
		return Helper.extractLastWord(student2.getName()).compareTo(
				Helper.extractLastWord(student1.getName()));
	}

}
