package com.pmpmaverick.itto.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Painter;
import javax.swing.SwingWorker;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import com.pmpmaverick.itto.util.DBHelper;

public class ITTOExplorerSplashScreen extends JWindow{
	
		private JProgressBar progress;
		private JFrame frame;
		
	    public ITTOExplorerSplashScreen(String filename,final JFrame frame, int waitTime)
	    {
	        super(frame);
	        JLabel l = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(filename)));
	        getContentPane().add(l, BorderLayout.CENTER);
	        progress = new JProgressBar();
	        getContentPane().add(progress, BorderLayout.SOUTH);
	        pack();
	        Dimension screenSize =
	          Toolkit.getDefaultToolkit().getScreenSize();
	        Dimension labelSize = l.getPreferredSize();
	        setLocation(screenSize.width/2 - (labelSize.width/2),
	                    screenSize.height/2 - (labelSize.height/2));
	        
	        setVisible(true);
	      /*  addMouseListener(new MouseAdapter()
	            {
	                public void mousePressed(MouseEvent e)
	                {
	                    setVisible(false);
	                    dispose();
	                }
	            });
	        final int pause = waitTime;
	        final Runnable closerRunner = new Runnable()
	            {
	                public void run()
	                {
	                    setVisible(false);
	                    dispose();
	                    ITTOExplorer explorer = new ITTOExplorer();
	                    URL url = ITTOExplorer.class.getClassLoader().getResource("images/pmp.png");
	            		Toolkit kit = Toolkit.getDefaultToolkit();
	            		Image img = kit.createImage(url);
	            		frame.setIconImage(img);
	            		frame.getContentPane().add(explorer);
	            		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            		frame.setSize(700, 700);
	            		frame.setVisible(true);
	            		//explorer.setDividerLocations();
	                }
	            };
	        Runnable waitRunner = new Runnable()
	            {
	                public void run()
	                {
	                    try
	                        {
	                            //Thread.sleep(pause);
	                    		DBHelper.loadDB();
	                            SwingUtilities.invokeAndWait(closerRunner);
	                        }
	                    catch(Exception e)
	                        {
	                            e.printStackTrace();
	                            // can catch InvocationTargetException
	                            // can catch InterruptedException
	                        }
	                }
	            };
	        setVisible(true);
	        Thread splashThread = new Thread(waitRunner, "SplashThread");
	        splashThread.start();*/
	        
	        
	        
	        
	        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
		    	
		    	@Override
	            protected Void doInBackground() throws Exception {
	                for (int i = 0; i < 50; i++) {
	                    Thread.sleep(10);// Simulate loading
	                    publish(i);// Notify progress
	                }
	                DBHelper.loadDB();
	                for (int i = 50; i < 100; i++) {
	                    Thread.sleep(10);// Simulate loading
	                    publish(i);// Notify progress
	                }
	                
	               
	                return null;
	            }

	            @Override
	            protected void process(List<Integer> chunks) {
	                progress.setValue(chunks.get(chunks.size() - 1));
	            }

	            @Override
	            protected void done() {
	            	 setVisible(false);
	                 dispose();
	                 ITTOExplorer explorer = new ITTOExplorer();
	                 URL url = ITTOExplorer.class.getClassLoader().getResource("images/pmp.png");
	         		Toolkit kit = Toolkit.getDefaultToolkit();
	         		Image img = kit.createImage(url);
	         		frame.setIconImage(img);
	         		frame.getContentPane().add(explorer);
	         		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	         		frame.setSize(700, 700);
	         		frame.setVisible(true);
	            }

		    };
		    
		    worker.execute();
	    }
	    
	    
	   
	    
	}

