package com.hunter.user.chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.hunter.user.UserMain;

/*
 * 차단한 테이블 번호와 ip를 보관하고 반환해서
 * 여기 명단에 있는 경우 나에게 대화를 걸 수 없게한다.
 * */
public class ChadanList {
	String TAG = this.getClass().getName();
	Map<String, String> chadan = new HashMap<>();
	UserMain userMain;
	String theirIp;
	public ChadanList(UserMain userMain, String theirIp) {
		System.out.println(TAG + ", ChadanList가 메모리에 올라갔다.");
		this.userMain = userMain;
		this.theirIp = theirIp;
		addChadanList(this.theirIp);
	}
	
	//차단목록에 담기 메서드
	public void addChadanList(String chadanIp) {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// db연결후 차단할 대상 ip로 조회해서 결과를 차단리스트 맵에 담는다.
		String sql = "select table_no,table_ip from table_info where table_ip=?";
		try {
			pstmt = con.prepareStatement(sql);
			System.out.println(TAG+"차단할 아이피주소 ? "+chadanIp);
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
	
	//차단목록 얻기 메서드
	public Map<String, String> getChadanList() {
		return chadan;
	}	
	
}
