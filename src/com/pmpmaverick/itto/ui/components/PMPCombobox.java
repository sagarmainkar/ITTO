package com.pmpmaverick.itto.ui.components;

import java.awt.Color;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JComboBox;

public class PMPCombobox<E> extends JComboBox{

	private Properties componentProperties;
	 protected Color enabledBackground = null ; 
     protected Color enabledForeground = null ; 
     protected Color selectionBackground = null ;
     protected Color selectionForeground = null ;
     protected Color disabledBackground = null ; 
     protected Color disabledForeground = null ; 
     
	public PMPCombobox(){
		super();
		try {
			componentProperties = new Properties();
			componentProperties.load(getClass().getClassLoader().getResourceAsStream("com/pmpmaverick/itto/ui/components/Component.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setProperties();
	}
	
	public PMPCombobox(Vector<E> items){
		super(items);
		try {
			componentProperties = new Properties();
			componentProperties.load(getClass().getClassLoader().getResourceAsStream("com/pmpmaverick/itto/ui/components/Component.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setProperties();
	}
	public void setProperties(){
		/*setFont(new Font("Arial", Font.PLAIN, 11));

        selectionBackground = ColorResolver.getColor(componentProperties.getProperty("COMBOBOX_SELECTION_BACKGROUND_COLOUR"));
		UIManager.put("ComboBox.selectionBackground",selectionBackground); 

        selectionForeground = ColorResolver.getColor(componentProperties.getProperty("COMBOBOX_SELECTION_FOREGROUND_COLOUR"));
		UIManager.put("ComboBox.selectionForeground",selectionForeground); //V 1.8.4

		enabledForeground = ColorResolver.getColor(componentProperties.getProperty("COMBOBOX_FOREGROUND_COLOUR"));
        this.setForeground (enabledForeground);//(enabledForeground);

		enabledBackground = ColorResolver.getColor(componentProperties.getProperty("COMBOBOX_BACKGROUND_COLOUR"));
        this.setBackground(enabledBackground); //(enabledBackground);

		disabledForeground = ColorResolver.getColor(componentProperties.getProperty("COMBOBOX_DISABLED_FOREGROUND_COLOUR"));
		UIManager.put("ComboBox.disabledForeground",disabledForeground);

		disabledBackground = ColorResolver.getColor(componentProperties.getProperty("COMBOBOX_DISABLED_BACKGROUND_COLOUR"));
		UIManager.put("ComboBox.disabledBackground",disabledBackground);
		updateUI();
        this.repaint();*/
    }

}
