package com.duke.nurseryschool;

import com.duke.nurseryschool.core.AuthenticationRequiredAction;
import com.duke.nurseryschool.hibernate.dao.MixedDAO;

public class DashboardAction extends AuthenticationRequiredAction {

	private static final long serialVersionUID = -6457541389956060209L;

	private MixedDAO mixedDAO = new MixedDAO();

	private int classAvailableCount;
	private int activeStudentsCount;

	@Override
	public String execute() throws Exception {
		this.classAvailableCount = this.mixedDAO.getActiveClassesCount();
		this.activeStudentsCount = this.mixedDAO.getActiveStudentsCount();

		return SUCCESS;
	}

	public int getClassAvailableCount() {
		return this.classAvailableCount;
	}

	public void setClassAvailableCount(int classAvailableCount) {
		this.classAvailableCount = classAvailableCount;
	}

	public int getActiveStudentsCount() {
		return this.activeStudentsCount;
	}

	public void setActiveStudentsCount(int activeStudentsCount) {
		this.activeStudentsCount = activeStudentsCount;
	}

}
