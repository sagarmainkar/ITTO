package com.pmpmaverick.itto.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pmpmaverick.itto.entity.KnowledgeArea;
import com.pmpmaverick.itto.util.DBHelper;

public class KnowledgeAreaDAO {

	private static Logger logger = Logger.getLogger(KnowledgeAreaDAO.class);
	
	public List<KnowledgeArea> getAllKnowledgeAreas(){
		Connection connection = null;
		Statement statement =null;
		ResultSet resultSet = null;
		List<KnowledgeArea> kaitems = new Vector<KnowledgeArea>();
		try{
			logger.debug("Fetching KA's");
		  Class.forName("org.hsqldb.jdbcDriver");
          connection = DBHelper.getConnection();
          
          connection.setAutoCommit(true);
          statement = connection.createStatement();
          resultSet = statement.executeQuery("SELECT ID,NAME FROM KNOWLEDGE_AREA");
			
          while (resultSet.next()) {
        	  KnowledgeArea ka = new KnowledgeArea(resultSet.getInt("ID"),resultSet.getString("NAME"));
			   kaitems.add(ka);
          }
		}
		catch (Exception e) {
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
		logger.debug("All KA fetched");
		return kaitems;
	}
}
