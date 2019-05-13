	package com.hunter.admin.menu;
	
	import java.awt.Image;
	
	import javax.swing.ImageIcon;
	import javax.swing.table.AbstractTableModel;
	
	public class MenuTableModel extends AbstractTableModel {
		String[] columnTitle = { "메뉴 번호", " 이미지", "메뉴명", "분류", "가격", "옵션" };
		Object[][] data;
		ImageIcon icon;
		Image img;
		String path = "C:/data/";
	
		public String getColumnName(int col) {
			return columnTitle[col];
		}
	
		public int getColumnCount() {
			return columnTitle.length;
		}
	
		public int getRowCount() {
			return data.length;
		}
		
		public Class getColumnClass(int col) {
	
			return getValueAt(0, col).getClass();
		}
	
		public Object getValueAt(int rowIndex, int columnIndex) {
			return data[rowIndex][columnIndex];
		}
		
		public void setValueAt(Object obj, int row, int col) {
			if (col == 0) {//체크박스만 값을 변경할수 있도록 조건을 부여
				data[row][col] = obj;
			}
		}
		
		public boolean isCellEditable(int row, int col) {
			return true;
		}
	
	}