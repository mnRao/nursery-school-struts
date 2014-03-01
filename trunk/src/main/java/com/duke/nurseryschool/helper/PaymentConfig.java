package com.duke.nurseryschool.helper;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.duke.nurseryschool.hibernate.HibernateUtil;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.dao.MixedDAO;

public class PaymentConfig {
	// Params
	private Payment payment;
	private FeePolicy feePolicy;

	// Auto-finding
	private List<Fee> allFeesExceptMeal;

	private MixedDAO mixedDAO = new MixedDAO();
	private Session session;
	private Transaction transaction;

	public PaymentConfig(Payment payment, FeePolicy feePolicy) {
		// Init Hibernate session
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
		this.transaction = this.session.beginTransaction();

		this.payment = payment;
		this.feePolicy = feePolicy;
		// Auto get
		this.allFeesExceptMeal = this.mixedDAO.getFees(this.session);
	}

	/**
	 * Total fee = Total normal meal fee + Total breakfast fee + Total static
	 * fees + Total dynamic fees
	 */
	public BigDecimal calculateTotalFee() {
		if (this.payment == null
				|| this.payment.getTotalBreakfastFee() == BigDecimal.ZERO
				|| this.payment.getTotalNormalMealFee() == BigDecimal.ZERO)
			return BigDecimal.valueOf(0);

		// Append meal fees
		BigDecimal totalFee = this.payment.getTotalNormalMealFee().add(
				this.payment.getTotalBreakfastFee());
		// Append static fees
		for (Fee fee : this.allFeesExceptMeal) {
			double actualAmount = BusinessLogicSolver.calculateFeeAmount(
					this.mixedDAO.getSession(), fee, this.feePolicy,
					this.payment);
			totalFee.add(BigDecimal.valueOf(actualAmount));
		}

		return totalFee;
	}
}
