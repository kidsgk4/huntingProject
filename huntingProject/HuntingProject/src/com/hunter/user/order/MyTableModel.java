package com.hunter.user.order;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
	String[] columnName = {"번호","상품 이름","상품 가격","수량" };
	Object[][] data; 
	ImageIcon icon;
	Image img;
	String path = "C:/data/";

	public MyTableModel() {
		// TODO Auto-generated constructor stub

		icon = new ImageIcon(path);
		img = icon.getImage();
		Image scaledImg = img.getScaledInstance(50,50, Image.SCALE_SMOOTH);
		ImageIcon thumb = new ImageIcon(scaledImg);
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnName.length;
	}

	public String getColumnName(int col) {
		return columnName[col];
	}
	public Class getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
}
