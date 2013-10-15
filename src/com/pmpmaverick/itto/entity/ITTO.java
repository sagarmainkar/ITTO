package com.pmpmaverick.itto.entity;

public class ITTO {
	private int ID;
	private String name;
	private int knowledgeAreaID;
	private int processID;
	private int processGrpID;
	public  enum TYPE{INPUT,OUTPUT,TOOL};
	private TYPE type;
	
	public ITTO(){
		
	}
	
	public ITTO(int ID,int knowledgeAreaID,int processID,String name,TYPE type){
		this.ID =ID;
		this.knowledgeAreaID = knowledgeAreaID;
		this.processID =processID;
		this.name =name;
		this.type = type;
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public int getKnowledgeAreaID() {
		return knowledgeAreaID;
	}
	public void setKnowledgeAreaID(int knowledgeAreaID) {
		this.knowledgeAreaID = knowledgeAreaID;
	}
	public int getProcessID() {
		return processID;
	}
	public void setProcessID(int processID) {
		this.processID = processID;
	}
	public TYPE getType() {
		return type;
	}
	public void setType(TYPE type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getProcessGrpID() {
		return processGrpID;
	}

	public void setProcessGrpID(int processGrpID) {
		this.processGrpID = processGrpID;
	}
	
	public String toString(){
		return name;
	}
	
	
	

}
