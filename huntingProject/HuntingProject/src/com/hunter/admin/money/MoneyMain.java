package com.hunter.admin.money;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.hunter.admin.Main;
import com.hunter.util.StringUtil;

public class MoneyMain extends JPanel {
	Main main;
	MoneyDetail md;

	JPanel p_west, p_east;
	JButton bt_showAll;
	JButton bt_search;
	JTextField t_date;
	JButton bt_detail;

	// 테이블 관련
	JTable monthTable;
	JTable dayTable;
	JScrollPane scroll, scroll2;
	MoneyMonthTableModel monthTableModel;
	MoneyDayTableModel dayTableModel;
	
/////////////////////////////수정//////////////////////////////////
	int table_no, member_id;

	int row;
	int col;
	
	String date;//조회하려는 날짜
//////////////////////////////////////////////////////////////////
	
	public MoneyMain(Main main) {
		this.main = main;

		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(1400, 800));

		p_west = new JPanel();
		add(p_west, BorderLayout.WEST);
		p_west.setPreferredSize(new Dimension(480, 800));
		p_west.setLayout(null);

		p_east = new JPanel();
		add(p_east, BorderLayout.EAST);
		p_east.setPreferredSize(new Dimension(900, 800));
		p_east.setLayout(null);

		monthTable = new JTable();
		monthTableModel = new MoneyMonthTableModel();
		monthTable.setRowHeight(65);
		monthTable.setFont(new Font("MD개성체", Font.PLAIN, 15));
		scroll = new JScrollPane(monthTable);
		scroll.setSize(330, 680);
		scroll.setLocation(100, 80);
		scroll.setPreferredSize(new Dimension(330, 700));
		p_west.add(scroll);

		dayTable = new JTable();
		dayTableModel = new MoneyDayTableModel();
		dayTable.setRowHeight(65);
		dayTable.setFont(new Font("MD개성체", Font.PLAIN, 15));
		scroll2 = new JScrollPane(dayTable);
		scroll2.setLocation(100, 60);
		scroll2.setSize(700, 650);
		scroll2.setPreferredSize(new Dimension(700, 600));
		p_east.add(scroll2);

		bt_detail = new JButton("상세 보기");
		bt_detail.setBounds(400, 750, 100, 30);
		bt_detail.setFont(new Font("MD개성체", Font.PLAIN, 15));
		p_east.add(bt_detail);
		bt_showAll = new JButton("전체 보기");
		bt_showAll.setBounds(100, 10, 100, 30);
		bt_showAll.setFont(new Font("MD개성체", Font.PLAIN, 15));
		p_west.add(bt_showAll);
		t_date = new JTextField();
		t_date.setBounds(100, 45, 200, 30);
		t_date.setFont(new Font("MD개성체", Font.PLAIN, 15));
		p_west.add(t_date);
		bt_search = new JButton("조 회");
		bt_search.setBounds(330, 45, 100, 30);
		bt_search.setFont(new Font("MD개성체", Font.PLAIN, 15));
		p_west.add(bt_search);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(20, 20, 1, 770);
		p_east.add(separator);

		bt_showAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("bt_showAll 눌렀어?");
/////////////////////////////수정//////////////////////////////////
				showAllMonth();
				t_date.setText("");
//////////////////////////////////////////////////////////////////
			}
		});
		bt_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("bt_search 눌렀어?");
/////////////////////////////수정//////////////////////////////////
				showMonth();
				t_date.setText("");
//////////////////////////////////////////////////////////////////
			}
		});
		bt_detail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (member_id == 0) {
					JOptionPane.showMessageDialog(main, "확인할 테이블을 선택해 주세요!");
					return;
				}
				showDetail();
			}
		});
		
///////////////////////////수정//////////////////////////////////
		// p_west 테이블 리스너
		monthTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = monthTable.getSelectedRow();
				col = 1;
				String origindate= (String) monthTable.getValueAt(row, col);
				date=StringUtil.getDate(origindate);
				
				System.out.println("monthTable 선택한 row: " + row + ", col: " + col);
				System.out.println("선택한 날짜"+date);
				getUserInTable();
			}
		});
////////////////////////////////////////////////////////////////
		
		// 오른쪽 테이블 리스너
		dayTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = dayTable.getSelectedRow();

				// table_no = (Integer) dayTable.getValueAt(row, 1);
				// member_id = (Integer) dayTable.getValueAt(row, 2);

				// 임시로 이거 나중에 지워야함
				member_id = 1;
			}
		});
	}
	//상세보기 메서드
	public void showDetail() {
		new ShowDetail(this, table_no, member_id);

	}
	
///////////////////////////수정//////////////////////////////////
	// p_west 테이블 보여주는 메서드
	public void showAllMonth() {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sb = new StringBuffer();
		sb.append("select pay_list_date, sum(menu_list_price*order_detail_count) as sum");
		sb.append(" from pay_list p, order_summary os, order_detail od, menu_list m");
		sb.append(
				" where pay_list_date like '19/%' and p.order_summary_id=os.order_summary_id and os.order_summary_id=od.order_summary_id and");
		sb.append(" od.menu_list_id=m.menu_list_id group by pay_list_date order by pay_list_date asc");

		System.out.println(sb.toString());
		
		try {
			pstmt = con.prepareStatement(sb.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();
			Object[][] data = new Object[total][monthTableModel.columnTitle.length];
			rs.beforeFirst();
			for (int i = 0; i < total; i++) {
				rs.next();
				data[i][0] = i + 1;
				data[i][1] = rs.getString("pay_list_date"); // 날짜
				data[i][2] = rs.getInt("sum"); // 그날 하루 총 매출액

			}
			monthTableModel.data = data;
			monthTable.setModel(monthTableModel);
			monthTable.updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
	}
///////////////////////////수정//////////////////////////////////
	// p_west 특정 날짜 매출 검색하는 메서드
	public void showMonth() {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sb = new StringBuffer();
		sb.append("select pay_list_date, sum(menu_list_price*order_detail_count) as sum");
		sb.append(" from pay_list p, order_summary os, order_detail od, menu_list m");
		sb.append(
				" where pay_list_date like ? and p.order_summary_id=os.order_summary_id and os.order_summary_id=od.order_summary_id and");
		sb.append(" od.menu_list_id=m.menu_list_id group by pay_list_date order by pay_list_date asc");

		// 관리자가 조회하고 싶은 날짜 구해오기
		String date = t_date.getText();

		try {
			pstmt = con.prepareStatement(sb.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, date);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();
			Object[][] data = new Object[total][monthTableModel.columnTitle.length];
			rs.beforeFirst();
			for (int i = 0; i < total; i++) {
				rs.next();
				data[i][0] = i + 1;
				data[i][1] = rs.getString("pay_list_date"); // 날짜
				data[i][2] = rs.getInt("sum"); // 그날 하루 총 매출액

			}

			monthTableModel.data = data;
			monthTable.setModel(monthTableModel);
			monthTable.updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
	}
		public void getDayTable() { //사용 안함..
			Connection con = main.getCon();
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			StringBuffer sb = new StringBuffer();
			sb.append("select *");
			sb.append(" from member_list order by member_list_id asc");

			System.out.println(sb.toString());

			try {
				pstmt = con.prepareStatement(sb.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = pstmt.executeQuery();
				rs.last();
				int total = rs.getRow();
				Object[][] data = new Object[total][dayTableModel.columnTitle.length];
				rs.beforeFirst();
				for (int i = 0; i < total; i++) {
					rs.next();
					data[i][0] = i + 1;
					data[i][1] = rs.getString(""); // 테이블 번호
					data[i][2] = rs.getString(""); // 회원id
					data[i][3] = rs.getString(""); // 결제한 시간
					data[i][4] = rs.getString(""); // 금액
				}
				dayTableModel.data = data;
				dayTable.setModel(dayTableModel);
				dayTable.updateUI();

				// date = (String) dayTable.getValueAt(row, col);
				// System.out.println("date 값은: " + date);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {}
		}
///////////////////////////수정//////////////////////////////////
		public void getUserInTable() {
			Connection con=main.getCon();
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String userName=null;
			
			StringBuffer sb=new StringBuffer();
			sb.append("select table_no, u.user_list_id as user_list_id, u.user_type_id as user_type_id, u.user_id as user_id, pay_list_date , sum(menu_list_price*order_detail_count)");
			sb.append(" from table_info t, entry_list e, user_list u, order_summary os, ( select od.order_summary_id,od.order_detail_id, m.menu_list_id, menu_list_price ,order_detail_count");
			sb.append(" from order_summary os , order_detail od , menu_list m where os.order_summary_id = od.order_summary_id and od.menu_list_id = m.menu_list_id) od, pay_list p ");
			sb.append(" where p.pay_list_date = ? and t.table_info_id=e.table_info_id and u.user_list_id=e.user_list_id and e.entry_list_id=os.entry_list_id and os.order_summary_id=p.order_summary_id and os.order_summary_id=od.order_summary_id");
			sb.append(" group by  table_no,u.user_list_id,u.user_type_id,u.user_id,pay_list_date");
			
			System.out.println(sb.toString());
			
			try {
				pstmt = con.prepareStatement(sb.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				pstmt.setString(1, date);
				rs = pstmt.executeQuery();
				rs.last();
				int total = rs.getRow();
				Object[][] data = new Object[total][dayTableModel.columnTitle.length];
				rs.beforeFirst();
				for (int i = 0; i < total; i++) {
					rs.next();
					
					if(rs.getInt("user_type_id")==1) {
						userName=getGuestInTable(rs.getInt("user_id"));
					}else {
						userName=getMemberInTable(rs.getInt("user_id"));
					}
					
					data[i][0] = i + 1;
					data[i][1] = rs.getInt("table_no"); // 테이블 번호
					data[i][2] = userName; // 회원id
					data[i][3] = rs.getString("pay_list_date"); // 결제한 시간
					data[i][4] = rs.getString("sum(menu_list_price*order_detail_count)"); // 총결제금액
				}
				dayTableModel.data = data;
				dayTable.setModel(dayTableModel);
				dayTable.updateUI();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {}
		}
///////////////////////////수정//////////////////////////////////
		public String getMemberInTable(int member_list_id) {
			Connection con=main.getCon();
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String phone=null;
			
			String sql = "select member_list_phone from member_list where member_list_id=?";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, member_list_id);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getString("member_list_phone"));
					phone=rs.getString("member_list_phone");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				
			}
			return phone;
		}
///////////////////////////수정//////////////////////////////////
		public String getGuestInTable(int guest_list_id) {
			Connection con=main.getCon();
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String name=null;
			
			String sql = "select guest_list_name from guest_list where guest_list_id=?";
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, guest_list_id);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					System.out.println("guest "+rs.getString("guest_list_name"));
					name=rs.getString("guest_list_name");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {}
			return name;
		}
/////////////////////////////////////////////////////////////////
}
