package com.duke.nurseryschool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import jxl.Workbook;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.ExcelGenerator;
import com.duke.nurseryschool.helper.Helper;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.dao.FeePolicyDAO;
import com.duke.nurseryschool.hibernate.dao.MixedDAO;
import com.duke.nurseryschool.hibernate.dao.MonthDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ExcelGeneratorAction extends ActionSupport {

	private InputStream		fileInputStream;
	private String			fileName;

	private MixedDAO		mixedDAO		= new MixedDAO();
	private FeePolicyDAO	feePolicyDAO	= new FeePolicyDAO();
	private int				feePolicyId;
	private MonthDAO		monthDAO		= new MonthDAO();
	private int				monthId;

	public String singlePolicy() throws Exception {
		FeePolicy feePolicy = this.feePolicyDAO.getFeePolicy(this.feePolicyId);
		File tempFile = this.createTemporaryFile(feePolicy.getMonth()
				.getLabel());
		WritableWorkbook workbook = Workbook.createWorkbook(tempFile);
		this.addContentToExcelFile(feePolicy, workbook, 0);
		this.closeWorkbook(workbook);
		// Configure for download
		this.configureFileForDownload(tempFile);

		return Action.SUCCESS;
	}

	public String allMonthPolicies() throws Exception {
		Month month = this.monthDAO.getMonth(this.monthId);
		Set<FeePolicy> feePolicies = month.getFeePolicies();
		// If no class setup for this month then fail
		if (feePolicies.size() == 0) {
			throw new Exception(
					this.getText(Constant.I18N.ERROR_NO_FEEPOLICY_APPLIED));
		}

		File tempFile = this.createTemporaryFile(month.getLabel());
		int sheetNumber = 0;
		// Initialize work book the very first time
		WritableWorkbook workbook = Workbook.createWorkbook(tempFile);
		for (FeePolicy feePolicy : feePolicies) {
			this.addContentToExcelFile(feePolicy, workbook, sheetNumber);
			sheetNumber++;
		}
		this.closeWorkbook(workbook);
		this.configureFileForDownload(tempFile);

		return Action.SUCCESS;
	}

	private void closeWorkbook(WritableWorkbook workbook) throws IOException,
			WriteException {
		workbook.write();
		workbook.close();
	}

	private void addContentToExcelFile(FeePolicy feePolicy,
			WritableWorkbook workbook, int sheetNumber) throws IOException,
			WriteException, Exception {
		try {
			ExcelGenerator excelGenerator = new ExcelGenerator(workbook,
					feePolicy);
			excelGenerator.addContent(sheetNumber);
		}
		catch (IllegalStateException e) {
			// Handle illegal state exception for no payment to show on screen
			throw new Exception(
					this.getText(Constant.I18N.ERROR_NO_PAYMENT_APPLIED));
		}
	}

	private File createTemporaryFile(String monthLabel) throws IOException {
		// File for download
		File tempFile = File
				.createTempFile(this.generateExcelFilePrefix(monthLabel),
						Constant.EXCEL_SUFFIX);
		tempFile.deleteOnExit();// Delete when virtual machine terminates
		return tempFile;
	}

	private void configureFileForDownload(File tempFile)
			throws FileNotFoundException {
		this.fileInputStream = new FileInputStream(tempFile);
		this.fileName = tempFile.getName();
	}

	private String generateExcelFilePrefix(String monthLabel) {
		return Helper.getI18N(Constant.I18N.EXCEL_FILE_PREFIX_TITLE)
				+ Constant.SPACE + monthLabel
				+ Constant.PUNCTUATION_MARK.HYPHEN + System.currentTimeMillis();
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
