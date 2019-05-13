package com.hunter.admin.member;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;

public class MemberAdd extends JFrame{
	MemberMain memberMain;
	// 회원 추가
		JTextField t_member_phone; //
		JTextField t_member_pw; //

		JLabel l_member_phone;
		JLabel l_member_pw;

		JButton bt_ok; // 
		JButton bt_cancle; // 취소
		
		
	
	public MemberAdd(MemberMain memberMain) {
		getContentPane().setBackground(new Color(230, 230, 250));
		this.memberMain=memberMain;
		setTitle("회원 등록");

		t_member_phone = new JTextField(); 
		t_member_phone.setBounds(243, 132, 175, 25);
		t_member_pw = new JTextField(); 
		t_member_pw.setBounds(243, 162, 175, 25);

		l_member_phone = new JLabel("전화번호");
		l_member_phone.setHorizontalAlignment(SwingConstants.CENTER);
		l_member_phone.setBounds(63, 132, 175, 25);
		l_member_pw = new JLabel("비밀번호");
		l_member_pw.setHorizontalAlignment(SwingConstants.CENTER);
		l_member_pw.setBounds(63, 162, 175, 25);

		bt_ok = new JButton("등록 하기");
		bt_ok.setBounds(63, 233, 175, 25);
		bt_cancle = new JButton("등록 취소");
		bt_cancle.setBounds(243, 233, 175, 25);
		Dimension d = new Dimension(175, 25);
		
		t_member_phone.setPreferredSize(d);
		t_member_pw.setPreferredSize(d);

		l_member_phone.setPreferredSize(d);
		l_member_pw.setPreferredSize(d);

		bt_ok.setPreferredSize(d);
		bt_cancle.setPreferredSize(d);
		getContentPane().setLayout(null);
		
		getContentPane().add(l_member_phone);
		getContentPane().add(t_member_phone);
		getContentPane().add(l_member_pw);
		getContentPane().add(t_member_pw);
		getContentPane().add(bt_ok);
		getContentPane().add(bt_cancle);

		bt_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
		
		bt_cancle.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            setVisible(false);
	         }
		});
		
		setVisible(true);
		setSize(500, 400);
		setLocationRelativeTo(null);
	
	}
	
	public void regist() {
		Connection con = memberMain.main.getCon();
		PreparedStatement pstmt = null;
		String phone = t_member_phone.getText();
		String pw = t_member_pw.getText();

		
		String sql = "insert into member_list(member_list_id,member_list_phone ,member_list_pw)";
		sql += " values(seq_member_list.nextval,'"+phone+"','"+pw+"')";
		System.out.println(sql);
		try {
			pstmt = con.prepareStatement(sql);
			int result = pstmt.executeUpdate();
			if (result == 0) {
				JOptionPane.showMessageDialog(this, "등록 실패");
			} else {
				JOptionPane.showMessageDialog(this, "등록 성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		refresh();
	}
	
	public void refresh() {
		t_member_phone.setText("");
		t_member_pw.setText("");
		setVisible(false);
		memberMain.md.selectAll();
		memberMain.table.updateUI();
	}
	
}
