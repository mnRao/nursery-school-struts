package com.duke.nurseryschool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.write.WriteException;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.ExcelGenerator;
import com.duke.nurseryschool.helper.Helper;
import com.duke.nurseryschool.hibernate.bean.FeePolicy;
import com.duke.nurseryschool.hibernate.dao.FeePolicyDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ExcelGeneratorAction extends ActionSupport {

	private InputStream fileInputStream;
	private String fileName;

	private FeePolicyDAO feePolicyDAO = new FeePolicyDAO();
	private int feePolicyId;

	@Override
	public String execute() throws WriteException, IOException {
		FeePolicy feePolicy = this.feePolicyDAO.getFeePolicy(this.feePolicyId);

		// File for download
		File tempFile = File.createTempFile(
				this.generateExcelFilePrefix(feePolicy), Constant.EXCEL_SUFFIX);
		tempFile.deleteOnExit();// Delete when virtual machine terminates
		// TODO Handle illegal state exception for no payment to show on screen
		try {
			ExcelGenerator excelGenerator = new ExcelGenerator(
					tempFile.getAbsolutePath(), feePolicy);
			excelGenerator.write();
		}
		catch (IllegalStateException e) {
			this.addActionError(Constant.I18N.ERROR_NO_PAYMENT_APPLIED);
			return Action.ERROR;
		}

		// Configure for download
		this.fileInputStream = new FileInputStream(tempFile);
		this.fileName = tempFile.getName();

		return Action.SUCCESS;
	}

	private String generateExcelFilePrefix(FeePolicy feePolicy) {
		return Helper.getI18N(Constant.I18N.EXCEL_FILE_PREFIX_TITLE)
				+ Constant.SPACE + feePolicy.getMonth().getLabel()
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

}
