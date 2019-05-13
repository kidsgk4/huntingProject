package com.hunter.user.order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.hunter.user.UserMain;

public class FoodPage extends JPanel {
	UserMain main;
	OrderList orderList;
	String path = "C:/java_developer/spring_workspace/HuntingProject/res/data";

	JPanel container;
	JPanel p_south;
	JScrollPane scroll;
	int total;
	// JPanel p_area;
	Bag bag;

	public FoodPage(UserMain main,OrderList orderList,Bag bag) {
		this.main = main;
		this.bag = bag;
		this.orderList = orderList;
		this.setLayout(new BorderLayout());
		container = new JPanel();
		p_south = new JPanel();
		container.setLayout(new GridLayout(2, 3));
		scroll = new JScrollPane(container);
		scroll.setPreferredSize(new Dimension(700, 800));
		container.setBackground(Color.BLACK);
		add(scroll);
		selectAll();

	}

	public void selectAll() {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from menu_list where menu_type_id = 1 order by menu_list_id asc";
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String filename = path + rs.getString("menu_list_img");
				int product_id = rs.getInt("menu_list_id");
				String product_name = rs.getString("menu_list_name");
				String price = rs.getString("menu_list_price");

				Product product = new Product(orderList, bag,filename,product_id, product_name, price);
				container.add(product);
			}
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
