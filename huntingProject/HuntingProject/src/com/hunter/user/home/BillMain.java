package com.hunter.user.home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.hunter.user.User;
import com.hunter.user.UserMain;

public class BillMain extends JPanel {
	UserMain userMain;
	User user;

	JLabel la_tableNum;
	public JTable table;
	JScrollPane scroll;
	JLabel la_total;
	JButton bt_exit;

	int tableNum;// 테이블 번호
	String userId;// 사용자 아이디
	public int sum = 0; // 총 주문 금액

	public BillModel billModel; // 테이블 모델
	
	public String menu_list_name;
	public String menu_list_price;
	public int order_detail_count;
	public int row;
	public String[][] data = new String[1][1];

	public BillMain(UserMain userMain, User user) {
		this.userMain = userMain;
		this.user = user;

		la_tableNum = new JLabel();
		la_total = new JLabel();
		bt_exit = new JButton("닫기");
		// 테이블 관련
		table = new JTable(5, 6);
		scroll = new JScrollPane(table);

		// 서식설정
		Dimension d = new Dimension(400, 50);
		la_tableNum.setPreferredSize(d);
		la_total.setPreferredSize(new Dimension(200, 50));

		la_tableNum.setFont(new Font("돋움", Font.BOLD, 20));
		la_total.setFont(new Font("돋움", Font.BOLD, 20));

		add(la_tableNum);
		add(scroll);
		add(la_total);
		add(bt_exit);

		bt_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				sum=0;
			}
		});
		
		setBackground(Color.pink);
		setPreferredSize(new Dimension(500, 650));
	}


	// 테이블 모델적용 하는 메서드
	public void getRecords() {
		
		userMain.getBill("bill",user.getTableIp(),Integer.toString((user.getEntryListId())));
		billModel = new BillModel();
		/*
			String[][] data = new String[total][4];
			
			for (int i = 0; i < total; i++) {
				rs.next();
				data[i][0] = (Integer.toString(i+1));
				int price=rs.getInt("menu_list_price");
				int count=rs.getInt("order_detail_count");
				sum+=price*count;

				for (int a = 1; a < 4; a++) {
					data[i][a] = rs.getString(a);
				}
			}
			*/
			// 모델의 멤버변수에 덮어쓰기
			//billModel.data = data;// 교체!!

			// setModel의 적용시점은 모든 업무가 끝날때
			table.setModel(billModel);

	
	}
	// 로그인한 사람의 테이블번호, 아이디 보여주기
	public void setUserInfo() {
		tableNum = user.getTableNum();
		la_tableNum.setText("테이블 번호 : " + tableNum + "    ");
		la_total.setText("총 합계: " + sum + " 원");
		if (user.getFlag()) {
			userId = user.getMemeber().getId();
			la_total.setText("총 합계: " + sum + " 원");
		} else {
			userId = (Integer.toString(user.getGuest().getGuestId()));
		}
	}
	//테이블 관련 디자인
	public void tableDesign() {
		table.setRowHeight(50);
		table.getColumn("no.").setPreferredWidth(1);
		table.getColumn("수량").setPreferredWidth(1);
		table.updateUI();
	}
}
