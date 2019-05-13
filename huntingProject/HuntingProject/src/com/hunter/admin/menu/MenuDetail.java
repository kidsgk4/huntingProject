package com.hunter.admin.menu;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;

public class MenuDetail {
	MenuMain menuMain;

	public MenuDetail(MenuMain menuMain) {
		this.menuMain = menuMain;
	}

	public void selectAll() {
		Connection con = menuMain.main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sb = new StringBuffer();
		menuMain.list.removeAll(menuMain.list);
		sb.append(
				"select l.menu_list_id,l.menu_list_img,t.menu_type_name as type_name,l.menu_list_name,l.menu_list_price,o.menu_option_name as option_name");
		sb.append(" from menu_list l, menu_type t,menu_option o");
		sb.append(" where l.menu_type_id = t.menu_type_id ");
		sb.append(" and l.menu_option_id = o.menu_option_id");
		sb.append(" order by l.menu_list_id asc");

		//System.out.println(sb.toString());
		try {
			pstmt = con.prepareStatement(sb.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();

			Object[][] data = new Object[total][menuMain.model.columnTitle.length];
			rs.beforeFirst();
			for (int i = 0; i < total; i++) {
				rs.next();
				menuMain.list.add(rs.getString("menu_list_img"));
				ImageIcon icon = new ImageIcon(menuMain.model.path + rs.getString("menu_list_img"));
				icon = new ImageIcon(icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH));
				data[i][0] = rs.getInt("menu_list_id");
				data[i][1] = icon;
				data[i][2] = rs.getString("menu_list_name");
				data[i][3] = rs.getString("type_name");
				data[i][4] = rs.getString("menu_list_price");
				data[i][5] = rs.getString("option_name");
			}
			menuMain.model.data = data;
			menuMain.table.updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
