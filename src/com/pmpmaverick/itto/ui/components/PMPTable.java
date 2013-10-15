package com.pmpmaverick.itto.ui.components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class PMPTable extends JTable implements PMPConstants{
	Properties componentProperties;
	public PMPTable(TableModel model){
		super(model);
		try {
			componentProperties = new Properties();
			componentProperties.load(getClass().getClassLoader().getResourceAsStream("com/pmpmaverick/itto/ui/components/Component.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setProperties();
	}
	
	private void setProperties(){
		for(int i=0; i<getColumnModel().getColumnCount(); i++) {
			TableColumn tc = getColumnModel().getColumn(i);
            if(tc!=null)
                tc.setHeaderRenderer(new TableHeadRenderer());
        }
		setForeground(ColorResolver.getColor(componentProperties.getProperty("TABLE_FOREGROUND_COLOUR")));
		setBackground(ColorResolver.getColor(componentProperties.getProperty("DEFAULT_BACKGROUND_COLOUR")));
		getTableHeader().setBackground(ColorResolver.getColor(componentProperties.getProperty("TABLE_HEADER_BACKGROUND_COLOUR")));
		getTableHeader().setForeground(ColorResolver.getColor(componentProperties.getProperty("TABLE_HEADER_FOREGROUND_COLOUR")));
		setSelectionBackground(ColorResolver.getColor(componentProperties.getProperty("TABLE_SELECTION_BACKGROUND_COLOUR")));
		setSelectionForeground(ColorResolver.getColor(componentProperties.getProperty("TABLE_SELECTION_FOREGROUND_COLOUR")));
		setGridColor(ColorResolver.getColor(componentProperties.getProperty("DEFAULT_FOREGROUND_COLOUR")));
		setRowHeight(18);
        setIntercellSpacing(new Dimension(0,2));
        setShowHorizontalLines(true);
        setShowGrid(true);
        setShowVerticalLines(false);
        setIntercellSpacing(new Dimension(0,2));
        setFont(new Font("Arial",Font.PLAIN,11));
        setSelectionMode(getSelectionModel().SINGLE_SELECTION);
        //setRowSelectionInterval(0,0);
        setDefaultRenderer( Object.class, new PMPTableCellRenderer() );
	}
	
	class PMPTableCellRenderer extends DefaultTableCellRenderer{
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
			JComponent component =(JComponent)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			if(isSelected){
				component.setFont(new Font("Arial",Font.BOLD,11));
			}
			else{
				component.setFont(new Font("Arial",Font.PLAIN,11));
			}
			return component;
		}
	}
	
	class TableHeadRenderer extends DefaultTableCellRenderer {
		public TableHeadRenderer() {

		}

		public Component getTableCellRendererComponent( JTable table,
			Object value,
			boolean isSelected,
			boolean hasFocus,
			int row,
			int column) {
				setHorizontalAlignment(JLabel.CENTER);
				String columnTitle = (value==null) ? "" : value.toString();
				setFont(new Font("Arial",Font.BOLD,11));
				setText(columnTitle );
				JTableHeader sHeader = table.getTableHeader();
				if(sHeader!=null) {
					setForeground(ColorResolver.getColor(componentProperties.getProperty("TABLE_HEADER_FOREGROUND_COLOUR"))); 
					setBackground(ColorResolver.getColor(componentProperties.getProperty("TABLE_HEADER_BACKGROUND_COLOUR"))); 
					setHorizontalTextPosition(this.CENTER);
					setHorizontalAlignment(this.CENTER);
				}
				//setBorder(UIManager.getBorder("TableHeader.cellBorder"));
				return this;
		}
	}
}
