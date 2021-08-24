package com.zerv.database.utils;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.zerv.api.utils.ReadProperty;
import com.zerv.crypto.utils.PasswordEncrypt;

public class DatabaseUtils {

	ReadProperty prop = new ReadProperty();
	PasswordEncrypt pe = new PasswordEncrypt();
	// String env = System.getProperty("env");
	String env = "qa";

	/**
	 * Author :Satya Description : To connect to the Postgree database
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection createOracleConnection() throws Exception {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found!");

			e.printStackTrace();

		}

		Connection connection = null;

		try {
			if (env.equals("qa")) {
				connection = DriverManager.getConnection(prop.getProperty("DevStable_url"),
						prop.getProperty("DevStable_username"), pe.decrypt(prop.getProperty("DevStable_password")));
			} else if (env.equals("prod")) {
				connection = DriverManager.getConnection(prop.getProperty("Prod_url"),
						prop.getProperty("Prod_username"), pe.decrypt(prop.getProperty("Prod_password")));
			}

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

		}

		if (connection != null) {
			// System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}

		return connection;

	}

	/**
	 * Author :Satya Description : To connect to the ODBC database
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection createConnection() throws Exception {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();

		}

		Connection connection = null;

		try {
			if (env.equals("qa")) {
				connection = DriverManager.getConnection(prop.getProperty("ODBCDevStable_url"),
						prop.getProperty("ODBCDevStable_username"),
						pe.decrypt(prop.getProperty("ODBCDevStable_password")));
			} else if (env.equals("prod")) {
				connection = DriverManager.getConnection(prop.getProperty("OdbcProdDevStable_url"),
						prop.getProperty("OdbcProdDevStable_username"),
						pe.decrypt(prop.getProperty("OdbcProdDevStable_password")));
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

		}

		if (connection != null) {
			// System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}

		return connection;

	}

	/**
	 * Author= Satya
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public int getRowCount(String query) throws Exception {
		Connection connection = createOracleConnection();
		int count = 0;

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (!rs.next()) {
				System.out.println("No data found in database for given input");
			} else {

				do {

					count = rs.getInt("count");

				} while (rs.next());
			}

		} catch (Exception e)

		{

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return count;

	}

	/**
	 * Author :Satya Description : To get the StringValue
	 * 
	 * @return
	 * @throws Exception
	 */

	public String getStringDbValue(String query, String ColumnName) throws Exception {

		Connection connection = createOracleConnection();
		String StringValue = null;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (!rs.next()) {
				System.out.println("No data found in database  for given input");
			} else {

				do {
					try {
						if (rs.getString(ColumnName) != null) {
							StringValue = rs.getString(ColumnName);

						}
					} catch (Exception e) {

						// e.printStackTrace();
					}
					break;
				} while (rs.next());
			}

		} catch (Exception e) {

			// e.printStackTrace();
		} finally {
			connection.close();
		}
		return StringValue;
	}

	/**
	 * Author= Satya
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */

	public Set<String> getDbListValues(String query, String ColumnName) throws Exception {

		Connection connection = createOracleConnection();

		Set<String> set = new TreeSet<String>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (!rs.next()) {
				System.out.println("No data found in database for given input");
			} else {

				do {

					set.add(rs.getString(ColumnName));

				} while (rs.next());
			}

		} catch (Exception e)

		{

			e.printStackTrace();
		}

		finally {
			connection.close();
		}
		return set;

	}

	public String getDbSingleValue(String query, String ColumnName) throws Exception {

		Connection connection = createOracleConnection();

		String result = "";

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (!rs.next()) {
				// System.out.println("No data found in database for given input");
			} else {

				do {

					result = rs.getString(ColumnName);
					break;

				} while (rs.next());
			}

		} catch (Exception e)

		{

			e.printStackTrace();
		}

		finally {
			connection.close();
		}
		return result;

	}

	/**
	 * Author= Satya
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */

	public List<String> getDbListOfValues(String query, String ColumnName) throws Exception {

		Connection connection = createOracleConnection();

		List<String> list = new ArrayList<String>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (!rs.next()) {
				// System.out.println("No data found in database for given input");
			} else {

				do {

					list.add(rs.getString(ColumnName));

				} while (rs.next());
			}

		} catch (Exception e)

		{

			e.printStackTrace();
		}

		finally {
			connection.close();
		}
		return list;

	}

	public List<String> getDbListOfValues(String query) throws Exception {

		Connection connection = createOracleConnection();

		List<String> list = new ArrayList<String>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (!rs.next()) {
				// System.out.println("No data found in database for given input");
			} else {

				do {

					list.add(rs.getString(1));
					list.add(rs.getString(2));
					list.add(rs.getString(3));
					list.add(rs.getString(4));

				} while (rs.next());
			}

		} catch (Exception e)

		{

			e.printStackTrace();
		}

		finally {
			connection.close();
		}
		return list;

	}

	/**
	 * Author :Satya Description : To connect to the ODBC database
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection createODBCConnection() throws Exception {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();

		}

		Connection connection = null;

		try {
			if (env.equals("qa")) {
				connection = DriverManager.getConnection(prop.getProperty("ODBCDevStable_url"),
						prop.getProperty("ODBCDevStable_username"),
						pe.decrypt(prop.getProperty("ODBCDevStable_password")));
			} else if (env.equals("prod")) {
				connection = DriverManager.getConnection(prop.getProperty("OdbcProdDevStable_url"),
						prop.getProperty("OdbcProdDevStable_username"),
						pe.decrypt(prop.getProperty("OdbcProdDevStable_password")));
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

		}

		if (connection != null) {
			// System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}

		return connection;

	}

	/**
	 * Author= Satya
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */

	public List<String> getDbListOfValues1(String query, String ColumnName) throws Exception {

		Connection connection = createOracleConnection();

		List<String> list = new ArrayList<String>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (!rs.next()) {
				// System.out.println("No data found in database for given input");
			} else {

				do {

					list.add(rs.getString(ColumnName));

				} while (rs.next());
			}

		} catch (Exception e)

		{

			e.printStackTrace();
		}

		finally {
			connection.close();
		}
		return list;

	}

	/**
	 * Author=Satya
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */

	public List<String> getRowList(String query) throws Exception {

		Connection connection = createOracleConnection();

		List<String> list = new ArrayList<String>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int colcount = rs.getMetaData().getColumnCount();
			if (!rs.next()) {
				// System.out.println("No data found in database for given input");
			} else {

				do {
					for (int i = 1; i <= colcount; i++) {
						list.add(rs.getString(i));

					}
				} while (rs.next());
			}

		} catch (Exception e)

		{

			e.printStackTrace();
		}

		finally {
			connection.close();
		}
		return list;

	}

	/**
	 * Author=Satya
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 * @ JDBC
	 */

	public List<String> getDbListOfValue(String query, String ColumnName) throws Exception {

		Connection connection = createConnection();

		List<String> list = new ArrayList<String>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (!rs.next()) {
				// System.out.println("No data found in database for given input");
			} else {

				do {

					list.add(rs.getString(ColumnName));

				} while (rs.next());
			}

		} catch (Exception e)

		{

			e.printStackTrace();
		}

		finally {
			connection.close();
		}
		return list;

	}

	/**
	 * Author :Satya Description : To get the StringValue
	 * 
	 * @return
	 * @throws Exception
	 */

	public String getStringDbValue1(String query, String ColumnName) throws Exception {

		Connection connection = createConnection();
		String StringValue = null;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (!rs.next()) {
				System.out.println("No data found in database  for given input");
			} else {

				do {
					try {
						if (rs.getString(ColumnName) != null) {
							StringValue = rs.getString(ColumnName);

						}
					} catch (Exception e) {

						// e.printStackTrace();
					}
					break;
				} while (rs.next());
			}

		} catch (Exception e) {

			// e.printStackTrace();
		} finally {
			connection.close();
		}
		return StringValue;
	}

	/**
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getRowMap(String query) throws Exception {

		Connection connection = createOracleConnection();

		Map<String, String> result = new HashMap<String, String>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData metaData = rs.getMetaData();
			if (!rs.next()) {
				// System.out.println("No data found in database for given input");
			} else {
				do {

					for (int k = 1; k < rs.getMetaData().getColumnCount(); k++) {
						String columnName = metaData.getColumnName(k);
						// System.out.println(columnName);
						String value = rs.getString(columnName);
						// System.out.println(value);
						result.put(columnName, value);
					}

				} while (rs.next());

			}

		} catch (Exception e)

		{

			e.printStackTrace();
		}

		finally {
			connection.close();
		}
		return result;

	}

	/**
	 * Author: satya
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getRowDataInMap(String query) throws Exception {

		Connection connection = createODBCConnection();

		Map<String, String> result = new HashMap<String, String>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData metaData = rs.getMetaData();
			if (!rs.next()) {
				// System.out.println("No data found in database for given input");
			} else {
				do {

					for (int k = 1; k <= rs.getMetaData().getColumnCount(); k++) {
						String columnName = metaData.getColumnName(k);
						// System.out.println(columnName);
						String value = rs.getString(columnName);
						// System.out.println(value);
						result.put(columnName, value);
					}

				} while (rs.next());

			}

		} catch (Exception e)

		{

			e.printStackTrace();
		}

		finally {
			connection.close();
		}
		return result;

	}
}