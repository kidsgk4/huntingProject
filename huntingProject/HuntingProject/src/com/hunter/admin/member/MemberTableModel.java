package com.hunter.admin.member;

import javax.swing.table.AbstractTableModel;

public class MemberTableModel extends AbstractTableModel{
	String[] columnTitle = {"회원 번호","전화번호","비밀번호","등록날짜"};
	Object[][] data;	
	public String getColumnName(int col) {
		return columnTitle[col];
	}
	public int getColumnCount() {
		return columnTitle.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
}
