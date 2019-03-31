package csp.codehs.robot;

import csp.common.robot.*;

public class Q5 extends AbstractDriver {
	
	private static final Q5 INSTANCE = new Q5();

	public static void main(String[] args) throws IllegalMoveException {
		Grid grid = new Grid(5, 3);
		INSTANCE.initGrid(grid);
		Robot robot = new Robot(Robot.WEST, new Location(4, 2), grid);
		System.out.println(INSTANCE.goalReached(robot));
	}
	
	public void initGrid(Grid grid) {
		grid.close(1, 0);
		grid.close(1, 1);
		grid.close(3, 1);
		grid.close(3, 2);
		grid.setTarget(0, 0);
	}
	
	private boolean goalReached(Robot robot) throws IllegalMoveException {
		boolean goalReached = false;
		for(int iterations = 1, maxIterations = 100; !(goalReached = robot.onTarget()) && iterations < maxIterations; iterations++) {
			if(robot.canMove(Robot.RIGHT))
				robot.rotateRight();
			else {
				if(robot.canMove(Robot.LEFT))
					robot.rotateLeft();
				if(robot.canMove(Robot.FORWARD))
					robot.moveForward();
			}
			if(positionRepeated(robot)) {
				System.out.println("Position repeated: " + robot);
				break;
			} else
				addPosition(robot);
			System.out.println("Iteration " + iterations + " " + robot);
		}
		return goalReached;
	}
}