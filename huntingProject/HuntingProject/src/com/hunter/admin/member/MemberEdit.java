package com.hunter.admin.member;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;

public class MemberEdit extends JFrame{
	MemberMain memberMain;
	// 회원 추가
		JTextField t_member_id; //
		JTextField t_member_phone; //
		JTextField t_member_pw; //
		JTextField t_member_date;

		JLabel l_member_id;
		JLabel l_member_phone;
		JLabel l_member_pw;
		JLabel l_member_date;

		JButton bt_ok; // 
		JButton bt_cancle; // 취소
		
		int member_list_id;
		int id;
		String phone;
		String pw;
		String date;
		
	
	public MemberEdit(MemberMain memberMain,int member_list_id) {
		getContentPane().setBackground(new Color(230, 230, 250));
		this.memberMain=memberMain;
		this.member_list_id=member_list_id;
		setTitle("회원 등록");
		t_member_id = new JTextField(); 
		t_member_id.setBounds(245, 94, 175, 25);
		t_member_phone = new JTextField(); 
		t_member_phone.setBounds(245, 124, 175, 25);
		t_member_pw = new JTextField(); 
		t_member_pw.setBounds(245, 154, 175, 25);
		t_member_date = new JTextField(); 
		t_member_date.setBounds(245, 184, 175, 25);

		l_member_id = new JLabel("회원 이름");
		l_member_id.setHorizontalAlignment(SwingConstants.CENTER);
		l_member_id.setBounds(65, 94, 175, 25);
		l_member_phone = new JLabel("전화번호");
		l_member_phone.setHorizontalAlignment(SwingConstants.CENTER);
		l_member_phone.setBounds(65, 124, 175, 25);
		l_member_pw = new JLabel("비밀번호");
		l_member_pw.setHorizontalAlignment(SwingConstants.CENTER);
		l_member_pw.setBounds(65, 154, 175, 25);
		l_member_date = new JLabel("등록날짜");
		l_member_date.setHorizontalAlignment(SwingConstants.CENTER);
		l_member_date.setBounds(65, 184, 175, 25);

		bt_ok = new JButton("수정 하기");
		bt_ok.setBackground(Color.WHITE);
		bt_ok.setBounds(65, 255, 175, 25);
		bt_cancle = new JButton(" 취소");
		bt_cancle.setBounds(245, 255, 175, 25);
		Dimension d = new Dimension(175, 25);
		
		t_member_id.setPreferredSize(d);
		t_member_phone.setPreferredSize(d);
		t_member_pw.setPreferredSize(d);
		t_member_date.setPreferredSize(d);
		t_member_id.enable(false);
		t_member_date.enable(false);

		l_member_id.setPreferredSize(d);
		l_member_phone.setPreferredSize(d);
		l_member_pw.setPreferredSize(d);
		l_member_date.setPreferredSize(d);
		

		bt_ok.setPreferredSize(d);
		bt_cancle.setPreferredSize(d);
		getContentPane().setLayout(null);
		
		getContentPane().add(l_member_id);
		getContentPane().add(t_member_id);
		getContentPane().add(l_member_phone);
		getContentPane().add(t_member_phone);
		getContentPane().add(l_member_pw);
		getContentPane().add(t_member_pw);
		getContentPane().add(l_member_date);
		getContentPane().add(t_member_date);
		getContentPane().add(bt_ok);
		getContentPane().add(bt_cancle);
		
		getMember();
		
		t_member_id.setText(Integer.toString(id));
		t_member_phone.setText(phone);
		t_member_pw.setText(pw);
		t_member_date.setText(date);
		
		

		bt_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit();
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
	
	public void getMember() {
		Connection con = memberMain.main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select member_list_id, member_list_phone,member_list_pw,member_list_date");
		//sb.append("select *");
		sb.append(" from member_list");
		sb.append(" where member_list_id="+member_list_id);

		// System.out.println(sb.toString());
		try {
			pstmt = con.prepareStatement(sb.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			
				rs.next();
				id= rs.getInt("member_list_id");
				phone = rs.getString("member_list_phone");
				pw = rs.getString("member_list_pw");
				date = rs.getString("member_list_date");
		}catch (SQLException e) {
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
	
	public void edit() {
		Connection con = memberMain.main.getCon();
		PreparedStatement pstmt = null;
		String phone = t_member_phone.getText();
		String pw = t_member_pw.getText();
		
		String sql = "update member_list set member_list_phone='"+phone+"',member_list_pw='"+pw+"' where member_list_id="+member_list_id;
		System.out.println(sql);
		try {
			pstmt = con.prepareStatement(sql);
			int result = pstmt.executeUpdate();
			if (result == 0) {
				JOptionPane.showMessageDialog(this, "수정실패");
			} else {
				JOptionPane.showMessageDialog(this, "수정완료");
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
		
		memberMain.md.selectAll();
		memberMain.table.updateUI();
		setVisible(false);
	}
	
}
