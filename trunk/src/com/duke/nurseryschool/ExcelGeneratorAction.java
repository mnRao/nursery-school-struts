package com.duke.nurseryschool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.write.WriteException;

import com.duke.nurseryschool.helper.ExcelGenerator;
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
		this.fileName = "MyFile.xls";
		String filePath = "C:\\" + this.fileName;
		this.fileInputStream = new FileInputStream(new File(filePath));

		ExcelGenerator excelGenerator = new ExcelGenerator(filePath, feePolicy);
		excelGenerator.write();

		return Action.SUCCESS;
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
