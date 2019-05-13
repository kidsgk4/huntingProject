package com.hunter.user.home;

import javax.swing.table.AbstractTableModel;

public class BillModel extends AbstractTableModel{
	String[] columnName= {"no.","메뉴이름","메뉴가격","수량"};
	public String[][] data=new String[1][1];
	
	public int getRowCount() {
		return data.length;
	}
	public int getColumnCount() {
		return columnName.length;
	}
	//컬럼의 이름을 추출하자
	public String getColumnName(int col) {
		return columnName[col];
	}
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	
}
