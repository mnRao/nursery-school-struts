package com.duke.nurseryschool.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.helper.excel.ExcelManager;
import com.duke.nurseryschool.hibernate.HibernateUtil;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.FeeGroup;
import com.duke.nurseryschool.hibernate.bean.FeeMap;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.dao.MixedDAO;

public class ExcelGenerator extends ExcelManager {

	private Classes				associatedClass;
	private Month				month;
	private List<Payment>		payments						= new ArrayList<Payment>();
	private List<FeeMap>		feeMaps							= new ArrayList<FeeMap>();
	private FeePolicy			feePolicy;

	private List<FeeGroup>		feeGroups						= new ArrayList<FeeGroup>();
	private List<Fee>			fees							= new ArrayList<Fee>();
	private List<Fee>			staticFees						= new ArrayList<Fee>();

	private int					lastColumn;
	// private int extraFeeStartCol;
	private int					otherStartCol;

	private Session				session;
	private Transaction			transaction;
	private MixedDAO			mixedDAO						= new MixedDAO();

	// Positions for elements
	private static final int	HEADER_TOP_ROW					= 0;
	private static final int	HEADER_TOP_COLUMN				= 0;
	private static final int	HEADER_NORMAL_ROW				= 1;
	private static final int	HEADER_NORMAL_SPANNED_ROW		= 2;
	private static final int	CONTENT_START_ROW				= 3;
	private static final int	CONTENT_STATIC_FEES_START_COL	= 5;

	private static final int	TRIVIAL_LAST_COLUMNS_SIZE		= 3;

	public ExcelGenerator(WritableWorkbook workbook, FeePolicy feePolicy)
			throws IllegalStateException {
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
			throw new IllegalStateException(
					"No payment applied. Please specify one first.");
		}

		// Calculate columns
		this.calculateDataPositions();
	}

	public void addContent(int sheetNumber) throws IOException, WriteException {
		// Sheet for current class
		WritableSheet sheet = this.workbook.createSheet(
				this.associatedClass.getCurrentName(), sheetNumber);
		// Write contents
		this.addStyles();
		this.createHeaders(sheet);
		this.createContents(sheet);
	}

	private void createHeaders(WritableSheet sheet)
			throws RowsExceededException, WriteException {
		this.addCaption(sheet, HEADER_TOP_COLUMN, HEADER_TOP_ROW, this
				.generateTopMostHeaderLabel(this.month.getMonthName(),
						this.month.getYear(), this.associatedClass.getLabel(),
						this.feePolicy));

		this.addCaption(sheet, 0, HEADER_NORMAL_ROW, "Order");
		this.addCaption(sheet, 1, HEADER_NORMAL_ROW, "Full name");

		this.addCaption(sheet, 2, HEADER_NORMAL_ROW, "Meal");
		this.addCaption(sheet, 2, HEADER_NORMAL_SPANNED_ROW, "Absence count");
		this.addCaption(sheet, 3, HEADER_NORMAL_SPANNED_ROW,
				"Total normal meal fee");
		this.addCaption(sheet, 4, HEADER_NORMAL_SPANNED_ROW,
				"Total breakfast fee");

		// Static fees
		int dynamicCol = CONTENT_STATIC_FEES_START_COL;
		for (Fee staticFee : this.staticFees) {
			this.addCaption(sheet, dynamicCol, HEADER_NORMAL_ROW,
					staticFee.getName());
			dynamicCol++;
		}

		// Dyamic fees
		// int dynamicCol = CONTENT_DYNAMIC_FEES_START_COL;
		int feeCount = 0;
		for (FeeGroup feeGroup : this.feeGroups) {
			this.addCaption(sheet, dynamicCol, HEADER_NORMAL_ROW,
					feeGroup.getName());
			for (Fee fee : feeGroup.getFees()) {
				this.addCaption(sheet, dynamicCol, HEADER_NORMAL_SPANNED_ROW,
						fee.getName());
				dynamicCol++;
				feeCount++;
			}
		}

		// Others
		this.addCaption(sheet, this.otherStartCol, HEADER_NORMAL_ROW, "Total");
		this.addCaption(sheet, this.otherStartCol + 1, HEADER_NORMAL_ROW,
				"Paid Date");
		this.addCaption(sheet, this.otherStartCol + 2, HEADER_NORMAL_ROW,
				"Signature");
		this.addCaption(sheet, this.otherStartCol + 3, HEADER_NORMAL_ROW,
				"Note");

		// Merge cells
		this.mergeHeaderCells(sheet);
	}

	private void mergeHeaderCells(WritableSheet sheet) throws WriteException,
			RowsExceededException {
		// All columns top row
		sheet.mergeCells(HEADER_TOP_COLUMN, HEADER_TOP_ROW, this.lastColumn,
				HEADER_TOP_ROW);
		// 2-row height for 2 first items
		sheet.mergeCells(0, HEADER_NORMAL_ROW, 0, HEADER_NORMAL_SPANNED_ROW);
		sheet.mergeCells(1, HEADER_NORMAL_ROW, 1, HEADER_NORMAL_SPANNED_ROW);

		// Meal's items
		sheet.mergeCells(2, HEADER_NORMAL_ROW,
				CONTENT_STATIC_FEES_START_COL - 1, HEADER_NORMAL_ROW);
		// 2-row height for static fees
		for (int i = 0; i < this.staticFees.size(); i++) {
			sheet.mergeCells(CONTENT_STATIC_FEES_START_COL + i,
					HEADER_NORMAL_ROW, CONTENT_STATIC_FEES_START_COL + i,
					HEADER_NORMAL_SPANNED_ROW);
		}

		// Dynamic fees
		int dynamicCol = CONTENT_STATIC_FEES_START_COL + this.staticFees.size();
		for (FeeGroup feeGroup : this.feeGroups) {
			int limit = dynamicCol + feeGroup.getFees().size();
			sheet.mergeCells(dynamicCol, HEADER_NORMAL_ROW, limit - 1,
					HEADER_NORMAL_ROW);

			dynamicCol = limit;
		}

		// 2-row height for 4 last columns
		sheet.mergeCells(this.otherStartCol, HEADER_NORMAL_ROW,
				this.otherStartCol, HEADER_NORMAL_SPANNED_ROW);
		sheet.mergeCells(this.otherStartCol + 1, HEADER_NORMAL_ROW,
				this.otherStartCol + 1, HEADER_NORMAL_SPANNED_ROW);
		sheet.mergeCells(this.otherStartCol + 2, HEADER_NORMAL_ROW,
				this.otherStartCol + 2, HEADER_NORMAL_SPANNED_ROW);
		sheet.mergeCells(this.otherStartCol + 3, HEADER_NORMAL_ROW,
				this.otherStartCol + 3, HEADER_NORMAL_SPANNED_ROW);
	}

	private void createContents(WritableSheet sheet)
			throws RowsExceededException, WriteException {
		int count = 1;
		int row = CONTENT_START_ROW;
		for (Payment payment : this.payments) {
			this.addNumber(sheet, 0, row, count);
			this.addLabel(sheet, 1, row, payment.getStudent().getName());
			this.addNumber(sheet, 2, row, payment.getAbsenceCount());
			this.addNumber(sheet, 3, row, payment.getTotalNormalMealFee()
					.doubleValue());
			this.addNumber(sheet, 4, row, payment.getTotalBreakfastFee()
					.doubleValue());

			// Static fees
			int dynamicCol = CONTENT_STATIC_FEES_START_COL;
			for (Fee staticFee : this.staticFees) {
				double calculatedAmount = BusinessLogicSolver
						.calculateFeeAmount(this.session, staticFee,
								this.feePolicy, payment);
				this.addNumber(sheet, dynamicCol, row, calculatedAmount);
				dynamicCol++;
			}
			// Dynamic fees
			// int dynamicCol = CONTENT_DYNAMIC_FEES_START_COL;
			for (FeeGroup feeGroup : this.feeGroups) {
				Set<Fee> groupFees = feeGroup.getFees();

				for (Fee fee : groupFees) {
					double calculatedAmount = BusinessLogicSolver
							.calculateFeeAmount(this.session, fee,
									this.feePolicy, payment);
					this.addNumber(sheet, dynamicCol, row, calculatedAmount);
					dynamicCol++;
				}
			}

			// Other remaining contents
			this.addNumber(sheet, this.otherStartCol, row, payment
					.getTotalFee().doubleValue());
			this.addLabel(sheet, this.lastColumn, row, payment.getNote());

			count++;
			row++;
		}

	}

	/* Generate label for top most header */
	private String generateTopMostHeaderLabel(int month, int year,
			String className, FeePolicy feePolicy) {
		StringBuffer headerTop = new StringBuffer();
		headerTop.append(Helper.getI18N(Constant.I18N.EXCEL_HEADER_PAYMENT))
				.append(Constant.SPACE)
				.append(Helper.getI18N(Constant.I18N.EXCEL_HEADER_MONTH))
				.append(Constant.SPACE).append(month).append(Constant.SPACE)
				.append(Helper.getI18N(Constant.I18N.EXCEL_HEADER_YEAR))
				.append(Constant.SPACE).append(year).append(Constant.SPACE)
				.append(Constant.PUNCTUATION_MARK.PARENTHESIS_OPEN)
				.append(feePolicy.getAvailableDays()).append(Constant.SPACE)
				.append(Helper.getI18N(Constant.I18N.EXCEL_HEADER_DAYS))
				.append(Constant.PUNCTUATION_MARK.PARENTHESIS_CLOSE)
				.append(Constant.SPACE)
				.append(Helper.getI18N(Constant.I18N.EXCEL_HEADER_CLASS))
				.append(Constant.SPACE).append(className);

		return headerTop.toString();
	}

	/* Positions for some critical elements */
	private void calculateDataPositions() {
		this.otherStartCol = CONTENT_STATIC_FEES_START_COL + this.fees.size();
		this.lastColumn = this.otherStartCol + this.TRIVIAL_LAST_COLUMNS_SIZE;
	}

	/**
	 * FeeGroup and Fee do not depend on FeePolicy
	 */
	public void getStaticData() {
		try {
			this.feeGroups = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_FEE_GROUPS).list();
			this.fees = this.session.createQuery(
					Constant.DATABASE_QUERY.ALL_FEES).list();
			this.staticFees = this.mixedDAO.getFeeByType(this.session,
					FeeType.STATIC);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
