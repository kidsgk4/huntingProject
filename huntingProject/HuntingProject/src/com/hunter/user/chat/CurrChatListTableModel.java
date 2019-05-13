package com.hunter.user.chat;

import javax.swing.table.AbstractTableModel;

public class CurrChatListTableModel extends AbstractTableModel{
	String[] columnTitle= {"No","Host","Guest"};
	Object[][] data;//db연동해서 메모리에 올림
	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return columnTitle.length;
	}
	//컬럼 제목 표시 메서드
	@Override
	public String getColumnName(int col) {
		return columnTitle[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

}
