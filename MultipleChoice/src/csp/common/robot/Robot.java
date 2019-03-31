package csp.common.robot;

public class Robot {
	
	public static final char NORTH = 'N';
	public static final char SOUTH = 'S';
	public static final char WEST = 'W';
	public static final char EAST = 'E';
	
	public static final char LEFT = 'L';
	public static final char RIGHT = 'R';
	public static final char FORWARD = 'F';
	public static final char BACKWARD = 'B';

	private char direction;
	private Location location;
	private Grid grid;
	
	public Robot(char direction, Location location, Grid grid) {
		this.direction = direction;
		this.location = location;
		this.grid = grid;
	}
	
	public boolean onTarget() {
		return grid.isTarget(location);
	}
	
	public char getDirection() {
		return direction;
	}

	public Location getLocation() {
		return location;
	}

	private Location getLocationInFront() {
		int curRow = location.getRow(), curCol = location.getCol();
		switch(direction) {
		case NORTH: return new Location(curRow - 1, curCol);
		case SOUTH: return new Location(curRow + 1, curCol);
		case EAST: return new Location(curRow, curCol + 1);
		case WEST: return new Location(curRow, curCol - 1);
		default: throw new IllegalStateException("Unknown direction: " + direction);
		}
	}
	
	public void moveForward() throws IllegalMoveException {
		Location front = getLocationInFront();
		if(grid.isLocationOpen(front))
			location = front;
		else
			throw new IllegalMoveException("Can't move forward at location " + location + ", and direction " + direction);
	}
	
	public void rotateLeft() {
		switch(direction) {
		case 'N':
			direction = 'W';
			break;
		case 'S':
			direction = 'E';
			break;
		case 'E':
			direction = 'N';
			break;
		case 'W':
			direction = 'S';
			break;
		}
	}
	
	public void rotateRight() {
		for(int i = 3; i > 0; i--)
			rotateLeft();
	}
	
	public boolean canMove(char direction) {
		char saveDirection = this.direction;
		this.direction = interpretNonCompassDirection(direction);
		boolean canMove = grid.isLocationOpen(getLocationInFront());
		this.direction = saveDirection;
		return canMove;
	}
	
	public char interpretNonCompassDirection(char direction) {
		char retDir = direction;
		final char actDir = this.direction;
		switch(direction) {
		case LEFT:
			rotateLeft();
			retDir = this.direction;
			break;
		case RIGHT:
			rotateRight();
			retDir = this.direction;
			break;
		case FORWARD: retDir = this.direction;
		}
		this.direction = actDir;
		return retDir;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return direction + " " + location;
	}
}
