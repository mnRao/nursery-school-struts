package com.duke.nurseryschool.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duke.nurseryschool.hibernate.HibernateUtil;
import com.duke.nurseryschool.hibernate.bean.Classes;
import com.duke.nurseryschool.hibernate.bean.FeeMap;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.bean.Payment;
import com.duke.nurseryschool.hibernate.bean.Student;
import com.duke.nurseryschool.hibernate.bean.AlternativeFeeMap;
import com.duke.nurseryschool.hibernate.bean.embedded.ClassMonth;

public class ExcelGenerator {
	private WritableCellFormat timesBold;
	private WritableCellFormat times;
	private String outputFile;

	// private FeeDetails feeDetails;
	private Classes associatedClass;
	private Month month;
	private List<AlternativeFeeMap> subjectFeeMaps = new ArrayList<AlternativeFeeMap>();
	private List<FeeMap> extraFeeMaps = new ArrayList<FeeMap>();
	private FeePolicy feePolicy;

	private int lastCol;
	private int extraFeeStartCol;
	private int otherStartCol;

	private Session session;
	private Transaction transaction;

	private static final int HEADER_TOP_ROW = 0;
	private static final int HEADER_TOP_COLUMN = 0;
	private static final int HEADER_NORMAL_ROW = 1;
	private static final int HEADER_NORMAL_SPANNED_ROW = 2;
	private static final int CONTENT_START_ROW = 3;
	private static final int CONTENT_SUBJECT_START_COL = 6;

	public ExcelGenerator(String outpuFile, FeePolicy feePolicy)
			throws IllegalStateException {
		this.outputFile = outpuFile;
		this.feePolicy = feePolicy;
		this.associatedClass = this.feePolicy.getAssociatedClass();
		this.month = this.feePolicy.getMonth();
		// this.subjectFeeMaps.addAll(this.feePolicy.getSubjectFeeMaps());
		// this.extraFeeMaps.addAll(this.feePolicy.getExtraFeeMaps());

		// Match policy & payment
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
		this.transaction = this.session.beginTransaction();
		this.assignFeePolicy(this.associatedClass, this.month);

		// Calculate columns
		this.calculateData();
	}

	public void write() throws IOException, WriteException {
		File file = new File(this.outputFile);
		WritableWorkbook workbook = Workbook.createWorkbook(file);
		WritableSheet sheet = workbook.createSheet(
				this.associatedClass.getCurrentName(), 0);

		this.addStyles();
		this.createHeaders(sheet);
		this.createContents(sheet);

		workbook.write();
		workbook.close();
	}

	private void createHeaders(WritableSheet sheet)
			throws RowsExceededException, WriteException {
		int lastColumn = 12;

		// TODO
		this.addCaption(sheet, HEADER_TOP_COLUMN, HEADER_TOP_ROW, this
				.calculateHeaderTopMost(this.month.getMonthName(),
						this.month.getYear(), this.associatedClass.getLabel(),
						this.feePolicy));

		this.addCaption(sheet, 0, HEADER_NORMAL_ROW, "Order");
		this.addCaption(sheet, 1, HEADER_NORMAL_ROW, "Full name");
		this.addCaption(sheet, 2, HEADER_NORMAL_ROW, "Study fee");

		this.addCaption(sheet, 3, HEADER_NORMAL_ROW, "Meal");
		this.addCaption(sheet, 3, HEADER_NORMAL_SPANNED_ROW, "Absence count");
		this.addCaption(sheet, 4, HEADER_NORMAL_SPANNED_ROW,
				"Total normal meal fee");
		this.addCaption(sheet, 5, HEADER_NORMAL_SPANNED_ROW,
				"Total breakfast fee");

		// // Subjects
		// int dynamicCol = CONTENT_SUBJECT_START_COL;
		// this.addCaption(sheet, CONTENT_SUBJECT_START_COL, HEADER_NORMAL_ROW,
		// "Subjects");
		// for (AlternativeFeeMap subjectFeeMap : this.subjectFeeMaps) {
		// this.addCaption(sheet, dynamicCol, HEADER_NORMAL_SPANNED_ROW,
		// subjectFeeMap.getSubjectFee().getSubject().getName());
		// dynamicCol++;
		// }
		// // Extra fees
		// this.addCaption(sheet, this.extraFeeStartCol, HEADER_NORMAL_ROW,
		// "Extra");
		// for (FeeMap extraFeeMap : this.extraFeeMaps) {
		// this.addCaption(sheet, dynamicCol, HEADER_NORMAL_SPANNED_ROW,
		// extraFeeMap.getFeeDetailsExtraFee().getExtraFeeType()
		// .getName());
		// dynamicCol++;
		// }

		// Others
		this.addCaption(sheet, this.otherStartCol, HEADER_NORMAL_ROW, "Total");
		this.addCaption(sheet, this.otherStartCol + 1, HEADER_NORMAL_ROW,
				"Paid Date");
		this.addCaption(sheet, this.otherStartCol + 2, HEADER_NORMAL_ROW,
				"Signature");
		this.addCaption(sheet, this.otherStartCol + 3, HEADER_NORMAL_ROW,
				"Note");

		// Merge cells
		sheet.mergeCells(HEADER_TOP_COLUMN, HEADER_TOP_ROW, lastColumn,
				HEADER_TOP_ROW);
		sheet.mergeCells(0, HEADER_NORMAL_ROW, 0, HEADER_NORMAL_SPANNED_ROW);
		sheet.mergeCells(1, HEADER_NORMAL_ROW, 1, HEADER_NORMAL_SPANNED_ROW);
		sheet.mergeCells(2, HEADER_NORMAL_ROW, 2, HEADER_NORMAL_SPANNED_ROW);
		// sheet.mergeCells(3, HEADER_NORMAL_ROW, 3, HEADER_NORMAL_SPANNED_ROW);

		sheet.mergeCells(3, HEADER_NORMAL_ROW, CONTENT_SUBJECT_START_COL - 1,
				HEADER_NORMAL_ROW);
		sheet.mergeCells(CONTENT_SUBJECT_START_COL, HEADER_NORMAL_ROW,
				this.extraFeeStartCol - 1, HEADER_NORMAL_ROW);
		sheet.mergeCells(this.extraFeeStartCol, HEADER_NORMAL_ROW,
				this.otherStartCol - 1, HEADER_NORMAL_ROW);

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
		for (Student student : this.associatedClass.getStudents()) {
			// Get payment
			// Payment payment = this.getPayment(student.getStudentId(),
			// this.feeDetails.getFeeDetailsId());
			//
			// this.addNumber(sheet, 0, row, count);
			// this.addLabel(sheet, 1, row, student.getName());
			// this.addNumber(sheet, 2, row,
			// this.feeDetails.getBasicStudyFee());
			// this.addNumber(sheet, 3, row, payment.getAbsenceCount());
			// this.addNumber(sheet, 4, row, payment.getTotalNormalMealFee());
			// this.addNumber(sheet, 5, row, payment.getTotalBreakfastFee());
			//
			// // Subjects
			// int dynamicCol = CONTENT_SUBJECT_START_COL;
			// for (AlternativeFeeMap subjectFeeMap : this.subjectFeeMaps) {
			// this.addNumber(sheet, dynamicCol, row,
			// subjectFeeMap.getAmount());
			// dynamicCol++;
			// }
			// // Extra fees
			// dynamicCol = this.extraFeeStartCol;
			// for (FeeMap extraFeeMap : this.extraFeeMaps) {
			// this.addNumber(sheet, dynamicCol, row, extraFeeMap.getAmount());
			// dynamicCol++;
			// }
			//
			// // Other remaining contents
			// this.addNumber(sheet, this.otherStartCol, row,
			// payment.getTotalFee());
			// this.addLabel(sheet, this.lastCol, row, payment.getNote());

			count++;
			row++;
		}

	}

	private void addCaption(WritableSheet sheet, int column, int row,
			String text) throws RowsExceededException, WriteException {
		Label label = new Label(column, row, text, this.timesBold);
		sheet.addCell(label);
	}

	private void addNumber(WritableSheet sheet, int column, int row,
			double amount) throws RowsExceededException, WriteException {
		Number number = new Number(column, row, amount, this.times);
		sheet.addCell(number);
	}

	private void addLabel(WritableSheet sheet, int column, int row, String text)
			throws RowsExceededException, WriteException {
		Label label = new Label(column, row, text, this.times);
		sheet.addCell(label);
	}

	private void addStyles() throws WriteException {
		WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
		this.times = new WritableCellFormat(times10pt);
		this.times.setAlignment(Alignment.CENTRE);
		this.times.setWrap(true);

		WritableFont times10ptBold = new WritableFont(WritableFont.TIMES, 10,
				WritableFont.BOLD, false);
		this.timesBold = new WritableCellFormat(times10ptBold);
		this.timesBold.setAlignment(Alignment.CENTRE);
		this.timesBold.setWrap(true);

		CellView cv = new CellView();
		cv.setFormat(this.times);
		cv.setFormat(this.timesBold);
		cv.setAutosize(true);
	}

	private String calculateHeaderTopMost(int month, int year,
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

	private void calculateData() {
		this.extraFeeStartCol = CONTENT_SUBJECT_START_COL
				+ this.subjectFeeMaps.size();
		this.otherStartCol = this.extraFeeStartCol + this.extraFeeMaps.size();
		this.lastCol = this.otherStartCol + 3;
	}

	public void assignFeePolicy(Classes associatedClass, Month month) {
		ClassMonth classMonth = new ClassMonth(associatedClass, month);
		FeePolicy feePolicy = null;
		try {
			feePolicy = (FeePolicy) this.session.get(FeePolicy.class,
					classMonth);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// Assignment
		this.feePolicy = feePolicy;
		if (this.feePolicy == null) {
			throw new IllegalStateException(
					"No fee policy applied. Please specify one first.");
		}
	}

	// public Payment getPayment(int studentId, int feePolicyId) {
	// Student student = (Student) this.session.get(Student.class,
	// Integer.valueOf(studentId));
	// FeePolicy feeDetails = (FeePolicy) this.session.get(FeePolicy.class,
	// Integer.valueOf(feePolicyId));
	// StudentFeePolicy studentFeeDetails = new StudentFeePolicy(student,
	// feeDetails);
	//
	// Payment payment = null;
	// try {
	// payment = (Payment) this.session.get(Payment.class,
	// studentFeeDetails);
	// }
	// catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// if (payment == null) {
	// throw new IllegalStateException(
	// "No payment appliced. Please specify one first.");
	// }
	//
	// return payment;
	// }
}
