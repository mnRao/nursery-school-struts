package com.duke.nurseryschool.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import com.duke.nurseryschool.core.CoreDAO;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.hibernate.bean.Parent;

public class ParentDAO extends CoreDAO {

	@SuppressWarnings("unchecked")
	public List<Parent> getParents() {
		List<Parent> parents = new ArrayList<Parent>();
		try {
			parents = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_PARENTS).list();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return parents;
	}

	public Parent getParent(int parentId) {
		Parent parent = null;
		try {
			parent = (Parent) this.session.get(Parent.class, parentId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return parent;
	}

	public void saveOrUpdateParent(Parent parent) {
		try {
			this.session.saveOrUpdate(parent);
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
		}
	}

	public boolean deleteParent(int parentId) {
		try {
			Parent parent = (Parent) this.session.get(Parent.class, parentId);
			this.session.delete(parent);
			this.session.flush();
		}
		catch (Exception e) {
			this.transaction.rollback();
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
