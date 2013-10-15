package com.pmpmaverick.itto.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.pmpmaverick.itto.entity.ITTO;
import com.pmpmaverick.itto.ui.ITTOExplorer;
import com.pmpmaverick.itto.util.DBHelper;




public class ITTODAO {
	
	private static Logger logger = Logger.getLogger(ITTODAO.class);
	
	public List<ITTO> getInputsForProcess(int processID){
		try{
			logger.debug("Fetching inputs for process "+processID);
			Class.forName("org.hsqldb.jdbcDriver");
	        Connection connection = DBHelper.getConnection();
	        connection.setAutoCommit(true);
	        Statement statement = connection.createStatement();
	    
			ResultSet resultSet = statement.executeQuery("SELECT IOID,KAID,PGID FROM INPUT_OUTPUT_KA_PG WHERE PID ="+processID+" AND ISINPUT=1 ORDER BY ORDERID");
			List<ITTO> inputList = new ArrayList<ITTO>();
			while(resultSet.next()){
				
				ITTO itto= getInputOutput(resultSet.getInt("IOID"),processID,resultSet.getInt("PGID"),resultSet.getInt("KAID"),ITTO.TYPE.INPUT);
				inputList.add(itto);
				
			}
			
			logger.debug("Inputs fetched for process "+processID);
			return inputList;

		}
		catch(ClassNotFoundException cfe){
			logger.error(cfe.getMessage());
			cfe.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ITTO> getOutputsForProcess(int processID){
		try{
			logger.debug("Fetching outputs for process "+processID);
			Class.forName("org.hsqldb.jdbcDriver");
	        Connection connection = DBHelper.getConnection();
	        connection.setAutoCommit(true);
	        Statement statement = connection.createStatement();
	        
			ResultSet resultSet = statement.executeQuery("SELECT IOID,KAID,PGID FROM INPUT_OUTPUT_KA_PG WHERE PID ="+processID+" AND ISINPUT=0 ORDER BY ORDERID");
			List<ITTO> inputList = new ArrayList<ITTO>();
			while(resultSet.next()){
				
				ITTO itto= getInputOutput(resultSet.getInt("IOID"),processID,resultSet.getInt("PGID"),resultSet.getInt("KAID"),ITTO.TYPE.OUTPUT);
				inputList.add(itto);
				
			}
			logger.debug("Outputs fetched for process "+processID);
			return inputList;

		}
		catch(ClassNotFoundException cfe){
			logger.error(cfe.getMessage());
			cfe.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ITTO> getToolsForProcess(int processID){
		try{
			logger.debug("Fetching tools for process "+processID);
			Class.forName("org.hsqldb.jdbcDriver");
			Connection connection = DBHelper.getConnection();
	        connection.setAutoCommit(true);
	        Statement statement = connection.createStatement();
	        
			ResultSet resultSet = statement.executeQuery("SELECT TTID,KAID,PGID FROM TOOLTECHNIQUE_KA_PG WHERE PID ="+processID+" ORDER BY ORDERID");
			List<ITTO> toolsList = new ArrayList<ITTO>();
			while(resultSet.next()){
				
				ITTO itto= getToolTechnique(resultSet.getInt("TTID"),processID,resultSet.getInt("PGID"),resultSet.getInt("KAID"));
				toolsList.add(itto);
				
			}
			return toolsList;

		}
		catch(ClassNotFoundException cfe){
			logger.error(cfe.getMessage());
			cfe.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public ITTO getInputOutput(int inputID,int processID,int processGrpID,int knowledgeAreaID,ITTO.TYPE type) {
		Connection connection = null;
		Statement statement =null;
		ResultSet resultSet = null;
		
		try {
		logger.debug("Fetching input output for process "+processID);
		Class.forName("org.hsqldb.jdbcDriver");
		
        connection = DBHelper.getConnection();
        connection.setAutoCommit(true);
        statement = connection.createStatement();	
        String query ="SELECT NAME FROM INPUT_OUTPUT WHERE ID="+inputID;
         resultSet = statement.executeQuery(query);
        ITTO itto = new ITTO();
        itto.setID(inputID);
        itto.setKnowledgeAreaID(knowledgeAreaID);
        itto.setProcessID(processID);
        itto.setProcessGrpID(processGrpID);
        itto.setType(type);
        while(resultSet.next()){
        	itto.setName(resultSet.getString("NAME"));
        }
        
        
        return itto;
		}catch(ClassNotFoundException cfe){
			logger.error(cfe.getMessage());
			cfe.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
		finally{
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		return null;
	}
	
	public ITTO getToolTechnique(int toolTechniqueID,int processID,int processGrpID,int knowledgeAreaID) {
		Connection connection = null;
		Statement statement =null;
		ResultSet resultSet = null;
		
		try {
		logger.debug("Fetching tool technique ");
		Class.forName("org.hsqldb.jdbcDriver");
		
        connection = DBHelper.getConnection();
        connection.setAutoCommit(true);
        statement = connection.createStatement();	
        String query ="SELECT NAME FROM TOOLTECHNIQUE WHERE ID="+toolTechniqueID;
         resultSet = statement.executeQuery(query);
        ITTO itto = new ITTO();
        itto.setID(toolTechniqueID);
        itto.setKnowledgeAreaID(knowledgeAreaID);
        itto.setProcessID(processID);
        itto.setProcessGrpID(processGrpID);
        itto.setType(ITTO.TYPE.TOOL);
        while(resultSet.next()){
        	itto.setName(resultSet.getString("NAME"));
        }
        
        
        return itto;
		}catch(ClassNotFoundException cfe){
			logger.error(cfe.getMessage());
			cfe.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
		finally{
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		return null;
	}

}
