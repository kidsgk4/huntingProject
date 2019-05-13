package com.hunter.user.chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.hunter.user.UserMain;

/*
 * ������ ���̺� ��ȣ�� ip�� �����ϰ� ��ȯ�ؼ�
 * ���� ��ܿ� �ִ� ��� ������ ��ȭ�� �� �� �����Ѵ�.
 * */
public class ChadanList {
	String TAG = this.getClass().getName();
	Map<String, String> chadan = new HashMap<>();
	UserMain userMain;
	String theirIp;
	public ChadanList(UserMain userMain, String theirIp) {
		System.out.println(TAG + ", ChadanList�� �޸𸮿� �ö󰬴�.");
		this.userMain = userMain;
		this.theirIp = theirIp;
		addChadanList(this.theirIp);
	}
	
	//���ܸ�Ͽ� ��� �޼���
	public void addChadanList(String chadanIp) {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// db������ ������ ��� ip�� ��ȸ�ؼ� ����� ���ܸ���Ʈ �ʿ� ��´�.
		String sql = "select table_no,table_ip from table_info where table_ip=?";
		try {
			pstmt = con.prepareStatement(sql);
			System.out.println(TAG+"������ �������ּ� ? "+chadanIp);
			pstmt.setString(1, chadanIp);
			rs = pstmt.executeQuery();
			if(rs.next()) {				
				chadan.put(Integer.toString(rs.getInt("table_no")), rs.getString("table_ip"));
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
	
	//���ܸ�� ��� �޼���
	public Map<String, String> getChadanList() {
		return chadan;
	}	
	
}
