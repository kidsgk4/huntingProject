package com.hunter.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
	Main main;
	ServerMain serverMain;
	int count =0;
	public Controller(Main main, ServerMain serverMain) {
		this.main = main;
		this.serverMain = serverMain;
		// TODO Auto-generated constructor stub
	}

	public void insertEntry(int user_list_id, int gender_type_id, int entry_list_total) {
		// TODO Auto-generated method stub
		Connection con = main.getCon();
		PreparedStatement pstmt3 = null;
		ResultSet rs3 = null;

		String table_ip = serverMain.ip;
		// db연결후 테이블 정보 담아오기
		String sql3 = "select table_info_id from table_info where table_ip=?";
		int table_info_id = 0;
		try {
			pstmt3 = con.prepareStatement(sql3);
			pstmt3.setString(1, table_ip);
			rs3 = pstmt3.executeQuery();
			if (rs3.next()) {
				table_info_id = rs3.getInt("table_info_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs3 != null) {
				try {
					rs3.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt3 != null) {
				try {
					pstmt3.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		PreparedStatement pstmt = null;

		String sql = "insert into entry_list(entry_list_id,table_info_id,user_list_id,gender_type_id,entry_list_total,entry_list_date) values(seq_entry_list.nextval,"
				+ table_info_id + "," + user_list_id + "," + gender_type_id + "," + entry_list_total + ",sysdate)";
		try {
			pstmt = con.prepareStatement(sql);
			int result = pstmt.executeUpdate();
			if (result == 0) {
				System.out.println("서버에서 entry_List 인서트 실패\n");
			} else {
				System.out.println("서버에서 entry_list 인서트 성공!\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		String sql2 = "select entry_list_id from entry_list order by entry_list_id desc";
		int entry_list_id = 0;
		try {
			pstmt2 = con.prepareStatement(sql2, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs2 = pstmt2.executeQuery();

			rs2.next();
			entry_list_id = rs2.getInt("entry_list_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(entry_list_id);

		System.out.println("test용" + table_ip + entry_list_id);
		serverMain.sendData("entryinfo", Integer.toString(table_info_id), table_ip, Integer.toString(entry_list_id));
	}

	public void checkId(String table_ip, String member_list_phone) {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String check = "";

		String sql = "select * from member_list where member_list_phone=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_list_phone);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				check = "false"; // 중복 전화번호가 있는 경우
			} else {
				check = "true"; // 가입가능 전화번호
			}
			serverMain.sendCheckIdBack("checkidback", table_ip, check);

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

	public void join(String member_list_phone, String member_list_pw, int user_type_id) {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;

		StringBuffer sb = new StringBuffer();
		sb.append(
				"insert into member_list(member_list_id ,user_type_id, member_list_phone ,member_list_pw,member_list_date)");
		sb.append(" values(seq_member_list.nextval,?,?,?,sysdate)");

		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, user_type_id);// user_type_id
			pstmt.setString(2, member_list_phone);// member_list_phone
			pstmt.setString(3, member_list_pw);// member_list_pw

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void login(String table_ip, String member_list_phone, String member_list_pw) {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String check = "";
		// db연결해서 member_list 테이블에서 비교해서 로그인 시키기
		String sql = "select * from member_list where member_list_phone='" + member_list_phone
				+ "' and member_list_pw='" + member_list_pw + "'";

		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			System.out.println(sql);
			rs = pstmt.executeQuery();
			// 바인드 변수로 쿼리 수정하기----------------------------------
			// 입력값과 db값 비교
			// 아이디와 비밀번호가 같으면 로그인 성공!
			if (rs.next()) {
				check = "true";
			} else {
				check = "false";
			}
			serverMain.sendLoginBack("checkloginback", table_ip, check);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertGuest(int user_type_id,String guest_list_name) {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;

		StringBuffer sb = new StringBuffer();
		sb.append("insert into guest_list(guest_list_id, user_type_id,guest_list_name)");
		sb.append(" values(seq_guest_list.nextval," + user_type_id + ","+guest_list_name+")");

		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.executeUpdate();
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
		
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;

		String sql2 = "select guest_list_id from guest_list where guest_list_name= "+guest_list_name;
		
		int guest_list_id = 0;
		try {
			pstmt2 = con.prepareStatement(sql2, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs2 = pstmt2.executeQuery();
			
			if (rs2.next()) {
				guest_list_id = rs2.getInt("guest_list_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		serverMain.sendGuestListIdBack("guestlistidback", Integer.toString(guest_list_id));
	}

	public void getOrderData(int table_no, int menu_list_id, String menu_list_name, int order_detail_count,
			int entry_list_id) {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;

		StringBuffer sb = new StringBuffer();
		sb.append("insert into order_summary(order_summary_id,entry_list_id,order_summary_date)");
		sb.append(" values(seq_order_summary.nextval,?,sysdate)");

		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, entry_list_id);
			pstmt.executeUpdate();
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
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		String sql2 = "select order_summary_id from order_summary where entry_list_id = " + entry_list_id;
		int order_summary_id = 0;
		try {
			pstmt2 = con.prepareStatement(sql2, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs2 = pstmt2.executeQuery();
			if(rs2.next()) {
				order_summary_id = rs2.getInt("order_summary_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement pstmt3 = null;

		StringBuffer sb3 = new StringBuffer();
		sb3.append("insert into order_detail(order_detail_id,order_summary_id,menu_list_id,order_detail_count)");
		sb3.append(" values(seq_order_detail.nextval,?,?,?)");
		try {
			pstmt3 = con.prepareStatement(sb3.toString());
			pstmt3.setInt(1, order_summary_id);
			pstmt3.setInt(2, menu_list_id);
			pstmt3.setInt(3, order_detail_count);
			pstmt3.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt3 != null) {
				try {
					pstmt3.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void billUpdate(String table_ip, int entry_list_id) {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sb = new StringBuffer();
		sb.append(
				"select entry_list_id, menu_list_name, menu_list_price,order_detail_count from order_detail od, menu_list m,order_summary os where od.menu_list_id=m.menu_list_id and od.order_summary_id = os.order_summary_id and entry_list_id = "
						+ entry_list_id);

		String menu_list_name = "";
		String menu_list_price = "";
		int order_detail_count = 0;
		try {
			pstmt = con.prepareStatement(sb.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int row = rs.getRow();
			rs.beforeFirst();
			while (rs.next()) {
				menu_list_name = rs.getString("menu_list_name");
				menu_list_price = rs.getString("menu_list_price");
				order_detail_count = rs.getInt("order_detail_count");
				
				serverMain.sendBillData("billback",menu_list_name,menu_list_price,Integer.toString(order_detail_count),Integer.toString(row));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void getMemberId(String phone) {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int memberId = 0;
		String sql = "select member_list_id from member_list where member_list_phone=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberId = rs.getInt("member_list_id");
				System.out.println("user_list에 새로 등록한 번호는" + phone + ", pk:" + memberId);
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
		serverMain.sendMemberId("memberidback",Integer.toString(memberId));
	}
	public void checkGame(String table_ip) {
		count++;
		
		if(count<8) {
			System.out.println(table_ip+"님은 "+count+"번 째 참가자!");
			ArrayList list = new ArrayList();
			list.add(table_ip);
		}
	}
}
