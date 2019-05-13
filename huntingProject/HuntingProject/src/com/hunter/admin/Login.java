package com.hunter.admin;

import java.awt.Color;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login extends JFrame {
	private JPasswordField tf_pw;
	Main main;
	// ConnectionManager connectionManager;
	// private Connection con;

	public Login(Main main) {
		this.main = main;
		// connectionManager = new ConnectionManager();
		// con = connectionManager.getConnection();
		getContentPane().setBackground(new Color(230, 230, 250));

		tf_pw = new JPasswordField();
		tf_pw.setBounds(90, 125, 115, 30);
		tf_pw.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(tf_pw);
		tf_pw.setColumns(10);

		JButton bt_login = new JButton("접속");
		bt_login.setFont(new Font("MD개성체", Font.PLAIN, 15));
		bt_login.setBounds(210, 125, 100, 30);
		getContentPane().add(bt_login);

		JLabel lblNewLabel = new JLabel("ADMINISTOR");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("MD개성체", Font.PLAIN, 24));
		lblNewLabel.setBounds(90, 81, 220, 35);
		getContentPane().add(lblNewLabel);
		setSize(400, 300);

		bt_login.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				check();
			}
		});

		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setVisible(true);
	}

	public void check() {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select admin_pw from admin";

		// System.out.println(sb.toString());
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();
			rs.beforeFirst();
			for (int i = 0; i < total; i++) {
				rs.next();
				String pw = rs.getString("admin_pw");
				if (pw.equals(tf_pw.getText())) {
					main.setVisible(true);
					this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(this, "비밀번호가 틀렸습니다.");
					return;
				}
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
