package com.pmpmaverick.itto.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;

import com.pmpmaverick.itto.ui.ITTOExplorer;

public class DBHelper {

	private static Logger logger = Logger.getLogger(DBHelper.class);
	public static void loadDB(){
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:pmp", "SA", "");
			connection.setAutoCommit(true);
			logger.debug("Getting SQL File");
			InputStreamReader ior= new InputStreamReader(DBHelper.class.getClassLoader().getResourceAsStream("com/pmpmaverick/itto/dao/PMP.sql"));
			
			
			SqlFile sqlFile = new SqlFile(ior,ior.toString(),System.out,null,false,null);
			logger.debug("Got SQL File");
			sqlFile.setConnection(connection);
			logger.debug("Creating DB");
			sqlFile.execute();
			logger.debug("DB created");
			
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} /*catch (URISyntaxException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}*/ catch (SqlToolError e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
	//	return DriverManager.getConnection("jdbc:hsqldb:file:data\\testdb", "SA", "");
		return DriverManager.getConnection("jdbc:hsqldb:mem:pmp", "SA", "");
	}
}
