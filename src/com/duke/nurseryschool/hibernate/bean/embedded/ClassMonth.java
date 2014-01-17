package com.duke.nurseryschool.hibernate.bean.embedded;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.Month;

@Embeddable
public class ClassMonth implements Serializable {
	@ManyToOne
	@JoinColumn(name = "classId")
	private Classes associatedClass;

	@ManyToOne
	@JoinColumn(name = "monthId")
	private Month month;

	public Classes getAssociatedClass() {
		return this.associatedClass;
	}

	public void setAssociatedClass(Classes associatedClass) {
		this.associatedClass = associatedClass;
	}

	public Month getMonth() {
		return this.month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}
}
