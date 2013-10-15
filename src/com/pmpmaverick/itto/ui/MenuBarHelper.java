package com.pmpmaverick.itto.ui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.pmpmaverick.itto.ui.components.ColorResolver;

public class MenuBarHelper {
	
	 Frame owner ;
	 private ResourceBundle rb = ResourceBundle.getBundle("com.pmpmaverick.itto.ui.resources");
	 Properties componentProperties;
	 public MenuBarHelper(Frame frame){
		 this.owner = frame;
	 }
	public  JMenuBar getMenuBar(){
		try {
			componentProperties = new Properties();
			componentProperties.load(getClass().getClassLoader().getResourceAsStream("com/pmpmaverick/itto/ui/components/Component.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setBackground(ColorResolver.getColor(componentProperties.getProperty("DIALOG_BACKGROUND_COLOUR")));
		JMenuItem exitMenu = new JMenuItem(new AbstractAction("Exit") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
		});
		fileMenu.add(exitMenu);
		
		
		JMenu menu = new JMenu("Help");
		menu.setBackground(ColorResolver.getColor(componentProperties.getProperty("DIALOG_BACKGROUND_COLOUR")));
		JMenuItem  aboutMenu = new JMenuItem(new AboutMenuAction("About"));
		aboutMenu.setBackground(ColorResolver.getColor(componentProperties.getProperty("DIALOG_BACKGROUND_COLOUR")));
		menu.add(aboutMenu);
		
		JMenuItem  licenseMenu = new JMenuItem(new LicenseMenuAction("License"));
		aboutMenu.setBackground(ColorResolver.getColor(componentProperties.getProperty("DIALOG_BACKGROUND_COLOUR")));
		menu.add(licenseMenu);
		
		JMenuItem  contactMenu = new JMenuItem(new AbstractAction("Contact us") {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ContactUSDialog dialog = new ContactUSDialog(MenuBarHelper.this.owner,rb.getString("MenuBarHelper.contact.txt"));
				dialog.pack();
				
				Dimension screenSize =  Toolkit.getDefaultToolkit().getScreenSize();
		        Dimension labelSize = dialog.getPreferredSize();
		        dialog.setLocation(screenSize.width/2 - (labelSize.width/2),screenSize.height/2 - (labelSize.height/2));
		        dialog.setVisible(true);
				
			}
		});
		contactMenu.setBackground(ColorResolver.getColor(componentProperties.getProperty("DIALOG_BACKGROUND_COLOUR")));
		menu.add(contactMenu);
		
		menuBar.add(fileMenu);
		menuBar.add(menu);
		menuBar.setBackground(ColorResolver.getColor(componentProperties.getProperty("DIALOG_BACKGROUND_COLOUR")));
		return menuBar;
	}
	
	class AboutMenuAction extends AbstractAction{
		
		public AboutMenuAction(String name){
			super(name);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			AboutMenuDialog dialog = new AboutMenuDialog(MenuBarHelper.this.owner,rb.getString("MenuBarHelper.about.txt"));
			dialog.pack();
			
			Dimension screenSize =  Toolkit.getDefaultToolkit().getScreenSize();
	        Dimension labelSize = dialog.getPreferredSize();
	        dialog.setLocation(screenSize.width/2 - (labelSize.width/2),screenSize.height/2 - (labelSize.height/2));
	        dialog.setVisible(true);
		}
		
	}
	
	class LicenseMenuAction extends AbstractAction{
		
		public LicenseMenuAction(String name){
			super(name);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			LicenseDialog dialog = new LicenseDialog(MenuBarHelper.this.owner,rb.getString("MenuBarHelper.license.txt"));
			dialog.pack();
			
			Dimension screenSize =  Toolkit.getDefaultToolkit().getScreenSize();
	        Dimension labelSize = dialog.getPreferredSize();
	        dialog.setLocation(screenSize.width/2 - (labelSize.width/2),screenSize.height/2 - (labelSize.height/2));
	        dialog.setVisible(true);
		}
		
	}
}
