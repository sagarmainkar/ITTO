package com.pmpmaverick.itto.ui.components;

import java.awt.Font;
import java.awt.LayoutManager;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.UIManager;

public class PMPPanel extends JPanel{
	private Properties componentProperties;
	public PMPPanel(){
		super();
		
		setProperties();
	}
	
	public PMPPanel(LayoutManager manager){
		super(manager);
		setProperties();
	}

	private void setProperties() {
		componentProperties = new Properties();
		try {
			componentProperties.load(getClass().getClassLoader().getResourceAsStream("com/pmpmaverick/itto/ui/components/Component.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setBackground(ColorResolver.getColor(componentProperties.getProperty("PANEL_BACKGROUND_COLOUR")));
        UIManager.put("ToolTip.foreground", ColorResolver.getColor(componentProperties.getProperty("TOOLTIP_FOREGROUND_COLOUR")));
        UIManager.put("ToolTip.background", ColorResolver.getColor(componentProperties.getProperty("TOOLTIP_BACKGROUND_COLOUR")));
        UIManager.put("ToolTip.font", new Font("MS Sans Serif", Font.PLAIN, 11));
	}
	

}
