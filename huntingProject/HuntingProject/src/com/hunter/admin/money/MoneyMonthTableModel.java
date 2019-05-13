	package com.hunter.admin.money;
	
	import java.awt.Image;
	
	import javax.swing.ImageIcon;
	import javax.swing.table.AbstractTableModel;
	
	public class MoneyMonthTableModel extends AbstractTableModel {
		String[] columnTitle = {"no", "날짜", " 합계 매출액" };
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