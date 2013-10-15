package com.pmpmaverick.itto.entity;

public class PMPProcess {
	
		private int ID;
		private int knowledgeID;
		private String name;
		private int processGrpID;
		
		public PMPProcess(){
			
		}

		public PMPProcess(int id,int kaid,String name,int pgid){
			this.ID=id;
			this.knowledgeID=kaid;
			this.name=name;
			this.processGrpID=pgid;
		}

		public int getID() {
			return ID;
		}

		public void setID(int id) {
			ID = id;
		}

		public int getKnowledgeID() {
			return knowledgeID;
		}

		public void setKnowledgeID(int knowledgeID) {
			this.knowledgeID = knowledgeID;
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

