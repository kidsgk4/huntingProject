package com.hunter.user.login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.hunter.user.User;
import com.hunter.user.UserMain;
import com.hunter.user.regist.RegistMain;

import javax.swing.SwingConstants;

public class LoginMain extends JFrame {
	UserMain userMain;
	User user;
	public RegistMain registMain;

	JPanel p_center;
	JLabel la_title, la_phone, la_password;
	JTextField t_phone;
	JPasswordField t_password;
	JButton bt_login;
	JButton bt_cancel;

	String type = "회원"; // 자동 부여

	public LoginMain(UserMain userMain, User user) {
		this.userMain = userMain;
		this.user = user;
		p_center = new JPanel();
		la_phone = new JLabel("전화번호");
		la_phone.setHorizontalAlignment(SwingConstants.CENTER);
		la_phone.setBounds(60, 60, 150, 50);
		la_password = new JLabel("비밀번호");
		la_password.setHorizontalAlignment(SwingConstants.CENTER);
		la_password.setBounds(60, 120, 150, 50);
		t_phone = new JTextField(20);
		t_phone.setHorizontalAlignment(SwingConstants.CENTER);
		t_phone.setBounds(214, 60, 226, 50);
		t_password = new JPasswordField(20);
		t_password.setHorizontalAlignment(SwingConstants.CENTER);
		t_password.setBounds(214, 120, 226, 50);

		// 사이즈 조정하기
		Dimension d = new Dimension(150, 50);
		la_phone.setPreferredSize(d);
		la_password.setPreferredSize(d);
		t_phone.setPreferredSize(d);
		t_password.setPreferredSize(d);
		la_phone.setFont(new Font("MD개성체", Font.BOLD, 20));
		la_password.setFont(new Font("MD개성체", Font.BOLD, 20));
		p_center.setLayout(null);
		la_title = new JLabel("LOGIN");
		la_title.setBounds(160, 0, 150, 50);
		p_center.add(la_title);
		la_title.setHorizontalAlignment(SwingConstants.CENTER);
		la_title.setPreferredSize(new Dimension(200, 100));

		la_title.setFont(new Font("MD개성체", Font.BOLD, 40));
		p_center.add(la_phone);
		p_center.add(t_phone);
		p_center.add(la_password);
		p_center.add(t_password);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(p_center);
		bt_login = new JButton("로그인");
		bt_login.setBounds(130, 200, 100, 30);
		p_center.add(bt_login);

		bt_login.setPreferredSize(new Dimension(100, 30));
		bt_login.setFont(new Font("MD개성체", Font.BOLD, 20));
		bt_cancel = new JButton("취소");
		bt_cancel.setBounds(260, 200, 100, 30);
		p_center.add(bt_cancel);
		bt_cancel.setPreferredSize(new Dimension(100, 30));
		bt_cancel.setFont(new Font("MD개성체", Font.BOLD, 20));
		
		bt_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeLogin();
			}
		});

		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 사용자가 입력한 값 받아오기
				String phone = t_phone.getText();
				String password = new String(t_password.getPassword());
				userMain.getLogin("login",user.getTableIp(),phone,password);
				closeLogin();
				
			}
		});

		setSize(500, 300);
		setLocationRelativeTo(null);
		setVisible(true);// 창띄우기
	}

	public void closeLogin() {
		setVisible(false);
	}
}