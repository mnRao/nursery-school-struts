package com.duke.nurseryschool.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.duke.nurseryschool.AbstractTest;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.dao.MixedDAO;

public class MixedDAOTests extends AbstractTest {
	private MixedDAO mixedDAO;

	@Override
	protected void doSetup() {
		this.mixedDAO = new MixedDAO();
		this.mixedDAO.setSession(this.session);
	}

	@Test
	public void testGetActiveClassesCount() {
		assertEquals(4, this.mixedDAO.getActiveClassesCount());
	}

	@Test
	public void testGetActiveStudentsCount() {
		assertEquals(129, this.mixedDAO.getActiveStudentsCount());
	}

	@Test
	public void testGetStudentsHavingBreakfast() {
		assertEquals(30, this.mixedDAO.getStudentsHavingBreakfast(1).size());
		assertEquals(29, this.mixedDAO.getStudentsHavingBreakfast(2).size());
		// 28 for month 3 as 1 student is disabled (studentId = 133,
		// paymentId = 239)
		assertEquals(28, this.mixedDAO.getStudentsHavingBreakfast(3).size());
	}

	@Test
	public void testGetStudentsHavingSelectedOnlyFee() {
		assertEquals(3, this.mixedDAO.getStudentsHavingSelectedOnlyFee(1, 2).size());
		assertEquals(11, this.mixedDAO.getStudentsHavingSelectedOnlyFee(1, 3).size());
		assertEquals(10, this.mixedDAO.getStudentsHavingSelectedOnlyFee(1, 4).size());
		assertEquals(0, this.mixedDAO.getStudentsHavingSelectedOnlyFee(1, 5).size());
		assertEquals(5, this.mixedDAO.getStudentsHavingSelectedOnlyFee(1, 6).size());
		assertEquals(15, this.mixedDAO.getStudentsHavingSelectedOnlyFee(1, 7).size());
		assertEquals(22, this.mixedDAO.getStudentsHavingSelectedOnlyFee(1, 8).size());
		// 0 as 1 student is disabled (studentId = 133)
		assertEquals(0, this.mixedDAO.getStudentsHavingSelectedOnlyFee(3, 2).size());
	}

	@Test
	public void testGetPaymentsByFeePolicy() {
		assertEquals(31, this.mixedDAO.getPaymentsByFeePolicy(this.session, 1).size());
		assertEquals(30, this.mixedDAO.getPaymentsByFeePolicy(this.session, 2).size());
		assertEquals(32, this.mixedDAO.getPaymentsByFeePolicy(this.session, 3).size());
	}

	@Test
	public void testGetFees() {
		assertEquals(10, this.mixedDAO.getFees(this.session).size());
	}

	@Test
	public void testGetFeesWithNoGroup() {
		List<Fee> feeWithNoGroup = this.mixedDAO.getFeeWithNoGroup(this.session);
		assertEquals(1, feeWithNoGroup.size());
		assertEquals(1, feeWithNoGroup.get(0).getFeeId());
		assertEquals(0, feeWithNoGroup.get(0).getType().getType());
	}

	@Test
	public void testGetFeeByType() {
		// TODO
	}

	@Test
	public void testGetFeeNotByType() {
		// TODO
	}

	@Test
	public void testGetPaymentByStudentIdAndFeePolicyId() {
		assertEquals(24, this.mixedDAO.getPaymentByStudentIdAndFeePolicyId(1, 1).getPaymentId());
	}

	@Test
	public void testGetFeeMapByFeeIdAndFeePolicyId() {
		// TODO
	}

	@Test
	public void testGetAlternativeFeeMapByFeeIdAndFeePolicyId() {
		// TODO
	}
}
