package csp.nmsi.robot;

import csp.common.robot.*;

public class D6Q8 extends AbstractDriver {
	
	private static final D6Q8 INSTANCE = new D6Q8();

	public static void main(String[] args) throws IllegalMoveException {
		Grid grid = new Grid(5, 5);
		INSTANCE.initGrid(grid);
		Robot robot = new Robot(Robot.SOUTH, new Location(0, 4), grid);
		INSTANCE.zigZag(robot);
		INSTANCE.zigZag(robot);
		System.out.println(robot.getLocation());
	}

	@Override
	public void initGrid(Grid grid) {}
	
	public void zigZag(Robot robot) throws IllegalMoveException {
		if(robot.canMove(Robot.FORWARD))
			robot.moveForward();
		robot.rotateRight();
		if(robot.canMove(Robot.FORWARD))
			robot.moveForward();
		robot.rotateLeft();
		if(robot.canMove(Robot.FORWARD))
			robot.moveForward();
	}

}
