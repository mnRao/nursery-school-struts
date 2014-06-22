package com.duke.nurseryschool.helper.excel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.generated.I18N;
import com.duke.nurseryschool.helper.BusinessLogicSolver;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.Helper;
import com.duke.nurseryschool.helper.comparator.PaymentComparator;
import com.duke.nurseryschool.hibernate.HibernateUtil;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.FeeGroup;
import com.duke.nurseryschool.hibernate.bean.FeeMap;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.dao.MixedDAO;

public class PaymentExcelGenerator extends ExcelManager {

	private final Classes associatedClass;
	private final Month month;
	private final List<Payment> payments = new ArrayList<Payment>();
	private final List<FeeMap> feeMaps = new ArrayList<FeeMap>();
	private final FeePolicy feePolicy;

	private List<FeeGroup> feeGroups = new ArrayList<FeeGroup>();
	private List<Fee> fees = new ArrayList<Fee>();
	private List<Fee> staticFees = new ArrayList<Fee>();

	private int lastColumn;
	private int otherStartCol;

	private final Session session;
	private final Transaction transaction;
	private final MixedDAO mixedDAO = new MixedDAO();

	private BigDecimal totalFeeForClass = new BigDecimal(0);

	// Positions for elements
	private static final int HEADER_NORMAL_SPANNED_ROW = 2;
	private static final int CONTENT_START_ROW = 3;
	private static final int CONTENT_STATIC_FEES_START_COL = 5;

	private static final int TRIVIAL_LAST_COLUMNS_SIZE = 3;

	public PaymentExcelGenerator(WritableWorkbook workbook, FeePolicy feePolicy) throws IllegalStateException {
		super(workbook);// Work book

		// Init Hibernate session
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
		this.transaction = this.session.beginTransaction();

		this.feePolicy = feePolicy;
		// Assignment for comfortability
		this.associatedClass = this.feePolicy.getAssociatedClass();
		this.month = this.feePolicy.getMonth();
		this.feeMaps.addAll(this.feePolicy.getFeeMaps());
		this.payments.addAll(this.feePolicy.getPayments());
		this.getStaticData();

		if (this.payments.size() == 0) {
			throw new IllegalStateException("No payment applied. Please specify one first.");
		}

		// Sort students (via payments)
		Collections.sort(this.payments, new PaymentComparator());

		// Calculate columns
		this.calculateDataPositions();
	}

	public BigDecimal addContent(int sheetNumber) throws IOException, WriteException {
		// Sheet for current class
		WritableSheet sheet = this.workbook.createSheet(this.associatedClass.getCurrentName(), sheetNumber);
		// Write contents
		this.addStyles();
		this.createHeaders(sheet);
		this.createContents(sheet);

		return this.totalFeeForClass;
	}

	private void createHeaders(WritableSheet sheet) throws RowsExceededException, WriteException {
		this.addCaption(sheet, HEADER_TOP_COLUMN, HEADER_TOP_ROW, this.generateTopMostHeaderLabel(this.month.getMonthName(), this.month.getYear(), this.associatedClass.getLabel(), this.feePolicy));

		this.addCaption(sheet, 0, HEADER_NORMAL_ROW, Helper.getI18N(I18N.EXCEL_HEADER_NORMAL_ORDER));
		this.addCaption(sheet, 1, HEADER_NORMAL_ROW, Helper.getI18N(I18N.EXCEL_HEADER_NORMAL_NAME));

		this.addCaption(sheet, 2, HEADER_NORMAL_ROW, Helper.getI18N(I18N.EXCEL_HEADER_NORMAL_MEAL));
		this.addCaption(sheet, 2, HEADER_NORMAL_SPANNED_ROW, Helper.getI18N(I18N.EXCEL_HEADER_NORMAL_ABSENCECOUNT));
		this.addCaption(sheet, 3, HEADER_NORMAL_SPANNED_ROW, Helper.getI18N(I18N.EXCEL_HEADER_NORMAL_TOTALNORMALMEALFEE));
		this.addCaption(sheet, 4, HEADER_NORMAL_SPANNED_ROW, Helper.getI18N(I18N.EXCEL_HEADER_NORMAL_TOTALBREAKFASTFEE));

		// Static fees
		int dynamicCol = CONTENT_STATIC_FEES_START_COL;
		for (Fee staticFee : this.staticFees) {
			this.addCaption(sheet, dynamicCol, HEADER_NORMAL_ROW, staticFee.getName());
			dynamicCol++;
		}

		// Dynamic fees
		// int dynamicCol = CONTENT_DYNAMIC_FEES_START_COL;
		for (FeeGroup feeGroup : this.feeGroups) {
			this.addCaption(sheet, dynamicCol, HEADER_NORMAL_ROW, feeGroup.getName());
			for (Fee fee : feeGroup.getFees()) {
				this.addCaption(sheet, dynamicCol, HEADER_NORMAL_SPANNED_ROW, fee.getName());
				dynamicCol++;
			}
		}

		// Others
		this.addCaption(sheet, this.otherStartCol, HEADER_NORMAL_ROW, Helper.getI18N(I18N.EXCEL_HEADER_NORMAL_TOTAL));
		this.addCaption(sheet, this.otherStartCol + 1, HEADER_NORMAL_ROW, Helper.getI18N(I18N.EXCEL_HEADER_NORMAL_PAIDDATE));
		this.addCaption(sheet, this.otherStartCol + 2, HEADER_NORMAL_ROW, Helper.getI18N(I18N.EXCEL_HEADER_NORMAL_SIGNATURE));
		this.addCaption(sheet, this.otherStartCol + 3, HEADER_NORMAL_ROW, Helper.getI18N(I18N.EXCEL_HEADER_NORMAL_NOTE));

		// Merge cells
		this.mergeHeaderCells(sheet);
	}

	private void mergeHeaderCells(WritableSheet sheet) throws WriteException, RowsExceededException {
		// All columns top row
		sheet.mergeCells(HEADER_TOP_COLUMN, HEADER_TOP_ROW, this.lastColumn, HEADER_TOP_ROW);
		// 2-row height for 2 first items
		sheet.mergeCells(0, HEADER_NORMAL_ROW, 0, HEADER_NORMAL_SPANNED_ROW);
		sheet.mergeCells(1, HEADER_NORMAL_ROW, 1, HEADER_NORMAL_SPANNED_ROW);

		// Meal's items
		sheet.mergeCells(2, HEADER_NORMAL_ROW, CONTENT_STATIC_FEES_START_COL - 1, HEADER_NORMAL_ROW);
		// 2-row height for static fees
		for (int i = 0; i < this.staticFees.size(); i++) {
			sheet.mergeCells(CONTENT_STATIC_FEES_START_COL + i, HEADER_NORMAL_ROW, CONTENT_STATIC_FEES_START_COL + i, HEADER_NORMAL_SPANNED_ROW);
		}

		// Dynamic fees
		int dynamicCol = CONTENT_STATIC_FEES_START_COL + this.staticFees.size();
		for (FeeGroup feeGroup : this.feeGroups) {
			int limit = dynamicCol + feeGroup.getFees().size();
			sheet.mergeCells(dynamicCol, HEADER_NORMAL_ROW, limit - 1, HEADER_NORMAL_ROW);

			dynamicCol = limit;
		}

		// 2-row height for 4 last columns
		sheet.mergeCells(this.otherStartCol, HEADER_NORMAL_ROW, this.otherStartCol, HEADER_NORMAL_SPANNED_ROW);
		sheet.mergeCells(this.otherStartCol + 1, HEADER_NORMAL_ROW, this.otherStartCol + 1, HEADER_NORMAL_SPANNED_ROW);
		sheet.mergeCells(this.otherStartCol + 2, HEADER_NORMAL_ROW, this.otherStartCol + 2, HEADER_NORMAL_SPANNED_ROW);
		sheet.mergeCells(this.otherStartCol + 3, HEADER_NORMAL_ROW, this.otherStartCol + 3, HEADER_NORMAL_SPANNED_ROW);
	}

	private void createContents(WritableSheet sheet) throws RowsExceededException, WriteException {
		int count = 1;
		int row = CONTENT_START_ROW;
		for (Payment payment : this.payments) {
			this.addNumber(sheet, 0, row, count);
			this.addLabel(sheet, 1, row, payment.getStudent().getName());
			this.addNumber(sheet, 2, row, payment.getAbsenceCount());
			this.addNumber(sheet, 3, row, payment.getTotalNormalMealFee().doubleValue(), OBMITING_ZEROS, true);
			this.addNumber(sheet, 4, row, payment.getTotalBreakfastFee().doubleValue(), OBMITING_ZEROS, true);

			// Static fees
			int dynamicCol = CONTENT_STATIC_FEES_START_COL;
			for (Fee staticFee : this.staticFees) {
				double calculatedAmount = BusinessLogicSolver.getInstance().calculateFeeAmount(this.session, staticFee, this.feePolicy, payment);
				this.addNumber(sheet, dynamicCol, row, calculatedAmount, OBMITING_ZEROS, true);
				dynamicCol++;
			}
			// Dynamic fees
			// int dynamicCol = CONTENT_DYNAMIC_FEES_START_COL;
			for (FeeGroup feeGroup : this.feeGroups) {
				Set<Fee> groupFees = feeGroup.getFees();

				for (Fee fee : groupFees) {
					double calculatedAmount = BusinessLogicSolver.getInstance().calculateFeeAmount(this.session, fee, this.feePolicy, payment);
					this.addNumber(sheet, dynamicCol, row, calculatedAmount, OBMITING_ZEROS, true);
					dynamicCol++;
				}
			}

			// Other remaining contents
			this.addNumber(sheet, this.otherStartCol, row, payment.getTotalFee().doubleValue(), OBMITING_ZEROS, false);
			this.addLabel(sheet, this.lastColumn, row, payment.getNote());

			this.totalFeeForClass = this.totalFeeForClass.add(payment.getTotalFee());

			count++;
			row++;
		}

	}

	/* Generate label for top most header */
	private String generateTopMostHeaderLabel(int month, int year, String className, FeePolicy feePolicy) {
		StringBuffer headerTop = new StringBuffer();
		headerTop.append(Helper.getI18N(I18N.EXCEL_HEADER_TOP_PAYMENT, new String[] {
				Integer.toString(month), Integer.toString(year), Integer.toString(feePolicy.getAvailableDays()), className
		}));

		return headerTop.toString();
	}

	/* Positions for some critical elements */
	private void calculateDataPositions() {
		this.otherStartCol = CONTENT_STATIC_FEES_START_COL + this.fees.size();
		this.lastColumn = this.otherStartCol + TRIVIAL_LAST_COLUMNS_SIZE;
	}

	/**
	 * FeeGroup and Fee do not depend on FeePolicy
	 */
	@SuppressWarnings("unchecked")
	private void getStaticData() {
		try {
			this.feeGroups = this.session.createQuery(Constant.DATABASE_QUERY.ALL_FEE_GROUPS).list();
			this.fees = this.session.createQuery(Constant.DATABASE_QUERY.ALL_FEES).list();
			// this.staticFees = this.mixedDAO.getFeeByType(this.session,
			// FeeType.STATIC);
			this.staticFees = this.mixedDAO.getFeeWithNoGroup(this.session);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
