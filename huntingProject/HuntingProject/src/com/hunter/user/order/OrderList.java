package com.hunter.user.order;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.hunter.user.UserMain;

public class OrderList extends JPanel {
	UserMain main;
	JPanel p_tab; // 버튼3개 부착 탭
	JPanel p_center; // 음식 리스트들 나올 패널
	JButton bt_food; // 안주류
	JButton bt_drink; // 주류
	JButton bt_side; // 사이드류
	JPanel[] pages = new JPanel[3];

	List cartList = new ArrayList();
	Bag bag;
	int cart_id;
	int table_no;
	public OrderList(UserMain main, Bag bag) {
		// 생성
		this.main = main;
		this.bag = bag;
		this.table_no = bag.orderMain.table_no;
		p_tab = new JPanel();
		p_center = new JPanel();
		bt_food = new JButton("안주류");
		bt_food.setBounds(0, 0, 152, 50);
		bt_drink = new JButton("주류");
		bt_drink.setBounds(150, 0, 158, 50);
		bt_side = new JButton("사이드류");
		bt_side.setBounds(306, 0, 144, 50);

		// 안주 /주류 / 사이드류 페이지 배열에 담기
		pages[0] = new FoodPage(main, this, bag);
		pages[1] = new DrinkPage(main, this, bag);
		pages[2] = new SidePage(main, this, bag);
		p_tab.setLayout(null);

		// 탭 부분에 버튼 장착
		p_tab.add(bt_food);
		p_tab.add(bt_drink);
		p_tab.add(bt_side);

		// 본판에 페이지들 장착
		p_center.add(pages[0]);
		p_center.add(pages[1]);
		p_center.add(pages[2]);

		setLayout(new FlowLayout());

		// 각 버튼에 해당하는 페이지 메서드 호출
		bt_food.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPage(0);
			}
		});

		bt_drink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPage(1);
			}
		});

		bt_side.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPage(2);
			}
		});

		setLayout(new BorderLayout());
		p_tab.setPreferredSize(new Dimension(700, 50));
		p_center.setPreferredSize(new Dimension(600, 750));
		add(p_tab, BorderLayout.NORTH);
		add(p_center);
		//System.out.println("테스트 테이블"+table_no);
	}

	// p_center 화면 전환 메서드
	public void showPage(int page) {
		for (int i = 0; i < pages.length; i++) {
			if (i == page) {
				pages[i].setVisible(true);
			} else {
				pages[i].setVisible(false);
			}
		}
	}

	/*장바구니에 동일 메뉴가 있는지 조회*/
	public int dupulicateCheck(int table_no, int menu_list_id) {
		int cart_id=0;
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		StringBuffer sb = new StringBuffer();
		
		sb.append("select * from cart where menu_list_id=? and table_no=?"); //현재 로그인 한 사람에 대해서...나중에 table_info_id?로 바꿀지말지 결정##############
		try {
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, menu_list_id);
			pstmt.setInt(2, table_no); 
			rs= pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("이미 장바구니에 존재하네요");
				cart_id=rs.getInt("cart_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
		return cart_id;
	}
	
	/* 중복된 메뉴의 갯수 증가 */ 
	public int updateEa(int cart_id) { // table_info_id를 받아와야하는데...
		int result=0;
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();
		//이미 있는지 여부!!!	
		sb.append("update cart set ea=ea+1 where cart_id=?");
		try {
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1,cart_id);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	/* 장바구니에 넣기 */ 
	public void goCart(int table_no, int menu_list_id) { // table_info_id를 받아와야하는데...
		
		System.out.println("gocart 실행!!");
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();
		//이미 있는지 여부!!!	
		sb.append("insert into cart(cart_id,table_no,menu_list_id,ea)");
		sb.append(" values(seq_cart_id.nextval, ?, ?, 1)");
		
		try {
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, table_no);
			pstmt.setInt(2, menu_list_id);
			int result=pstmt.executeUpdate();
			System.out.println("gocart에서 받은 table_no는 "+table_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//return result;
	}
}














