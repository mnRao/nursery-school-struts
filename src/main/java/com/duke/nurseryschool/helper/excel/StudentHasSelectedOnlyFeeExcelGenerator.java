package com.duke.nurseryschool.helper.excel;

import java.io.IOException;
import java.util.List;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.Helper;
import com.duke.nurseryschool.hibernate.bean.Month;

public class StudentHasSelectedOnlyFeeExcelGenerator extends ExcelManager {

	protected static final int CONTENT_START_ROW = 2;
	protected static final int CONTENT_LAST_COLUMN = 10;
	private final Month month;
	private final List<String> studentNames;
	private final String feeName;

	public StudentHasSelectedOnlyFeeExcelGenerator(WritableWorkbook workbook,
			Month month, String feeName, List<String> studentNames) {
		super(workbook);
		this.month = month;
		this.feeName = feeName;
		this.studentNames = studentNames;
	}

	public void addContent(int sheetNumber) throws IOException, WriteException {
		// Sheet for current class
		WritableSheet sheet = this.workbook.createSheet(this.feeName,
				sheetNumber);
		// Write contents
		this.addStyles();
		this.createHeaders(sheet);
		this.createContents(sheet);
	}

	private void createHeaders(WritableSheet sheet)
			throws RowsExceededException, WriteException {
		this.addCaption(sheet, HEADER_TOP_COLUMN, HEADER_TOP_ROW,
				this.generateTopMostHeaderLabel());

		this.addCaption(sheet, 0, HEADER_NORMAL_ROW,
				Helper.getI18N(Constant.I18N.EXCEL_HEADER_NORMAL_ORDER));
		this.addCaption(sheet, 1, HEADER_NORMAL_ROW,
				Helper.getI18N(Constant.I18N.EXCEL_HEADER_NORMAL_NAME));

		this.mergeHeaderCells(sheet);
	}

	private void mergeHeaderCells(WritableSheet sheet) throws WriteException,
			RowsExceededException {
		// All columns top row
		sheet.mergeCells(HEADER_TOP_COLUMN, HEADER_TOP_ROW,
				CONTENT_LAST_COLUMN, HEADER_TOP_ROW);
	}

	private void createContents(WritableSheet sheet)
			throws RowsExceededException, WriteException {
		int count = 1;
		int row = CONTENT_START_ROW;
		for (String name : this.studentNames) {
			this.addNumber(sheet, 0, row, count);
			this.addLabel(sheet, 1, row, name);
			count++;
			row++;
		}
	}

	private String generateTopMostHeaderLabel() {
		StringBuffer headerTop = new StringBuffer();
		headerTop
				.append(Helper
						.getI18N(Constant.I18N.EXCEL_FILE_PREFIX_TITLE_STUDENTHASSELECTEDONLYFEE))
				.append(Constant.SPACE)
				.append(Helper.getI18N(Constant.I18N.EXCEL_HEADER_MONTH))
				.append(Constant.SPACE).append(this.month.getMonthName())
				.append(Constant.SPACE)
				.append(Helper.getI18N(Constant.I18N.EXCEL_HEADER_YEAR))
				.append(Constant.SPACE).append(this.month.getYear())
				.append(Constant.SPACE);
		return headerTop.toString();
	}

}