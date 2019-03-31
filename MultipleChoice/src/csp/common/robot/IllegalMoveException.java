package csp.common.robot;

public class IllegalMoveException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7318928612075935160L;

	public IllegalMoveException() {
		super();
	}

	public IllegalMoveException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public IllegalMoveException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public IllegalMoveException(String arg0) {
		super(arg0);
	}

	public IllegalMoveException(Throwable arg0) {
		super(arg0);
	}
	
	

}
