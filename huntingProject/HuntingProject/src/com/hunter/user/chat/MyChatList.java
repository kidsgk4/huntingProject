package com.hunter.user.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.hunter.user.UserMain;

public class MyChatList extends JPanel{
	JPanel p_north;
	JPanel p_center;
	JLabel la_title;
	ChatMain chatMain;
	UserMain userMain;
	MyChatListTableModel model;
	JTable table;
	JScrollPane scroll;
	public MyChatList(ChatMain chatMain,UserMain userMain) {
		this.chatMain = chatMain;
		this.userMain = userMain;
		setLayout(new BorderLayout(0,0));
		p_north = new JPanel();
		la_title = new JLabel("나의 채팅 목록");
		p_center = new JPanel();
		table = new JTable();
		scroll = new JScrollPane(table);
		
		p_north.setPreferredSize(new Dimension(700, 40));
		la_title.setPreferredSize(new Dimension(680, 40));
		la_title.setBackground(Color.ORANGE);
		la_title.setOpaque(true);
		la_title.setHorizontalAlignment(SwingConstants.CENTER);
		p_center.setPreferredSize(new Dimension(700, 700));
		
		//부착
		p_north.add(la_title);
		add(p_north,BorderLayout.NORTH);
		p_center.add(scroll);
		add(p_center);
		
		setPreferredSize(new Dimension(680, 680));
		setVisible(true);

		table.setModel(model = new MyChatListTableModel());
		//테이블 레코드 채우는 메서드 호출
		selectMyChatInfo();
	}
	//테이블 레코드 채우는 메서드 정의
	public void selectMyChatInfo() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select chat_list_id,chatroom_list_id,chat_list_name,chat_list_date from chat_list";
		//실제 sql은 아래의 것
		//String sql = "select chat_list_id,발신자,chat_list_name,chat_list_data from chat_list";
		try {
			pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();
			Object[][] data = new Object[total][model.columnTitle.length];
			rs.beforeFirst();
			for(int i=0;i<total;i++) {
				data[i][0] = rs.getInt("chat_list_id");
				data[i][1] = rs.getInt("chat_room_list");
				data[i][2] = rs.getString("cha_list_name");
				//data[i][3] = rs.getString("cha_list_date");
				data[i][3] = rs.getDate("chat_list_date");
			}
			model.data=data;
			table.updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
