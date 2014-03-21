package com.duke.nurseryschool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.duke.nurseryschool.core.AuthenticationRequiredAction;
import com.duke.nurseryschool.helper.Constant;

public class HelpAction extends AuthenticationRequiredAction {

	private static final long serialVersionUID = 8096333986346450810L;

	private InputStream fileInputStream;
	private String fileName;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String userGuideDownload() throws FileNotFoundException {
		// Get file in www/ folder
		this.fileInputStream = new FileInputStream(ServletActionContext
				.getServletContext().getRealPath(Constant.USER_GUIDE_FILE_PATH));
		this.fileName = Constant.USER_GUIDE_FILE_NAME;

		return SUCCESS;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
