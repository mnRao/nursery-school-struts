package com.duke.nurseryschool;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Parent;
import com.duke.nurseryschool.hibernate.dao.ParentDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class ParentAction extends CoreAction implements ModelDriven<Parent> {

	private Parent parent = new Parent();
	private List<Parent> parents = new ArrayList<Parent>();
	private ParentDAO dao = new ParentDAO();

	@Override
	public Parent getModel() {
		return this.parent;
	}

	public String saveOrUpdate() {
		this.dao.saveOrUpdateParent(this.parent);
		this.addActionMessage(this
				.getText(Constant.I18N.SUCCESS_RECORD_CREATE_UPDATE));

		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String list() {
		this.parents = this.dao.getParents();
		return Action.SUCCESS;
	}

	public String delete() {
		this.dao.deleteParent(Integer.parseInt(this.request
				.getParameter("parentId")));
		// Redirect to list action
		return Constant.ACTION_RESULT.SUCCESS_REDIRECT;
	}

	public String edit() {
		this.parent = this.dao.getParent(Integer.parseInt(this.request
				.getParameter("parentId")));
		// Load all
		this.parents = this.dao.getParents();
		return Action.SUCCESS;
	}

	public List<Parent> getParents() {
		return this.parents;
	}

	public void setParents(List<Parent> parents) {
		this.parents = parents;
	}

	public Parent getParent() {
		return this.parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

}
