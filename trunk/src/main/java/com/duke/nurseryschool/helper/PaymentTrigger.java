package com.duke.nurseryschool.helper;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.dao.MixedDAO;

public class PaymentTrigger {
	// Params
	private final Payment payment;
	private final FeePolicy feePolicy;

	// Auto-finding
	private final List<Fee> allFeesExceptMeal;

	private final MixedDAO mixedDAO = new MixedDAO();
	private final Session session;
	private final Transaction transaction;

	public PaymentTrigger(Session session, Payment payment, FeePolicy feePolicy) {
		this.session = session;
		this.transaction = this.session.beginTransaction();

		this.payment = payment;
		this.feePolicy = feePolicy;
		// Auto get
		this.allFeesExceptMeal = this.mixedDAO.getFees(this.session);
	}

	public PaymentTrigger(Session session, Payment payment) {
		this.session = session;
		this.transaction = this.session.beginTransaction();

		this.payment = payment;
		this.feePolicy = payment.getFeePolicy();
		// Auto get
		this.allFeesExceptMeal = this.mixedDAO.getFees(this.session);
	}

	public void calculateAndSetTotalFee() {
		this.payment.setTotalFee(this.calculateTotalFee());
	}

	public void calculateAndSetAll() {
		this.payment.setTotalNormalMealFee(this.calculateTotalNormalFee());
		this.payment.setTotalBreakfastFee(this.calculateTotalBreakfastFee());
		this.payment.setTotalFee(this.calculateTotalFee());
	}

	private BigDecimal calculateTotalBreakfastFee() {
		BigDecimal actualBreakfastFee = BigDecimal.valueOf(0);
		// Check whether has breakfast or not
		if (this.payment.getHasBreakfast() == Constant.BUSINESS_LOGIC.HAS_BREAKFAST) {
			BigDecimal penaltyFee = this.feePolicy.getPenaltyFeePerBreakfast()
					.multiply(
							BigDecimal.valueOf(this.payment.getAbsenceCount()));
			actualBreakfastFee = this.feePolicy.getTotalBreakfastFee()
					.subtract(penaltyFee);
		}
		return actualBreakfastFee;
	}

	private BigDecimal calculateTotalNormalFee() {
		return this.feePolicy
				.getFeePerNormalMeal()
				.multiply(
						BigDecimal.valueOf((this.feePolicy.getAvailableDays() - this.payment
								.getAbsenceCount())));
	}

	/**
	 * Total fee = Total normal meal fee + Total breakfast fee + Total static
	 * fees + Total dynamic fees
	 */
	private BigDecimal calculateTotalFee() {
		if (this.payment == null)
			return BigDecimal.valueOf(0);

		// Append meal fees
		BigDecimal totalFee = this.payment.getTotalNormalMealFee().add(
				this.payment.getTotalBreakfastFee());
		// Append static fees
		for (Fee fee : this.allFeesExceptMeal) {
			double actualAmount = BusinessLogicSolver.getInstance()
					.calculateFeeAmount(this.session, fee, this.feePolicy,
							this.payment);
			totalFee = totalFee.add(new BigDecimal(actualAmount));
		}

		return totalFee;
	}
}
