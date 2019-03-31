package com.jenks.ps.io;

import static java.lang.System.out;
import java.util.*;
import com.jenks.data.*;

public class ProcessExportData {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private final Map<String, AssignmentGroup> IMPORT_FILE_ASSIGNMENT_GROUP_MAP = new HashMap<>(5);
	private final ExportFileReader EXPORT_FILE_READER;
	
	public ProcessExportData(ExportFileReader exportFileReader) {
		EXPORT_FILE_READER = exportFileReader;
	}
	
	public void loadData() {
		final int firstRecordIndex = EXPORT_FILE_READER.isUseColumnHeader() ? 1 : 0;
		List<String[]> records = EXPORT_FILE_READER.getRecords();
		for(int index = records.size() - 1; index >= firstRecordIndex; index--) {
			String[] record = records.get(index);
			if(verifyRestrictions(record)) {
				
			}
		}
	}
	
	private boolean verifyRestrictions(String[] record) {
		boolean pass = true;
		final int dueDateCol = EXPORT_FILE_READER.getDueDateCol();
		pass = verifyDueDate(dueDateCol);
		if(pass) {
			
		}
		return pass;
	}
	
	private boolean verifyDueDate(final int dueDateCol) {
		boolean pass = true;
		if(dueDateCol >= 0) {
			
		}
		return pass;
	}
}
