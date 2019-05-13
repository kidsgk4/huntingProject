	package com.hunter.admin.order;
	
	import java.awt.Image;
	
	import javax.swing.ImageIcon;
	import javax.swing.table.AbstractTableModel;
	
	public class OrderTableModel extends AbstractTableModel {
		String[] columnTitle = { "No.", "테이블 번호","메뉴명","수량","시간"};
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