/**
 * 
 */
package com.myhome.helper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 
 *
 */
public class JdbcUtil {

	/** JdbcUtil instance */
	private static volatile JdbcUtil instance;
	
	/**
	 * Singleton use
	 */
	private JdbcUtil() {
	}
	
	/**
	 * @return JdbcUtil instance
	 */
	public static synchronized JdbcUtil getInstance() {
		if (instance == null) {
			instance = new JdbcUtil();
		}
		return instance;
	}

	/**
	 * @param Connection connection
	 * @param DatabaseMetaData metaData
	 * @param String schema
	 * @param String tableName
	 * @return Map<String, String> columns
	 * @throws SQLException
	 */
	private synchronized Map<String, String> getColumns(Connection connection,
							            		        DatabaseMetaData metaData,
							            		        String schema,
							            		        String tableName) throws SQLException {
		final Map<String, String> columns = new LinkedHashMap<String, String>();
		ResultSet resultSet = null;
		try {
			resultSet = metaData.getColumns(connection.getCatalog(), schema, tableName, null);
			while (resultSet.next()) {
				columns.put(resultSet.getString("COLUMN_NAME"), resultSet.getString("TYPE_NAME"));
			}
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
		}
		return columns;
	}
	
	/**
	 * @param Connection connection
	 * @param String schema
	 * @param String filter
	 * @return Map<String, Map<String, String>> tables
	 * @throws SQLException
	 */
	public synchronized Map<String, Map<String, String>> getTables(Connection connection,
									  							   String schema, 
									  							   String filter) throws SQLException {
		final Map<String, Map<String, String>> tables = new LinkedHashMap<String, Map<String, String>>();
		ResultSet resultSet = null;
		try {
			final DatabaseMetaData metaData = connection.getMetaData();
			resultSet = metaData.getTables(connection.getCatalog(), schema,	filter, new String[] {"TABLE"});
			String tableName = null;
			while (resultSet.next()) {
				tableName = resultSet.getString("TABLE_NAME");
				tables.put(tableName, getColumns(connection, metaData, schema, tableName));
			}
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
		}
		return tables;
	}
	
	/**
	 * @param Connection connection
	 * @param String procedureName
	 * @param String[] params
	 * @return String result
	 * @throws SQLException
	 */
	public synchronized String execute(Connection connection,
									   String procedureName, 
									   String[] params) throws SQLException {
		StringBuilder result = null;
		ResultSet resultSet = null;
		CallableStatement statement = null;
		try {
			final StringBuilder sqlQuery = new StringBuilder("{call ");
			sqlQuery.append(procedureName);
			sqlQuery.append("(");
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					sqlQuery.append("?");
					if (i != params.length - 1) {
						sqlQuery.append(",");
					}
				}
			}
			sqlQuery.append(")}");
			statement = connection.prepareCall(sqlQuery.toString(),
											   ResultSet.TYPE_FORWARD_ONLY, 
											   ResultSet.CONCUR_READ_ONLY);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					statement.setString(i + 1, params[i]);
				}
			}
			resultSet = statement.executeQuery();
			if (resultSet != null) {
				result = new StringBuilder();
				while (resultSet.next()) {
					result.append(resultSet.getString(1));
				}
			}
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
		}
		return result == null ? "" : result.toString();
	}	
}
