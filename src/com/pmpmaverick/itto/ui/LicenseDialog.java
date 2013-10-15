package com.pmpmaverick.itto.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import javax.swing.WindowConstants;

import com.pmpmaverick.itto.ui.components.ColorResolver;
import com.pmpmaverick.itto.ui.components.PMPDialog;
import com.pmpmaverick.itto.ui.components.PMPPanel;
import com.pmpmaverick.itto.ui.components.PMPScrollPane;
import com.pmpmaverick.itto.ui.components.PMPTextArea;

public class LicenseDialog extends PMPDialog{
	PMPTextArea textArea ;
	private String aboutText ="";
	Properties componentProperties;
	
	public LicenseDialog(Frame frame,String title){
		super(frame, title, true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		try{
			InputStreamReader ior =new InputStreamReader(getClass().getClassLoader().getResourceAsStream("com/pmpmaverick/itto/ui/License.txt"));
			BufferedReader br = new BufferedReader(ior);
			String s="";
			while((s=br.readLine())!=null){
				aboutText+=s +"\n";
			}
			textArea = new PMPTextArea(aboutText);
			textArea.setWrapStyleWord(true);
			textArea.setEditable(false);
			
			try {
				componentProperties = new Properties();
				componentProperties.load(getClass().getClassLoader().getResourceAsStream("com/pmpmaverick/itto/ui/components/Component.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Font font = new Font("Arial", Font.BOLD, 12);
			textArea.setFont(font);
			textArea.setBackground(ColorResolver.getColor(componentProperties.getProperty("DIALOG_BACKGROUND_COLOUR")));
			
		}
		catch(FileNotFoundException fne){
			fne.printStackTrace();
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		} 
		
		PMPScrollPane scrollPane = new PMPScrollPane(textArea);
		PMPPanel panel = new PMPPanel(new BorderLayout());
		panel.add(scrollPane,BorderLayout.CENTER);
		getContentPane().add(panel);
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		Dimension dim=toolKit.getScreenSize();
		Dimension prefDim= new Dimension(dim.width/2,dim.height/2);
		
		setPreferredSize(prefDim);
		
		
		
	}
}
