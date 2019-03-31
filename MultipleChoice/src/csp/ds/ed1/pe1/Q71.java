package csp.ds.ed1.pe1;

import static java.lang.System.out;

import csp.common.robot.*;

public class Q71 {
	
	public static final Location TARGET_LOCATION = new Location(4, 4);
	public static final Grid GRID = new Grid(5, 5);
	
	public static void main(String[] args) {
		GRID.setTarget(TARGET_LOCATION);
		try {
			out.println("A " + ansA());
		} catch (IllegalMoveException e) {
			out.println("A " + e.getMessage());
		}
		try {
			out.println("B " + ansB());
		} catch (IllegalMoveException e) {
			out.println("B " + e.getMessage());
		}
		try {
			out.println("C " + ansC());
		} catch (IllegalMoveException e) {
			out.println("C " + e.getMessage());
		}
		try {
			out.println("D " + ansD());
		} catch (IllegalMoveException e) {
			out.println("D " + e.getMessage());
		}
	}
	
	private static boolean ansA() throws IllegalMoveException {
		Robot robot = new Robot(Robot.SOUTH, new Location(0, 0), GRID);
		for(int i = 2; i > 0; i--) {
			robot.moveForward();
			robot.moveForward();
			robot.rotateLeft();
			robot.moveForward();
			robot.rotateRight();
		}
		robot.moveForward();
		robot.moveForward();
		return GRID.isTarget(robot.getLocation());
	}
	
	private static boolean ansB() throws IllegalMoveException {
		Robot robot = new Robot(Robot.SOUTH, new Location(0, 0), GRID);
		for(int i = 2; i > 0; i--) {
			robot.moveForward();
			robot.moveForward();
			robot.rotateLeft();
			robot.moveForward();
			robot.rotateRight();
		}
		robot.rotateLeft();
		robot.moveForward();
		robot.moveForward();
		return GRID.isTarget(robot.getLocation());
	}
	
	public static boolean ansC() throws IllegalMoveException {
		Robot robot = new Robot(Robot.SOUTH, new Location(0, 0), GRID);
		robot.rotateLeft();
		for(int i = 2; i > 0; i--) {
			for(int j = 4; j > 0; j--)
				robot.moveForward();
			robot.rotateRight();
		}
		return GRID.isTarget(robot.getLocation());
	}
	
	public static boolean ansD() throws IllegalMoveException {
		Robot robot = new Robot(Robot.SOUTH, new Location(0, 0), GRID);
		robot.rotateLeft();
		for(int i = 2; i > 0; i--) {
			robot.moveForward();
			robot.moveForward();
			robot.rotateRight();
			robot.moveForward();
			robot.moveForward();
			robot.rotateLeft();
		}
		return GRID.isTarget(robot.getLocation());
	}

}
