package com.duke.nurseryschool.helper.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.duke.nurseryschool.generated.I18N;
import com.duke.nurseryschool.helper.Helper;
import com.duke.nurseryschool.helper.comparator.StudentComparator;
import com.duke.nurseryschool.hibernate.bean.Month;
import com.duke.nurseryschool.hibernate.bean.Student;

public class StudentHasBreakfastExcelGenerator extends ExcelManager {

	private static final int CONTENT_START_ROW = 2;
	private static final int CONTENT_LAST_COLUMN = 10;

	private final Map<String, List<Student>> studentsByClasses;
	private final Month month;

	public StudentHasBreakfastExcelGenerator(WritableWorkbook workbook,
			Month month, List<Student> students) {
		super(workbook);
		this.month = month;

		this.studentsByClasses = new TreeMap<String, List<Student>>();
		this.populateAndSortData(students);
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
		this.addCaption(sheet, 2, HEADER_NORMAL_ROW,
				Helper.getI18N(I18N.EXCEL_HEADER_NORMAL_CLASS));

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

		java.util.Iterator<Entry<String, List<Student>>> iterator = this.studentsByClasses
				.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, List<Student>> entry = iterator.next();
			List<Student> studentsByClass = entry.getValue();

			for (Student student : studentsByClass) {
				this.addNumber(sheet, 0, row, count);
				this.addLabel(sheet, 1, row, student.getName());
				this.addLabel(sheet, 2, row, student.getAssociatedClass()
						.getCurrentName());
				count++;
				row++;
			}
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

	private void populateAndSortData(List<Student> students) {
		Set<String> unsortedClasses = new TreeSet<String>();
		// Populate all related classes
		for (Student student : students) {
			unsortedClasses.add(student.getAssociatedClass().getCurrentName());
		}
		// Sort classes
		List<String> sortedClasses = new ArrayList<>(unsortedClasses);
		Collections.sort(sortedClasses);
		// Populate into class-student hash map
		for (String className : sortedClasses) {
			List<Student> studentByClass = new ArrayList<Student>();
			for (Student student : students) {
				if (className.equals(student.getAssociatedClass()
						.getCurrentName())) {
					studentByClass.add(student);
				}
			}
			// Sort student names
			Collections.sort(studentByClass, new StudentComparator());
			// Add to map
			this.studentsByClasses.put(className, studentByClass);
		}
	}
}
