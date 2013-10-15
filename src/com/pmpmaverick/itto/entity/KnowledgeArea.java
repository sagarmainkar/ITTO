package com.pmpmaverick.itto.entity;

public class KnowledgeArea {
	
		public int id;
		public String name;

		public KnowledgeArea(int id,String name){
			this.id=id;
			this.name=name;
		}

		public String toString(){
			return name;
		}
}
