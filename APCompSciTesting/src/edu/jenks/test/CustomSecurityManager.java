/**
 * 
 */
package edu.jenks.test;

import java.security.Permission;

/**
 * @author Ted
 *
 */
public class CustomSecurityManager extends SecurityManager {

	/**
	 * 
	 */
	public CustomSecurityManager() {
		super();
	}

	@Override
	public void checkPermission(Permission permission) {
		if(permission.getName().startsWith("exitVM"))
			throw new SecurityException("System exit disallowed!");
		super.checkPermission(permission);
	}
}
