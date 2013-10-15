package com.pmpmaverick.itto.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.WindowConstants;

import org.jdesktop.swingx.JXHyperlink;

import com.pmpmaverick.itto.ui.components.PMPDialog;
import com.pmpmaverick.itto.ui.components.PMPPanel;

public class ContactUSDialog extends PMPDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7997152700395293789L;
	Properties componentProperties;
	private JXHyperlink mailtoLink;
	private JXHyperlink blogLink;
	
	public ContactUSDialog(Frame frame,String title){
		super(frame, title, true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		try{
			
			try {
				componentProperties = new Properties();
				componentProperties.load(getClass().getClassLoader().getResourceAsStream("com/pmpmaverick/itto/ui/components/Component.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
			mailtoLink = new JXHyperlink();
			mailtoLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			mailtoLink.setClickedColor(Color.red);
			mailtoLink.setAlignmentY(CENTER_ALIGNMENT);
			mailtoLink.setURI(new URI("mailto:sagar.mainkar@gmail.com"));
			
			blogLink = new JXHyperlink();
			blogLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			blogLink.setClickedColor(Color.red);
			blogLink.setURI(new URI("http://pmpnerd.blogspot.in"));
			blogLink.setAlignmentY(LEFT_ALIGNMENT);
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		PMPPanel panel = new PMPPanel(new BorderLayout());
		
		
		PMPPanel linkPanel = new PMPPanel(new GridBagLayout());
		GridBagConstraints gBC = new GridBagConstraints();
		gBC.fill = GridBagConstraints.HORIZONTAL;
		
		
		Font font = new Font("Arial", Font.BOLD, 12);
		JLabel contact = new JLabel();
		contact.setText("Contact:");
		contact.setFont(font);
		gBC.weightx = 0.5;
        gBC.gridx = 0;
        gBC.gridy = 0;
		
		linkPanel.add(contact,gBC);
		
		
		gBC.weightx = 1;
        gBC.gridx = 1;
        gBC.gridy = 0;
		
		linkPanel.add(mailtoLink,gBC);
		
		JLabel blog = new JLabel();
		blog.setText("Blog:");
		blog.setFont(font);
		gBC.weightx = 0.5;
        gBC.gridx = 0;
        gBC.gridy = 1;
		
		linkPanel.add(blog,gBC);
		
		
		gBC.weightx = 0.5;
        gBC.gridx = 1;
        gBC.gridy = 1;
		linkPanel.add(blogLink,gBC);
		
		panel.add(linkPanel,BorderLayout.CENTER);
		getContentPane().add(panel);
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		Dimension dim=toolKit.getScreenSize();
		Dimension prefDim= new Dimension(dim.width/5,dim.height/6);
		
		setPreferredSize(prefDim);
		setResizable(false);
		
		
	}

}

