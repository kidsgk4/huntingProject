package com.hunter.user.chat;

import javax.swing.table.AbstractTableModel;

public class MyChatListTableModel extends AbstractTableModel{
	String[] columnTitle = {"No","Host","Title","Time"};
	Object[][] data;
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnTitle.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnTitle[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
}
