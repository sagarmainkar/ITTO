package com.pmpmaverick.itto.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pmpmaverick.itto.entity.PMPProcess;
import com.pmpmaverick.itto.util.DBHelper;
import com.pmpmaverick.itto.util.SentenceCasesChanger;


public class PMPProcessDAO {
	
	private static Logger logger = Logger.getLogger(PMPProcessDAO.class);
	
	public List<PMPProcess> getinputOutputProcesses(int ID,boolean isInpput){
		try{
			logger.debug("Fetching process for KA "+ID + " fetching inputs "+isInpput);
			Class.forName("org.hsqldb.jdbcDriver");
			Connection connection = DBHelper.getConnection();
	        connection.setAutoCommit(true);
	        Statement statement = connection.createStatement();
	        
	        int input =isInpput==true?1:0;
	        String query = "SELECT PID FROM INPUT_OUTPUT_KA_PG WHERE IOID="+ID+" AND ISINPUT="+input;
			ResultSet resultSet = statement.executeQuery(query);
			List<PMPProcess> processList = new ArrayList<PMPProcess>();
			while(resultSet.next()){
				int processID =resultSet.getInt("PID");
				PMPProcess process = getProcess(processID);
				processList.add(process);
				
			}
			
			logger.debug("Process fetched");
			return processList;

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
	
	public List<PMPProcess> getProcessesForTool(int ID){
		try{
			logger.debug("Fetching process for tool "+ID);
			Class.forName("org.hsqldb.jdbcDriver");
	        Connection connection = DBHelper.getConnection();
	        connection.setAutoCommit(true);
	        Statement statement = connection.createStatement();
	        
	        String query = "SELECT PID FROM TOOLTECHNIQUE_KA_PG WHERE TTID="+ID;
			ResultSet resultSet = statement.executeQuery(query);
			List<PMPProcess> processList = new ArrayList<PMPProcess>();
			while(resultSet.next()){
				int processID =resultSet.getInt("PID");
				PMPProcess process = getProcess(processID);
				processList.add(process);
				
			}
			logger.debug("Fetched process");
			return processList;

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
	
	
	public PMPProcess getProcess(int processID){
		Connection connection = null;
		Statement statement =null;
		ResultSet resultSet = null;
		
		try {
			
		logger.debug("Fetchig process for process id"+processID);
		Class.forName("org.hsqldb.jdbcDriver");
		
        connection = DBHelper.getConnection();
        connection.setAutoCommit(true);
        statement = connection.createStatement();	
        String query ="SELECT NAME,KAID,PGID FROM PROCESS WHERE ID="+processID;
        resultSet = statement.executeQuery(query);
        PMPProcess pmpProcess = new PMPProcess();
        pmpProcess.setID(processID);
       
        while(resultSet.next()){
        	pmpProcess.setName(resultSet.getString("NAME"));
        	pmpProcess.setKnowledgeID(resultSet.getInt("KAID"));
        	pmpProcess.setProcessGrpID(resultSet.getInt("PGID"));
        }
        
        logger.debug("Process fetched");
        return pmpProcess;
		}
		catch(ClassNotFoundException cfe){
			logger.error(cfe.getMessage());
			cfe.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
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
	
	public List<PMPProcess> getProcessesForKA(int KAID){
		Connection connection = null;
		Statement statement =null;
		ResultSet resultSet = null;
		Vector<PMPProcess> processes = new Vector<PMPProcess>();
		try{
			logger.debug("Fetching process for KA "+KAID);
			Class.forName("org.hsqldb.jdbcDriver");
            connection = DBHelper.getConnection();
            connection.setAutoCommit(true);
            statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT ID,KAID,NAME,PGID FROM PROCESS WHERE KAID="+KAID);

            while (resultSet.next()) {
               PMPProcess proc = new PMPProcess(resultSet.getInt("ID"),resultSet.getInt("KAID"),SentenceCasesChanger.toggle(resultSet.getString("NAME")),resultSet.getInt("PGID"));
			   processes.add(proc);
            }
            
		} catch (Exception e) {
			logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		logger.debug("Fetched process");
		return processes;
	}
}
