package com.hunter.admin.money;

import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.hunter.admin.Main;
import com.hunter.util.StringUtil;

public class MoneyDetail {

	// 파일 관련
	MoneyMain memberMain;

	String userData = "C:/data";
	ArrayList type_list = new ArrayList();
	ArrayList option_list = new ArrayList();

	public MoneyDetail(MoneyMain memberMain) {
		// 메뉴 추가할 때..
		this.memberMain = memberMain;

	}

	/*public void selectAll() {
		Connection con = memberMain.main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sb = new StringBuffer();
		//sb.append("select member_list_id, member_list_phone,member_list_pw,member_list_date");
		sb.append("select *");
		sb.append(" from member_list order by member_list_id asc");
		//sb.append(" order by member_list_id asc ");

		// System.out.println(sb.toString());
		try {
			pstmt = con.prepareStatement(sb.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();

			Object[][] data = new Object[total][memberMain.model.columnTitle.length];
			rs.beforeFirst();
			for (int i = 0; i < total; i++) {
				rs.next();
				data[i][0] = rs.getInt("member_list_id");
				data[i][1] = rs.getString("member_list_phone");
				data[i][2] = rs.getString("member_list_pw");
				data[i][3] = rs.getString("member_list_date");

			}
			memberMain.model.data = data;
			memberMain.table.updateUI();
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
	}*/

}
