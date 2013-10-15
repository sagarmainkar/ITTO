package com.pmpmaverick.itto.ui.components;

import java.awt.Color;
import java.util.StringTokenizer;

public class ColorResolver implements PMPConstants{
	public static Color getColor(String color){
		
		StringTokenizer tokenizer = new StringTokenizer(color.trim(),COLOR_DELIMITER);
		int[] rgb= new int[3];
		int count=0;
		while(tokenizer.hasMoreElements()){
			rgb[count] = Integer.parseInt(tokenizer.nextElement().toString());
			count++;
		}
		return new Color(rgb[0],rgb[1],rgb[2]);
	}
}
