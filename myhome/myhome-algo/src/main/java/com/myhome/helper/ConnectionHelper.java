package com.myhome.helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author 
 *
 */
public class ConnectionHelper {
	
	/** String DATASOURCE_CONTEXT */
	private static final String DATASOURCE_CONTEXT = "jndiM2M";

	/** String JNDI_ERROR */
	private static final String JNDI_ERROR = "Abstract DAO connect() JNDI problem. Cannot get InitialContext";
	
	/** String INVALID_CONNECTION_ERROR */
	private static final String INVALID_CONNECTION_ERROR = "Abstract DAO connect() invalid connection";

	/** String JNDI_LOOKUP_ERROR */
	private static final String JNDI_LOOKUP_ERROR = "Abstract DAO connect() failed to lookup datasource";
	
	/** ConnectionHelper instance */
	private static volatile ConnectionHelper instance;
	
	/**
	 * Singleton use
	 */
	private ConnectionHelper() {
	}

	/**
	 * @return ConnectionHelper instance
	 */
	public static synchronized ConnectionHelper getInstance() {
		if (instance == null) {
			instance = new ConnectionHelper();
		}
		return instance;
	}
	
	/**
	 * @return Connection connection
	 * @throws SQLException
	 */
	public synchronized Connection connect() throws SQLException {
		final Timer timer = new Timer().start("jndiM2M connected");
		Connection connection = null;
		try {
			final Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			env.put(Context.PROVIDER_URL, "t3://localhost:7001");
			
			final Context initialContext = new InitialContext(env);
			if (initialContext == null) {
				throw new SQLException(JNDI_ERROR);			
			}
			final DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
			if (datasource != null) {
				connection = datasource.getConnection();
				if (connection == null) {
					throw new SQLException(INVALID_CONNECTION_ERROR);
				}
			} else {
				throw new SQLException(JNDI_LOOKUP_ERROR);
			}
		} catch (final NamingException ex) {
			throw new SQLException(INVALID_CONNECTION_ERROR, ex);
		} finally {
			System.out.println(timer.stop());
		}
		return connection;
	}

	/**
	 * @param Connection connection
	 * @throws SQLException
	 */
	public synchronized void disconnect(Connection connection) throws SQLException {
		final Timer timer = new Timer().start("jndiM2M disconnected");
		try {
			if (connection != null) {
				connection.close();
			}
		} finally {
			System.out.println(timer.stop());
		}
	}

}
