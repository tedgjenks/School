package csp.common.robot;

import java.util.*;

public abstract class AbstractDriver {
	private final Set<String> POSITIONS = new HashSet<>(20);
	
	public abstract void initGrid(Grid grid);
	
	public void addPosition(Robot robot) {
		POSITIONS.add(robot.toString());
	}
	
	public boolean positionRepeated(Robot robot) {
		return POSITIONS.contains(robot.toString());
	}
}
