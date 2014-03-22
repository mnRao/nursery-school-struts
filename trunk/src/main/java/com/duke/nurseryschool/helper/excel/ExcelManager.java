package com.duke.nurseryschool.helper.excel;

import jxl.CellView;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public abstract class ExcelManager {

	protected static final int HEADER_TOP_ROW = 0;
	protected static final int HEADER_TOP_COLUMN = 0;
	protected static final int HEADER_NORMAL_ROW = 1;

	// Excel's configurations
	protected WritableCellFormat timesBold;
	protected WritableCellFormat times;
	protected WritableWorkbook workbook;

	public ExcelManager(WritableWorkbook workbook) {
		this.workbook = workbook;
	}

	protected void addCaption(WritableSheet sheet, int column, int row,
			String text) throws RowsExceededException, WriteException {
		Label label = new Label(column, row, text, this.timesBold);
		sheet.addCell(label);
	}

	protected void addNumber(WritableSheet sheet, int column, int row,
			double amount) throws RowsExceededException, WriteException {
		Number number = new Number(column, row, amount, this.times);
		sheet.addCell(number);
	}

	protected void addLabel(WritableSheet sheet, int column, int row,
			String text) throws RowsExceededException, WriteException {
		Label label = new Label(column, row, text, this.times);
		sheet.addCell(label);
	}

	/* Define styles */
	protected void addStyles() throws WriteException {
		WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
		this.times = new WritableCellFormat(times10pt);
		this.times.setAlignment(Alignment.CENTRE);
		this.times.setWrap(true);

		WritableFont times10ptBold = new WritableFont(WritableFont.TIMES, 10,
				WritableFont.BOLD, false);
		this.timesBold = new WritableCellFormat(times10ptBold);
		this.timesBold.setAlignment(Alignment.CENTRE);
		this.timesBold.setVerticalAlignment(VerticalAlignment.CENTRE);
		this.timesBold.setWrap(true);

		CellView cv = new CellView();
		cv.setFormat(this.times);
		cv.setFormat(this.timesBold);
		cv.setAutosize(true);
	}
}
