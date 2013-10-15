package com.pmpmaverick.itto.ui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import com.pmpmaverick.itto.dao.PMPProcessDAO;
import com.pmpmaverick.itto.entity.PMPProcess;
import com.pmpmaverick.itto.ui.components.PMPPanel;
import com.pmpmaverick.itto.ui.components.PMPScrollPane;
import com.pmpmaverick.itto.ui.components.PMPTable;
import com.pmpmaverick.itto.util.SentenceCasesChanger;



public class ReverseProcessPanel extends PMPPanel{
	PMPTable inputProcessTable;
	PMPTable ttProcessTable;
	PMPTable outputProcessTable;
	
	ReverseProcessModel inputModel;
	ReverseProcessModel outputModel;
	ReverseProcessModel ttModel;
	
	private ResourceBundle rb = ResourceBundle.getBundle("com.pmpmaverick.itto.ui.resources");
	public ReverseProcessPanel(){
		createUI();
	}
	
	private void createUI(){
		setLayout(new GridLayout(1,3));
		List<PMPProcess> emptyList = new ArrayList<PMPProcess>();
		
		inputModel = new ReverseProcessModel(emptyList,ITTOExplorer.MODEL_TYPE.INPUT);
		outputModel = new ReverseProcessModel(emptyList,ITTOExplorer.MODEL_TYPE.OUTPUT);
		ttModel = new ReverseProcessModel(emptyList,ITTOExplorer.MODEL_TYPE.TT);
		
		inputProcessTable = new PMPTable(inputModel);
		outputProcessTable = new PMPTable(outputModel);
		ttProcessTable = new PMPTable(ttModel);
		
		PMPScrollPane inputPane = new PMPScrollPane(inputProcessTable);
		PMPScrollPane ttPane = new PMPScrollPane(ttProcessTable);
		PMPScrollPane outputPane = new PMPScrollPane(outputProcessTable);
		
		add(inputPane);
		add(ttPane);
		add(outputPane);
		
		
		
	}
	
	public void clearAllSelections(){
		List<PMPProcess> emptyList = new ArrayList<PMPProcess>();
		inputModel.updateData(emptyList);
		outputModel.updateData(emptyList);
		ttModel.updateData(emptyList);
	}
	
	public void inputOutputSelected(int inputID){
		PMPProcessDAO dao = new PMPProcessDAO();
		List<PMPProcess> inputProcess = dao.getinputOutputProcesses(inputID, true);
		List<PMPProcess> outputProcess = dao.getinputOutputProcesses(inputID, false);
		inputModel.updateData(inputProcess);
		outputModel.updateData(outputProcess);
		List<PMPProcess> emptyList = new ArrayList<PMPProcess>();
		ttModel.updateData(emptyList);
		
		
	}
	
	public void toolSelected(int ttid){
		PMPProcessDAO dao = new PMPProcessDAO();
		List<PMPProcess> toolProcess =dao.getProcessesForTool(ttid);
		ttModel.updateData(toolProcess);
		List<PMPProcess> emptyList = new ArrayList<PMPProcess>();
		inputModel.updateData(emptyList);
		outputModel.updateData(emptyList);

		
	}
	class ReverseProcessModel extends AbstractTableModel{
		private List<PMPProcess> processList;
		private ITTOExplorer.MODEL_TYPE type;
		public ReverseProcessModel(List<PMPProcess> processList,ITTOExplorer.MODEL_TYPE type){
			this.processList = processList;
			this.type = type;
		}
		@Override
		public int getColumnCount() {
			return 1;
		}

		@Override
		public int getRowCount() {
			if(processList.isEmpty()){
				return 0;
			}
			return processList.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if(processList.isEmpty()){
				return "";
			}
			PMPProcess process =processList.get(rowIndex);
			return SentenceCasesChanger.toggle(process.getName());
		}
		
		public void updateData(List<PMPProcess> list){
			processList = list;
			fireTableDataChanged();
		}
		
		
		public String getColumnName(int col){
			if(type ==ITTOExplorer.MODEL_TYPE.INPUT)
				return rb.getString("ReverseProcessPanel.ReverseProcessModel.Input.header.txt");
			if(type == ITTOExplorer.MODEL_TYPE.OUTPUT)
				return rb.getString("ReverseProcessPanel.ReverseProcessModel.Output.header.txt");
			else
				return rb.getString("ReverseProcessPanel.ReverseProcessModel.TT.header.txt");
		}

	}

}
