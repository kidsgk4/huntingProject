/*
 *ChatMain::대기실 플랫폼 역할 
 *ChatMain의 좌하단 패널에 현재 개설된 채팅방의 수와 게스트,호스트만 가려주는
 *JTable을 표시하는 클래스를 정의
 * */
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

public class CurrentChatList extends JPanel{
	JPanel p_north;
	JPanel p_center;
	JLabel la_title;
	JTable table;
	JScrollPane scroll;
	ChatMain chatMain;
	CurrChatListTableModel model;
	UserMain userMain;
	public CurrentChatList(ChatMain chatMain,UserMain userMain) {
		this.chatMain = chatMain;
		this.userMain = userMain;
		setLayout(new BorderLayout(0,0));
		p_north = new JPanel();
		la_title = new JLabel("현재 채팅 현황");
		p_center = new JPanel();
		table = new JTable(1,3);
		scroll = new JScrollPane(table);
		
		//컴포넌트 크기,색상,정렬처리
		p_north.setPreferredSize(new Dimension(700, 40));
		la_title.setPreferredSize(new Dimension(680, 40));
		la_title.setBackground(Color.ORANGE);
		la_title.setOpaque(true);
		la_title.setHorizontalAlignment(SwingConstants.CENTER);
		p_center.setPreferredSize(new Dimension(700, 410));
		
		
		//부착
		p_north.add(la_title);
		add(p_north,BorderLayout.NORTH);
		p_center.add(scroll);

		add(p_center);
		
		
		
		setSize(new Dimension(700, 450));
		//setPreferredSize(new Dimension(700, 450));
		setVisible(true);

		//table model
		table.setModel(model = new CurrChatListTableModel());
		getChatList();
	}
	
	//db테이블 조회해서 레코드 가져오기 메서드
	public void getChatList() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select chatroom_list_id,sender_id,receiver_id from chatroom_list";
		
		try {
			pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();
			Object[][] data = new Object[total][model.columnTitle.length];
			rs.beforeFirst();
			//여기서 선언한 data 2차원배열 채워넣기
			//for(Object records:data) {
			for(int i=0;i<total;i++) {
				data[i][0]=rs.getInt("chatroom_list_id");
				data[i][1]=rs.getInt("sender_id");
				data[i][2]=rs.getInt("receiver_id");
			}
			model.data=data;//모델에 있는 데이터배열에 현 클래스에서 작성한 데이터 배열 주입
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
