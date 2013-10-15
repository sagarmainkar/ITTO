package com.pmpmaverick.itto.ui.components;

import java.awt.Frame;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JDialog;

public class PMPDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4422474887713460683L;
	private Properties componentProperties;
	public PMPDialog(Frame frame,String title,boolean modal){
		super(frame,title,modal);
		
		setProperties();
	}
	
	

	private void setProperties() {
		componentProperties = new Properties();
		try {
			componentProperties.load(getClass().getClassLoader().getResourceAsStream("com/pmpmaverick/itto/ui/components/Component.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setBackground(ColorResolver.getColor(componentProperties.getProperty("DIALOG_BACKGROUND_COLOUR")));
	}

}
