package edu.jenks.scrape.data;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

import edu.jenks.scrape.util.SystemInfo;

public abstract class Persister {
	
	public static String CONNECTION_PROPERTIES_PATH = SystemInfo.INSTANCE.RESOURCES_PATH + "MySql/MySqlConnection.properties";

	protected final Logger LOGGER;
	protected Connection conn;
	protected List<Statement> openStatements = new ArrayList<>(10);
	
	public Persister(Logger logger) {
		LOGGER = logger;
	}
	
	public void handleSQLException(SQLException e) {
		handleSQLException(e, null);
	}
	
	public void handleSQLException(SQLException e, String message) {
		StringBuilder sb = new StringBuilder(200);
		sb.append(e.getMessage()).append(System.lineSeparator());
		if(message != null)
			sb.append(message).append(System.lineSeparator());
		StackTraceElement[] stElements = e.getStackTrace();
		for(int i = 0; i < stElements.length; i++)
			sb.append(stElements[i].toString()).append(System.lineSeparator());
		LOGGER.severe(sb.toString());	
	}
	
	public boolean connect() throws IOException {
		boolean connected = false;
		try {
			if(conn == null || conn.isClosed()) {
				Properties connectionProps = new Properties();
				connectionProps.load(new FileReader(CONNECTION_PROPERTIES_PATH));
			    conn = DriverManager.getConnection(connectionProps.getProperty("db_url"), connectionProps);
				initStatements();
			}
			connected = conn.isValid(5);
		} catch (SQLException e) {
			handleSQLException(e);
		}
		return connected;
	}
	
	public void disconnect() {
		try {
			closeStatements();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			handleSQLException(e);
		}
	}
	
	public PreparedStatement openPreparedStatement(String sql) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
		openStatements.add(ps);
		return ps;
	}
	
	protected void closeStatements() throws SQLException {
		for(int index = openStatements.size() - 1; index >= 0; index--)
			closeStatement(openStatements.get(index));
	}
	
	protected abstract void initStatements() throws SQLException;
	
	protected void closeStatement(Statement s) {
		try {
			s.close();
		} catch (SQLException e) {
			handleSQLException(e);
		}
	}

}
