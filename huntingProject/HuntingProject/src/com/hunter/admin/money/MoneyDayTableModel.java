	package com.hunter.admin.money;
	
	import java.awt.Image;
	
	import javax.swing.ImageIcon;
	import javax.swing.table.AbstractTableModel;
	
	public class MoneyDayTableModel extends AbstractTableModel {
		String[] columnTitle = { "No.", " 테이블 번호", "회원 ID", "결제 시간", "금액"};
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