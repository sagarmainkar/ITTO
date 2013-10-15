package com.pmpmaverick.itto.ui.components;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.UIManager;

import org.jdesktop.swingx.JXTaskPane;

public class PMPXTaskPane extends JXTaskPane{
	private Properties componentProperties;
	public PMPXTaskPane(){
		super();
		
		setProperties();
	}
	
	public PMPXTaskPane(String title){
		super(title);
		
		setProperties();
	}
	public PMPXTaskPane(Icon icon){
		super(icon);
		
		setProperties();
	}
	public PMPXTaskPane(String title,Icon icon){
		super(title,icon);
		
		setProperties();
	}
	
	
	private void setProperties() {
		componentProperties = new Properties();
		try {
			componentProperties.load(getClass().getClassLoader().getResourceAsStream("com/pmpmaverick/itto/ui/components/Component.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//getContentPane().setBackground(ColorResolver.getColor(componentProperties.getProperty("PANEL_BACKGROUND_COLOUR")));
		UIManager.getDefaults().put("TaskPane.titleBackgroundGradientStart",ColorResolver.getColor(componentProperties.getProperty("TABLE_HEADER_BACKGROUND_COLOUR")));
		UIManager.getDefaults().put("TaskPane.titleBackgroundGradientEnd", ColorResolver.getColor(componentProperties.getProperty("TABLE_HEADER_GRADIENT_END_BACKGROUND_COLOUR")));
		UIManager.getDefaults().put("TaskPane.specialTitleBackground", ColorResolver.getColor(componentProperties.getProperty("TABLE_HEADER_BACKGROUND_COLOUR")));
		UIManager.getDefaults().put("TaskPane.titleOver", ColorResolver.getColor(componentProperties.getProperty("TOOLTIP_FOREGROUND_COLOUR")));
		
//		UIManager.getDefaults().put("TaskPane.specialTitleForeground", ColorResolver.getColor(componentProperties.getProperty("TABLE_HEADER_FOREGROUND_COLOUR")));
		UIManager.put("TaskPane.titleForeground", Color.WHITE);
        UIManager.put("ToolTip.foreground", ColorResolver.getColor(componentProperties.getProperty("TOOLTIP_FOREGROUND_COLOUR")));
        UIManager.put("ToolTip.background", ColorResolver.getColor(componentProperties.getProperty("TOOLTIP_BACKGROUND_COLOUR")));
        UIManager.put("ToolTip.font", new Font("MS Sans Serif", Font.PLAIN, 11));
	}

}
