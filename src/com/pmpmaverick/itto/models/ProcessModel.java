package com.pmpmaverick.itto.models;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.pmpmaverick.itto.entity.PMPProcess;

public class ProcessModel extends AbstractTableModel{
	List<PMPProcess> data;
	public ProcessModel(List<PMPProcess> data){
		this.data = data;
	}
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {

		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		return data.get(rowIndex);
	}
	public void dataChanged(List<PMPProcess> data){
		this.data=data;
		fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int col){
		return "Process";
	}


}
