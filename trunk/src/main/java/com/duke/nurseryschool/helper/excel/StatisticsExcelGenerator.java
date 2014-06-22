package com.duke.nurseryschool.helper.excel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.duke.nurseryschool.generated.I18N;
import com.duke.nurseryschool.helper.Helper;
import com.duke.nurseryschool.helper.StatisticsBean;
import com.duke.nurseryschool.hibernate.bean.Classes;

public class StatisticsExcelGenerator extends ExcelManager {

	private static final int CONTENT_START_ROW = 2;
	private static final int CONTENT_LAST_COLUMN = 10;

	private final StatisticsBean statisticsBean;

	public StatisticsExcelGenerator(WritableWorkbook workbook, StatisticsBean statisticsBean) {
		super(workbook);
		this.statisticsBean = statisticsBean;
	}

	public void addContent(int sheetNumber) throws IOException, WriteException {
		// Sheet for current class
		WritableSheet sheet = this.workbook.createSheet(Helper.getI18N(I18N.EXCEL_SHEET_TITLE_STATISTICS), sheetNumber);
		// Write contents
		this.addStyles();
		this.createHeaders(sheet);
		this.createContents(sheet);
	}

	private void createHeaders(WritableSheet sheet) throws RowsExceededException, WriteException {
		this.addCaption(sheet, HEADER_TOP_COLUMN, HEADER_TOP_ROW, this.generateTopMostHeaderLabel());

		this.addCaption(sheet, 0, HEADER_NORMAL_ROW, Helper.getI18N(I18N.EXCEL_HEADER_NORMAL_ORDER));
		this.addCaption(sheet, 1, HEADER_NORMAL_ROW, Helper.getI18N(I18N.EXCEL_HEADER_NORMAL_MONTH));
		this.addCaption(sheet, 2, HEADER_NORMAL_ROW, Helper.getI18N(I18N.EXCEL_HEADER_NORMAL_TOTAL));

		this.mergeHeaderCells(sheet);
	}

	private void mergeHeaderCells(WritableSheet sheet) throws WriteException, RowsExceededException {
		// All columns top row
		sheet.mergeCells(HEADER_TOP_COLUMN, HEADER_TOP_ROW, CONTENT_LAST_COLUMN, HEADER_TOP_ROW);
	}

	private void createContents(WritableSheet sheet) throws RowsExceededException, WriteException {
		int count = 1;
		int row = CONTENT_START_ROW;
		Map<Classes, BigDecimal> totalFeeForClasses = this.statisticsBean.getTotalFeeForClasses();
		Set<Entry<Classes, BigDecimal>> entrySet = totalFeeForClasses.entrySet();
		for (Entry<Classes, BigDecimal> entry : entrySet) {
			this.addNumber(sheet, 0, row, count);
			this.addLabel(sheet, 1, row, entry.getKey().getCurrentName());
			this.addNumber(sheet, 2, row, entry.getValue().doubleValue(), OBMITING_ZEROS, false);
			count++;
			row++;
		}

		this.addLabel(sheet, 1, row + 1, Helper.getI18N(I18N.EXCEL_HEADER_NORMAL_TOTAL));
		this.addNumber(sheet, 2, row + 1, this.statisticsBean.getTotalFeeForSchool().doubleValue(), OBMITING_ZEROS, false);
	}

	private String generateTopMostHeaderLabel() {
		StringBuffer headerTop = new StringBuffer();
		headerTop.append(Helper.getI18N(I18N.EXCEL_HEADER_TOP_STATISTICS, new String[] {
				Integer.toString(this.statisticsBean.getMonth().getMonthName()), Integer.toString(this.statisticsBean.getMonth().getYear()),
		}));
		return headerTop.toString();
	}
}
