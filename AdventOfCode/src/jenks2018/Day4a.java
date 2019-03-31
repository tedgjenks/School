package jenks2018;

import java.util.*;
import static java.lang.System.out;
import jenks2018.io.FileHelper;

public class Day4a {
	
	static final Map<Short, Map<Byte, Short>> GUARD_SLEEP_RECORD = new HashMap<>(20); // guard ID -> Map(minute -> count)
	static final byte INIT_MINUTE_CAPACITY = 90;
	static final String GUARD_RECORD = "Guard #";

	public static void main(String[] args) {
		out.println("Begin");
		String[] input = FileHelper.loadInput("Input2018", "Day4.txt");
		processInput(input);
		out.println("End without error");
	}
	
	static void processInput(String[] records) {
		String record = null;
		int guardIndex = -1;
		for(int index = 0; index < records.length && record == null; index++) {
			guardIndex = records[index].indexOf(GUARD_RECORD);
			if(guardIndex >= 0)
				record = records[index];
		}
		out.println(getGuardNumber(record, guardIndex));
	}
	
	static short getGuardNumber(String guardRecord, int guardIndex) {
		int endGuardIndex = guardIndex + GUARD_RECORD.length();
		int spaceIndex = guardRecord.indexOf(" ", endGuardIndex);
		return Short.parseShort(guardRecord.substring(endGuardIndex, spaceIndex));
	}
}
