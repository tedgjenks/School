/**
 * 
 */
package edu.jenks.dist.simple;

/**
 * @author Ted Jenks
 *
 */
public abstract class AbstractHelloWorld {
	/**
	 * The string "Hello World"
	 */
	public final static String HELLO_WORLD = "Hello World";
	
	/**
	 * @return the string "Hello World"
	 */
	public abstract String generateHelloWorld();
	
	/**
	 * @param formalParameter
	 * @return <code>formalParameter</code>
	 */
	public abstract String generateParameter(String formalParameter);
	
}
