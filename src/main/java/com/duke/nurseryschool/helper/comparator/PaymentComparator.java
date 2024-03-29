package com.duke.nurseryschool.helper.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.duke.nurseryschool.helper.Helper;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.bean.Student;

public class PaymentComparator implements Comparator<Payment>, Serializable {

	private static final long serialVersionUID = 8226115690104673476L;

	@Override
	public int compare(Payment payment1, Payment payment2) {
		return this.sortAscending(payment1.getStudent(), payment2.getStudent());
	}

	private int sortAscending(Student student1, Student student2) {
		String[] reversedFragments1 = Helper.extractAndReverseNameFragments(student1);
		String[] reversedFragments2 = Helper.extractAndReverseNameFragments(student2);

		return Helper.compareNames(reversedFragments1, reversedFragments2);
	}

	private int sortDescending(Student student1, Student student2) {
		return this.sortAscending(student2, student1);
	}

}
