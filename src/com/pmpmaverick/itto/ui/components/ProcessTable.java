package com.pmpmaverick.itto.ui.components;

import java.awt.event.MouseEvent;

import javax.swing.table.TableModel;

import com.pmpmaverick.itto.dao.ITTODAO;
import com.pmpmaverick.itto.entity.PMPProcess;
import com.pmpmaverick.itto.models.ProcessModel;

public class ProcessTable extends PMPTable {
	private static final long serialVersionUID = -4143997510833330786L;
	
	public ProcessTable(TableModel model) {
		super(model);
	}
	
	
	@Override
	public String getToolTipText(MouseEvent me){
		
		int row =rowAtPoint(me.getPoint());
		
		ITTODAO dao = new ITTODAO();
		
		ProcessModel model =(ProcessModel) getModel();
		
		PMPProcess process =(PMPProcess)model.getValueAt(row,0);
		
		StringBuffer sb = new StringBuffer(); 
		sb.append("<html>Inputs:"+dao.getInputsForProcess(process.getID()).size());
		
		sb.append("<br>");
		sb.append("Tools and techniques:"+dao.getToolsForProcess(process.getID()).size());
		
		
		sb.append("<br>");
		sb.append("Outputs:"+dao.getOutputsForProcess(process.getID()).size());
		sb.append("</html>");
		return sb.toString();
		
	}

	
	

}
