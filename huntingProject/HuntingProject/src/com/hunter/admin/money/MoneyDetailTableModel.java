package com.hunter.admin.money;

import javax.swing.table.AbstractTableModel;

public class MoneyDetailTableModel extends AbstractTableModel{
	String[] columnTitle = {"No.","주문 시간","메뉴","수량","가격"};
	Object[][] data;	
	public String getColumnName(int col) {
		return columnTitle[col];
	}
	public int getColumnCount() {
		return columnTitle.length;
	}

	public int getRowCount() {
		return 10;
		//		return data.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return "아직";
		//	return data[rowIndex][columnIndex];
	}
}
