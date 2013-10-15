package com.pmpmaverick.itto.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class SentenceCasesChanger {
	
	
	public static String toggle(String str){
		if(str == null){
			return "";
		}
		if(str.equals("")){
			return str;
		}
		StringBuffer sb = new StringBuffer();
		
		Pattern pattern = Pattern.compile("\\w+");
		Matcher matcher = pattern.matcher(str.toUpperCase());
		while(matcher.find()){
			String newString = matcher.group();
			sb.append(newString.substring(0,1));
			sb.append(newString.substring(1).toLowerCase());
			sb.append(" ");
		}
		
		return sb.toString();
	}
	    
	    
	public static void main(String args[]) throws Exception{
		SentenceCasesChanger.toggle("Test is this");
		
	}

}
