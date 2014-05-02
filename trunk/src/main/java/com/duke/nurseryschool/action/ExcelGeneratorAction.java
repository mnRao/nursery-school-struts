package com.duke.nurseryschool.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import jxl.Workbook;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.duke.nurseryschool.generated.I18N;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.FeeType;
import com.duke.nurseryschool.helper.Helper;
import com.duke.nurseryschool.helper.StatisticsBean;
import com.duke.nurseryschool.helper.comparator.StudentComparator;
import com.duke.nurseryschool.helper.excel.AttendanceChecklistExcelGenerator;
import com.duke.nurseryschool.helper.excel.PaymentExcelGenerator;
import com.duke.nurseryschool.helper.excel.StatisticsExcelGenerator;
import com.duke.nurseryschool.helper.excel.StudentHasBreakfastExcelGenerator;
import com.duke.nurseryschool.helper.excel.StudentHasSelectedOnlyFeeExcelGenerator;
import com.duke.nurseryschool.hibernate.bean.Fee;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.bean.Student;
import com.duke.nurseryschool.hibernate.dao.FeePolicyDAO;
import com.duke.nurseryschool.hibernate.dao.MixedDAO;
import com.duke.nurseryschool.hibernate.dao.MonthDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ExcelGeneratorAction extends ActionSupport {

	private static final long serialVersionUID = 1322893944355304755L;

	private InputStream fileInputStream;
	private String fileName;

	private final MixedDAO mixedDAO = new MixedDAO();
	private final FeePolicyDAO feePolicyDAO = new FeePolicyDAO();
	private int feePolicyId;
	private final MonthDAO monthDAO = new MonthDAO();
	private int monthId;

	/* Single sheet for the specified fee policy */
	public String singlePolicy() throws Exception {
		FeePolicy feePolicy = this.feePolicyDAO.getFeePolicy(this.feePolicyId);
		File tempFile = this.createTemporaryFile(this
				.generatePaymentExcelFilePrefix(this
						.generateMonthLabel(feePolicy.getMonth())));
		WritableWorkbook workbook = Workbook.createWorkbook(tempFile);
		this.addContentToPaymentExcelFile(feePolicy, workbook, 0);

		if (this.hasActionErrors())
			return Constant.ACTION_RESULT.MESSAGE;

		this.closeWorkbook(workbook);
		// Configure for download
		this.configureFileForDownload(tempFile);

		return Action.SUCCESS;
	}

	public String singleBreakfast() throws Exception {
		Month month = this.monthDAO.getMonth(this.monthId);
		File tempFile = this.createTemporaryFile(this
				.generateBreakfastExcelFilePrefix(this
						.generateMonthLabel(month)));
		WritableWorkbook workbook = Workbook.createWorkbook(tempFile);
		this.addContentToBreakfastExcelFile(workbook, 0, month,
				this.mixedDAO.getStudentsHavingBreakfast(this.monthId));
		this.closeWorkbook(workbook);
		// Configure for download
		this.configureFileForDownload(tempFile);

		return Action.SUCCESS;
	}

	public String allAttendanceChecklist() throws Exception {
		Month month = this.monthDAO.getMonth(this.monthId);
		Set<FeePolicy> feePolicies = month.getFeePolicies();
		// If no class setup for this month then fail
		if (feePolicies.size() == 0) {
			throw new Exception(this.getText(I18N.ERROR_NO_FEEPOLICY_APPLIED));
		}

		File tempFile = this.createTemporaryFile(this
				.generateAttendanceChecklistExcelFilePrefix(this
						.generateMonthLabel(month)));
		int sheetNumber = 0;
		// Initialize work book the very first time
		WritableWorkbook workbook = Workbook.createWorkbook(tempFile);
		for (FeePolicy feePolicy : feePolicies) {
			this.addContentToAttendanceChecklistExcelFile(workbook,
					sheetNumber, feePolicy);
			sheetNumber++;
		}

		this.closeWorkbook(workbook);
		// Configure for download
		this.configureFileForDownload(tempFile);

		return Action.SUCCESS;
	}

	public String allSelectedOnlyFee() throws Exception {
		Month month = this.monthDAO.getMonth(this.monthId);
		File tempFile = this.createTemporaryFile(this
				.generateSelectedOnlyExcelFilePrefix(this
						.generateMonthLabel(month)));
		WritableWorkbook workbook = Workbook.createWorkbook(tempFile);
		int sheetNumber = 0;
		List<Fee> selectedOnlyFees = this.mixedDAO
				.getFeeByType(FeeType.SELECTED_ONLY);
		for (Fee fee : selectedOnlyFees) {
			List<String> studentNamesInFee = this.mixedDAO
					.getStudentsHavingSelectedOnlyFee(this.monthId,
							fee.getFeeId());
			this.addContentToSelectedOnlyFeeExcelFile(workbook, sheetNumber,
					month, fee.getName(), studentNamesInFee);
			sheetNumber++;
		}
		// Prevent IndexOutOfBound exception: sheets without content been
		// written
		// TODO THROW EXCEPTION FOR USER NOTICE
		if (selectedOnlyFees.size() > 0) {
			this.closeWorkbook(workbook);
		}
		this.configureFileForDownload(tempFile);

		return Action.SUCCESS;
	}

	public String allMonthPolicies() throws Exception {
		Month month = this.monthDAO.getMonth(this.monthId);
		Set<FeePolicy> feePolicies = month.getFeePolicies();
		// If no class setup for this month then fail
		if (feePolicies.size() == 0) {
			throw new Exception(this.getText(I18N.ERROR_NO_FEEPOLICY_APPLIED));
		}

		File tempFile = this
				.createTemporaryFile(this.generatePaymentExcelFilePrefix(this
						.generateMonthLabel(month)));
		int sheetNumber = 0;
		StatisticsBean statisticsBean = new StatisticsBean(month);
		// Initialize work book the very first time
		WritableWorkbook workbook = Workbook.createWorkbook(tempFile);
		for (FeePolicy feePolicy : feePolicies) {
			BigDecimal totalFeeForClass = this.addContentToPaymentExcelFile(
					feePolicy, workbook, sheetNumber);

			if (this.hasActionErrors())
				return Constant.ACTION_RESULT.MESSAGE;

			statisticsBean.addTotalFee(feePolicy.getAssociatedClass(),
					totalFeeForClass);
			sheetNumber++;
		}

		// Last sheet - Statistics
		this.addStatisticContentToPaymentExcelFile(statisticsBean, workbook,
				sheetNumber);

		this.closeWorkbook(workbook);
		this.configureFileForDownload(tempFile);

		return Action.SUCCESS;
	}

	private void closeWorkbook(WritableWorkbook workbook) throws IOException,
			WriteException {
		if (workbook != null) {
			workbook.write();
			workbook.close();
		}
	}

	private void addStatisticContentToPaymentExcelFile(
			StatisticsBean statisticsBean, WritableWorkbook workbook,
			int sheetNumber) throws Exception {
		try {
			StatisticsExcelGenerator excelGenerator = new StatisticsExcelGenerator(
					workbook, statisticsBean);
			excelGenerator.addContent(sheetNumber);
		}
		catch (IllegalStateException e) {
			// Handle illegal state exception for no payment to show on screen
			throw new Exception(this.getText(I18N.ERROR_NO_PAYMENT_APPLIED));
		}
	}

	private BigDecimal addContentToPaymentExcelFile(FeePolicy feePolicy,
			WritableWorkbook workbook, int sheetNumber) throws IOException,
			WriteException, Exception {
		try {
			PaymentExcelGenerator excelGenerator = new PaymentExcelGenerator(
					workbook, feePolicy);
			return excelGenerator.addContent(sheetNumber);
		}
		catch (IllegalStateException e) {
			// Handle illegal state exception for no payment to show on screen
			this.addActionError(this.getText(I18N.ERROR_NO_PAYMENT_APPLIED));
			return BigDecimal.valueOf(0);
		}
	}

	private void addContentToBreakfastExcelFile(WritableWorkbook workbook,
			int sheetNumber, Month month, List<Student> students)
			throws IOException, WriteException, Exception {
		try {
			StudentHasBreakfastExcelGenerator excelGenerator = new StudentHasBreakfastExcelGenerator(
					workbook, month, students);
			excelGenerator.addContent(sheetNumber);
		}
		catch (IllegalStateException e) {
			// Handle illegal state exception for no payment to show on screen
			throw new Exception(this.getText(I18N.ERROR_NO_PAYMENT_APPLIED));
		}
	}

	private void addContentToAttendanceChecklistExcelFile(
			WritableWorkbook workbook, int sheetNumber, FeePolicy feePolicy)
			throws IOException, WriteException, Exception {
		Set<Student> students = feePolicy.getAssociatedClass().getStudents();
		List<String> studentNames = new ArrayList<String>();
		for (Student student : students) {
			studentNames.add(student.getName());
		}

		try {
			AttendanceChecklistExcelGenerator excelGenerator = new AttendanceChecklistExcelGenerator(
					workbook, feePolicy, studentNames);
			excelGenerator.addContent(sheetNumber);
		}
		catch (IllegalStateException e) {
			// Handle illegal state exception for no payment to show on screen
			throw new Exception(this.getText(I18N.ERROR_NO_PAYMENT_APPLIED));
		}
	}

	private void addContentToSelectedOnlyFeeExcelFile(
			WritableWorkbook workbook, int sheetNumber, Month month,
			String feeName, List<String> studentNames) throws IOException,
			WriteException, Exception {
		try {
			StudentHasSelectedOnlyFeeExcelGenerator excelGenerator = new StudentHasSelectedOnlyFeeExcelGenerator(
					workbook, month, feeName, studentNames);
			excelGenerator.addContent(sheetNumber);
		}
		catch (IllegalStateException e) {
			// Handle illegal state exception for no payment to show on screen
			throw new Exception(this.getText(I18N.ERROR_NO_PAYMENT_APPLIED));
		}
	}

	private File createTemporaryFile(String prefix) throws IOException {
		// File for download
		File tempFile = File.createTempFile(prefix, Constant.EXCEL_SUFFIX);
		tempFile.deleteOnExit();// Delete when virtual machine terminates
		return tempFile;
	}

	private void configureFileForDownload(File tempFile)
			throws FileNotFoundException, UnsupportedEncodingException {
		this.fileInputStream = new FileInputStream(tempFile);
		// Encode file name in UTF-8
		this.fileName = URLEncoder.encode(tempFile.getName(), "UTF-8");
	}

	private String generatePaymentExcelFilePrefix(String monthLabel) {
		return Helper.getI18N(I18N.EXCEL_FILE_PREFIX_TITLE) + Constant.SPACE
				+ monthLabel + Constant.PUNCTUATION_MARK.HYPHEN
				+ System.currentTimeMillis();
	}

	private String generateBreakfastExcelFilePrefix(String monthLabel) {
		return Helper.getI18N(I18N.EXCEL_FILE_PREFIX_TITLE_STUDENTHASBREAKFAST)
				+ Constant.SPACE + monthLabel
				+ Constant.PUNCTUATION_MARK.HYPHEN + System.currentTimeMillis();
	}

	private String generateAttendanceChecklistExcelFilePrefix(String monthLabel) {
		return Helper.getI18N(I18N.EXCEL_FILE_PREFIX_TITLE_ATTENDANCECHECKLIST)
				+ Constant.SPACE + monthLabel
				+ Constant.PUNCTUATION_MARK.HYPHEN + System.currentTimeMillis();
	}

	private String generateSelectedOnlyExcelFilePrefix(String monthLabel) {
		return Helper
				.getI18N(I18N.EXCEL_FILE_PREFIX_TITLE_STUDENTHASSELECTEDONLYFEE)
				+ Constant.SPACE
				+ monthLabel
				+ Constant.PUNCTUATION_MARK.HYPHEN + System.currentTimeMillis();
	}

	private String generateMonthLabel(Month month) {
		return month.getYear() + Constant.PUNCTUATION_MARK.HYPHEN
				+ month.getMonthName();
	}

	public InputStream getFileInputStream() {
		return this.fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFeePolicyId() {
		return this.feePolicyId;
	}

	public void setFeePolicyId(int feePolicyId) {
		this.feePolicyId = feePolicyId;
	}

	public int getMonthId() {
		return this.monthId;
	}

	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}

}
