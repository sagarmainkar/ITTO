package com.pmpmaverick.itto.ui.components;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

public class PMPScrollPane extends JScrollPane{
	private Properties componentProperties;
	Color enabledBackground = null ;
	 Color enabledForeground = null;
	 Color tooltipBackground = null ;
	public PMPScrollPane() {

        this.setProperties();
        PMPScrollBar horizontal = new PMPScrollBar(javax.swing.JScrollBar.HORIZONTAL);
        PMPScrollBar vertical = new PMPScrollBar(javax.swing.JScrollBar.VERTICAL);
        this.setHorizontalScrollBar(horizontal);
        this.setVerticalScrollBar(vertical);
    }
	public PMPScrollPane(JComponent objComponent) {
        super(objComponent);
        setBorder(new BevelBorder(BevelBorder.LOWERED,Color.lightGray,Color.darkGray));
        PMPScrollBar horizontal = new PMPScrollBar(javax.swing.JScrollBar.HORIZONTAL);
        PMPScrollBar vertical = new PMPScrollBar(javax.swing.JScrollBar.VERTICAL);
        this.setHorizontalScrollBar(horizontal);
        this.setVerticalScrollBar(vertical);
        setProperties();
    }
	
	private void setProperties(){
		componentProperties = new Properties();
		try {
			componentProperties.load(getClass().getClassLoader().getResourceAsStream("com/pmpmaverick/itto/ui/components/Component.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
			enabledBackground = ColorResolver.getColor(componentProperties.getProperty("PANEL_BACKGROUND_COLOUR"));
	        enabledForeground =ColorResolver.getColor(componentProperties.getProperty("PANEL_FOREGROUND_COLOUR"));
			setBackground(enabledBackground);
	        getViewport().setBackground(enabledBackground);
			setForeground(enabledForeground);
	        UIManager.put("ToolTip.foreground", ColorResolver.getColor(componentProperties.getProperty("TOOLTIP_FOREGROUND_COLOUR")));
	        UIManager.put("ToolTip.background", ColorResolver.getColor(componentProperties.getProperty("TOOLTIP_BACKGROUND_COLOUR")));
	        setOpaque(false);       
	        UIManager.put("ToolTip.font", new Font("MS Sans Serif", Font.PLAIN, 11));
	        //setBorder(new BevelBorder(BevelBorder.LOWERED,Color.lightGray,Color.darkGray));
	     }

}
