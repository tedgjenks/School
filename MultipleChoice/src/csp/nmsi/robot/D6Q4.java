package csp.nmsi.robot;

import csp.common.robot.*;

public class D6Q4 extends AbstractDriver {
	
	private static final D6Q4 INSTANCE = new D6Q4();

	@Override
	public void initGrid(Grid grid) {
		grid.close(1, 3);
		grid.close(2, 2);
		grid.close(3, 3);
	}

	public static void main(String[] args) throws IllegalMoveException {
		Grid grid = new Grid(5, 5);
		INSTANCE.initGrid(grid);
		Robot robot = new Robot(Robot.WEST, new Location(2, 3), grid);
		INSTANCE.move(robot);
		System.out.println(robot);
	}
	
	public void move(Robot robot) throws IllegalMoveException {
		robot.rotateRight();
		robot.rotateRight();
		for(int count = 3; count > 0; count--) {
			if(robot.canMove(Robot.FORWARD))
				robot.moveForward();
		}
		robot.rotateRight();
		if(robot.canMove(Robot.FORWARD)) {
			robot.moveForward();
			robot.rotateRight();
		}
		robot.rotateRight();
	}
	
}
