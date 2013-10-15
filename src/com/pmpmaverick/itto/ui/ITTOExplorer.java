package com.pmpmaverick.itto.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXTaskPaneContainer;

import com.pmpmaverick.itto.dao.ITTODAO;
import com.pmpmaverick.itto.dao.KnowledgeAreaDAO;
import com.pmpmaverick.itto.dao.PMPProcessDAO;
import com.pmpmaverick.itto.entity.ITTO;
import com.pmpmaverick.itto.entity.KnowledgeArea;
import com.pmpmaverick.itto.entity.PMPProcess;
import com.pmpmaverick.itto.models.ProcessModel;
import com.pmpmaverick.itto.ui.components.ColorResolver;
import com.pmpmaverick.itto.ui.components.PMPCombobox;
import com.pmpmaverick.itto.ui.components.PMPPanel;
import com.pmpmaverick.itto.ui.components.PMPScrollPane;
import com.pmpmaverick.itto.ui.components.PMPTable;
import com.pmpmaverick.itto.ui.components.PMPXTaskPane;
import com.pmpmaverick.itto.ui.components.ProcessTable;
import com.pmpmaverick.itto.util.SentenceCasesChanger;

public class ITTOExplorer extends PMPPanel{
	private static final long serialVersionUID = 1130169278554912052L;
	private PMPCombobox cmbKA;
	private ProcessTable processTable;
	private PMPTable inputsTable;
	private PMPTable ttTable;
	private PMPTable outputsTable;

	private ProcessModel model;
	private InputOutputTTModel inputModel;
	private InputOutputTTModel outputModel;
	private InputOutputTTModel ttModel;
	
	private ReverseProcessPanel reverseProcessPanel;

	private OutputTableSelectionListener outputTableListener;
	private InputTableSelectionListener inputTableListener;
	private ToolTableSelectionListener toolTableListener;
	
	private JXTaskPaneContainer taskPaneContainer ;
	private PMPXTaskPane processPane;
	private PMPXTaskPane ittoPane;
	private PMPXTaskPane reverseProcPane;
	private Properties componentProperties;
	
	private static Logger logger = Logger.getLogger(ITTOExplorer.class);
	
	
	private ResourceBundle rb = ResourceBundle.getBundle("com.pmpmaverick.itto.ui.resources"); 
	
	public enum MODEL_TYPE{INPUT,OUTPUT,TT};

	public ITTOExplorer(){
		createUI();
	}
	private void createUI(){
		try {
			logger.debug("Inside createUI");
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            
			KnowledgeAreaDAO kaDAO = new KnowledgeAreaDAO();
			Vector<KnowledgeArea> kaitems = new Vector<KnowledgeArea>();
			kaitems.addAll(kaDAO.getAllKnowledgeAreas());
           
			cmbKA = new PMPCombobox(kaitems);
			KnowledgeArea ka = (KnowledgeArea)cmbKA.getSelectedItem();

			cmbKA.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){
						KnowledgeArea ka = (KnowledgeArea)e.getItem();
						ProcessFetcher pf= new ProcessFetcher(ka.id);
						pf.execute();

					}
				}
			});
			
			
			PMPProcessDAO pmpProcessDAO = new PMPProcessDAO();
			List<PMPProcess> procitems  = pmpProcessDAO.getProcessesForKA(ka.id);
			
			model = new ProcessModel(procitems);
			
			logger.debug("Knowledge Area combo model populated");
			
			processTable = new ProcessTable(model);
			processTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			processTable.setColumnSelectionAllowed(false);
			processTable.getSelectionModel().addListSelectionListener(new ProcessTableSelectionListener());
			
			
			List<ITTO> emptyList= new ArrayList<ITTO>();
			emptyList.add(new ITTO());
			inputModel = new InputOutputTTModel(emptyList,MODEL_TYPE.INPUT);
			outputModel = new InputOutputTTModel(emptyList,MODEL_TYPE.OUTPUT);
			ttModel = new InputOutputTTModel(emptyList,MODEL_TYPE.TT);

			inputsTable = new PMPTable(inputModel);
			outputsTable = new PMPTable(outputModel);
			ttTable     = new PMPTable(ttModel);
			
			
			inputsTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			inputsTable.setColumnSelectionAllowed(false);
			inputTableListener = new InputTableSelectionListener();
			inputsTable.getSelectionModel().addListSelectionListener(inputTableListener);
			
			outputsTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			outputsTable.setColumnSelectionAllowed(false);
			outputTableListener = new OutputTableSelectionListener();
			outputsTable.getSelectionModel().addListSelectionListener(outputTableListener);
			
			ttTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			ttTable.setColumnSelectionAllowed(false);
			toolTableListener= new ToolTableSelectionListener();
			ttTable.getSelectionModel().addListSelectionListener(toolTableListener);

			PMPScrollPane inputPane = new PMPScrollPane(inputsTable);
			PMPScrollPane outputPane = new PMPScrollPane(outputsTable);
			PMPScrollPane ttPane = new PMPScrollPane(ttTable);


			PMPPanel procTablePanel = new PMPPanel(new BorderLayout());
			procTablePanel.add(new PMPScrollPane(processTable),BorderLayout.CENTER);
			procTablePanel.setPreferredSize(new Dimension(screenSize.width/4,(screenSize.height/6)));

			PMPPanel inoutPanel = new PMPPanel(new GridLayout(1, 3));
			inoutPanel.add(inputPane);
			inoutPanel.add(ttPane);
			inoutPanel.add(outputPane);
			inoutPanel.setPreferredSize(new Dimension(screenSize.width/4,screenSize.height/5+20));
			
			reverseProcessPanel = new ReverseProcessPanel();
			reverseProcessPanel.setPreferredSize(new Dimension(screenSize.width/4,screenSize.height/5+20));

			taskPaneContainer = new JXTaskPaneContainer();
			processPane = new PMPXTaskPane("Processes");
			processPane.setMnemonic(new Character('P'));
			
			
			ittoPane = new PMPXTaskPane("Inputs 		Tools & Techniques		  Outputs");
			ittoPane.setMnemonic(new Character('I'));
			reverseProcPane = new PMPXTaskPane("Processes");
			reverseProcPane.setMnemonic(new Character('O'));
			
			
			
			processPane.add(procTablePanel);
			
			
			ittoPane.add(inoutPanel);
			
			reverseProcPane.add(reverseProcessPanel);
			
			taskPaneContainer.add(processPane);
			
			taskPaneContainer.add(ittoPane);
			taskPaneContainer.add(reverseProcPane);
			componentProperties = new Properties();
			componentProperties.load(getClass().getClassLoader().getResourceAsStream("com/pmpmaverick/itto/ui/components/Component.properties"));
			taskPaneContainer.setBackground(ColorResolver.getColor(componentProperties.getProperty("PANEL_BACKGROUND_COLOUR")));
			
			PMPPanel comboPanel = new PMPPanel();
			comboPanel.add(cmbKA);
			setLayout(new BorderLayout());
			add(comboPanel,BorderLayout.NORTH);
			add(taskPaneContainer,BorderLayout.CENTER);

			processTable.getSelectionModel().setSelectionInterval(0, 0);
			logger.debug("createUI completed");

        } catch (Exception e) {
        	logger.error(e.getMessage());
            e.printStackTrace();
        } 
	}
	class ProcessFetcher extends SwingWorker<String, String>{
		int KAID;
		List<PMPProcess> procitems = new Vector<PMPProcess>();
		public ProcessFetcher(int KAID){
			this.KAID =KAID;
		}
		@Override
		protected String doInBackground() throws Exception {
			logger.debug("Fetching processes");
			PMPProcessDAO dao = new PMPProcessDAO();
			procitems = dao.getProcessesForKA(KAID);

			return null;
		}

		public void done(){
			model.dataChanged(procitems);
			processTable.getSelectionModel().setSelectionInterval(0, 0);
			logger.debug("processes fetched and model generated");
		}

	}
	



	

	

	 class InputOutputTTModel extends AbstractTableModel{
		List<ITTO> data;
		private MODEL_TYPE type;

		public InputOutputTTModel(List<ITTO> data,MODEL_TYPE type){
			this.data = data;
			this.type = type;
		}
		@Override
		public int getRowCount() {
			return data.size();
		}

		public String getColumnName(int col){
			if(type ==MODEL_TYPE.INPUT)
				return rb.getString("InputOutputTTModel.InputTable.header.txt");
			if(type == MODEL_TYPE.OUTPUT)
				return rb.getString("InputOutputTTModel.OutputTable.header.txt");
			else
				return rb.getString("InputOutputTTModel.ToolTable.header.txt");
		}

		@Override
		public int getColumnCount() {

			return 1;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			String str = data.get(rowIndex)==null?"":data.get(rowIndex).toString();
			return SentenceCasesChanger.toggle(str);
		}
		
		public ITTO getDataAt(int row,int col){
			return data.get(row);
		}
		public void dataChanged(List<ITTO> data){
			this.data=data;
			fireTableDataChanged();
		}

	}

	class ProcessTableSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()){
				
				int row=processTable.getSelectedRow();
				logger.debug("ProcessTable selected row: "+row+" selected");
				if(row>=0){
					PMPProcess process =(PMPProcess)model.getValueAt(row,0);
					
					logger.debug("ProcessTable process : "+process.getName()+" selected");
					InputOutputFetcher worker = new InputOutputFetcher(process);
					worker.execute();
				}
			}

		}

	}
	
	
	class InputTableSelectionListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()){
				
				outputsTable.getSelectionModel().removeListSelectionListener(outputTableListener);
				ttTable.getSelectionModel().removeListSelectionListener(toolTableListener);
				outputsTable.clearSelection();
				ttTable.clearSelection();
				outputsTable.getSelectionModel().addListSelectionListener(outputTableListener);
				ttTable.getSelectionModel().addListSelectionListener(toolTableListener);
				int row=inputsTable.getSelectedRow();
				logger.debug("InputTable selected row: "+row+" selected");
				if(row>=0){
					ITTO input = inputModel.getDataAt(row, 0);
					
					logger.debug("InputTable input: "+input.getName()+" selected");
					reverseProcessPanel.inputOutputSelected(input.getID());
				}
			}

		}
	}
	
	class OutputTableSelectionListener implements ListSelectionListener{
		
		public void valueChanged(ListSelectionEvent e){
			if(!e.getValueIsAdjusting()){
				inputsTable.getSelectionModel().removeListSelectionListener(inputTableListener);
				ttTable.getSelectionModel().removeListSelectionListener(toolTableListener);
				inputsTable.clearSelection();
				ttTable.clearSelection();
				inputsTable.getSelectionModel().addListSelectionListener(inputTableListener);
				ttTable.getSelectionModel().addListSelectionListener(toolTableListener);
				int row=outputsTable.getSelectedRow();
				logger.debug("InputTable selected row: "+row+" selected");
				
				if(row>=0){
					ITTO output = outputModel.getDataAt(row, 0);
					logger.debug("OutputTable output: "+output.getName()+" selected");
					reverseProcessPanel.inputOutputSelected(output.getID());
				}
			}
		}
	}
	
	class ToolTableSelectionListener implements ListSelectionListener{
			
			public void valueChanged(ListSelectionEvent e){
				if(!e.getValueIsAdjusting()){
					inputsTable.getSelectionModel().removeListSelectionListener(inputTableListener);
					outputsTable.getSelectionModel().removeListSelectionListener(outputTableListener);
					inputsTable.clearSelection();
					outputsTable.clearSelection();
					inputsTable.getSelectionModel().addListSelectionListener(inputTableListener);
					outputsTable.getSelectionModel().addListSelectionListener(outputTableListener);
					int row=ttTable.getSelectedRow();
					logger.debug("ToolTable selected row: "+row+" selected");
					
					if(row>=0){
						ITTO tool = ttModel.getDataAt(row, 0);
						logger.debug("OutputTable tool: "+tool.getName()+" selected");
						reverseProcessPanel.toolSelected(tool.getID());
					}
				}
			}
		}

	class InputOutputFetcher extends SwingWorker<String, String>{
		PMPProcess process;
		List<ITTO> inputList = new ArrayList<ITTO>();
		List<ITTO> outputList = new ArrayList<ITTO>();
		List<ITTO> ttList = new ArrayList<ITTO>();
		public InputOutputFetcher(PMPProcess process){
			this.process = process;
		}

		@Override
		protected String doInBackground() throws Exception {
			ITTODAO dao = new ITTODAO();
			logger.debug("InputOutputFetcher fetching inpt output and tools and techniques for process"+process.getName());
			inputList = dao.getInputsForProcess(process.getID());
			outputList = dao.getOutputsForProcess(process.getID());
			ttList = dao.getToolsForProcess(process.getID());
			
			
			
			return null;
		}

		public void done(){
			 inputModel.dataChanged(inputList);
			 outputModel.dataChanged(outputList);
			 ttModel.dataChanged(ttList);
			 reverseProcessPanel.clearAllSelections();
			 
			 logger.debug("InputOutputFetcher fetching inpt output and tools and techniques for process completed");
			 
		}

	}

	public static void main(String[] args) throws MalformedURLException {
		
		
		 try {
			 
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//			 UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
	    
		JXFrame frame = new JXFrame();
		frame.setExtendedState(frame.getExtendedState() | JXFrame.MAXIMIZED_BOTH);
		MenuBarHelper menuHelper = new MenuBarHelper(frame);
		frame.setJMenuBar(menuHelper.getMenuBar());
		ITTOExplorerSplashScreen splash = new ITTOExplorerSplashScreen("images/ITTO.jpg",frame,1000);
		

	}


}
