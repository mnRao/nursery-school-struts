package com.duke.nurseryschool.helper.excel;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.duke.nurseryschool.generated.I18N;
import com.duke.nurseryschool.helper.Helper;
import com.duke.nurseryschool.hibernate.bean.Month;

public class StudentHasBreakfastExcelGenerator extends ExcelManager {

	private static final int CONTENT_START_ROW = 2;
	private static final int CONTENT_LAST_COLUMN = 10;

	private final List<String> studentNames;
	private final Month month;

	public StudentHasBreakfastExcelGenerator(WritableWorkbook workbook,
			Month month, List<String> studentNames) {
		super(workbook);
		this.month = month;
		this.studentNames = studentNames;
		// Sort student name
		this.sortStudentNames();
	}

	public void addContent(int sheetNumber) throws IOException, WriteException {
		// Sheet for current class
		WritableSheet sheet = this.workbook.createSheet(
				Helper.getI18N(I18N.EXCEL_SHEET_TITLE_ALL), sheetNumber);
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
				Helper.getI18N(I18N.EXCEL_HEADER_NORMAL_ORDER));
		this.addCaption(sheet, 1, HEADER_NORMAL_ROW,
				Helper.getI18N(I18N.EXCEL_HEADER_NORMAL_NAME));

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
		headerTop.append(Helper.getI18N(
				I18N.EXCEL_HEADER_TOP_STUDENTHASBREAKFAST,
				new String[] {
						Integer.toString(this.month.getMonthName()),
						Integer.toString(this.month.getYear())
				}));
		return headerTop.toString();
	}

	private void sortStudentNames() {
		Collections.sort(this.studentNames, new Comparator<String>() {
			@Override
			public int compare(String string1, String string2) {
				return Helper.extractLastWord(string1).compareTo(
						Helper.extractLastWord(string2));
			}
		});
	}
}
