package edu.jenks.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionUtil {
	
	public static Object newInstance(String fullyQualifiedName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		return newInstance(fullyQualifiedName, null, null);
	}
	
	public static Object newInstance(String fullyQualifiedName, Class<?>[] constructorTypes, Object[] constructorArgs) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> c = Class.forName(fullyQualifiedName);
		Constructor<?> constructor = constructorTypes != null ? c.getDeclaredConstructor(constructorTypes) : c.getDeclaredConstructor();
		return constructorArgs != null ? constructor.newInstance(constructorArgs) : constructor.newInstance();
	}
}
