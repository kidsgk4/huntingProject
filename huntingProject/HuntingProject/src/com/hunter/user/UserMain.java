package com.hunter.user;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.hunter.db.ConnectionManager;
import com.hunter.user.home.HomeMain;

import com.hunter.user.join.JoinMain;
import com.hunter.user.login.LoginMain;
import com.hunter.user.regist.RegistMain;

public class UserMain extends JFrame {
	String TAG = this.getClass().getName();
	User user;

	HomeMain homeMain;
	LoginMain loginMain;
	JoinMain joinMain;
	RegistMain registMain;

	String[] mainTitle = { "init", "homeMain", "loginMain", "joinMain", "registMain" };
	JPanel[] pages = new JPanel[mainTitle.length];

	JPanel container;

	JPanel p_guest, p_login, p_join;
	JLabel la_guest, la_login, la_join;

	private Connection con;
	ConnectionManager connectionManager;
	ClientMain clientMain;

	public UserMain() {
		connectionManager = new ConnectionManager();
		con = connectionManager.connect();
		clientMain = new ClientMain(this);
		System.out.println(TAG+" userMain이 메모리에 올라갔다. ");
		user = new User();
		// 메모리에 올리기

		container = new JPanel();
		p_guest = new JPanel();
		p_guest.setBounds(162, 300, 350, 350);
		p_login = new JPanel();
		p_login.setBounds(517, 300, 350, 350);
		p_join = new JPanel();
		p_join.setBounds(872, 300, 350, 350);
		la_guest = new JLabel("GUEST");
		la_login = new JLabel("LOGIN");
		la_join = new JLabel("JOIN");

		container.setPreferredSize(new Dimension(1300, 400));
		// this.setBorder(BorderFactory.createEmptyBorder(300, 0, 0, 0));
		Dimension d = new Dimension(350, 350);
		p_guest.setPreferredSize(d);
		p_login.setPreferredSize(d);
		p_join.setPreferredSize(d);
		// 글자 사이즈 조정
		Dimension f = new Dimension(200, 350);
		la_guest.setPreferredSize(f);
		la_guest.setHorizontalAlignment(SwingConstants.CENTER);
		la_login.setPreferredSize(f);
		la_login.setHorizontalAlignment(SwingConstants.CENTER);
		la_join.setPreferredSize(f);
		la_join.setHorizontalAlignment(SwingConstants.CENTER);

		// 서식 조정
		la_guest.setFont(new Font("Verdana", Font.BOLD, 50));
		la_guest.setForeground(Color.WHITE);
		la_login.setFont(new Font("Verdana", Font.BOLD, 50));
		la_login.setForeground(Color.WHITE);
		la_join.setFont(new Font("Verdana", Font.BOLD, 50));
		la_join.setForeground(Color.WHITE);

		// 패널 배경색
		container.setBackground(Color.black);
		p_guest.setBackground(Color.blue);
		p_login.setBackground(Color.GREEN);
		p_join.setBackground(Color.CYAN);

		// 글자부>패널>컨테이너 패널 부착

		p_guest.add(la_guest);
		p_login.add(la_login);
		p_join.add(la_join);
		container.setLayout(null);

		container.add(p_guest);
		container.add(p_login);
		container.add(p_join);

		getContentPane().add(container);

		setBackground(Color.black);

		// 윈도우 리스너 구현하기!!
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				connectionManager.closeDB(con);
				System.exit(0);
			}
		});

		// p_guest 리스너 연결
		p_guest.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				user.getGuest().addGuest();
				openRegist();
			}

			public void mouseEntered(MouseEvent e) {
				p_guest.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				p_guest.setBackground(Color.BLUE);
			}
		});
		// p_login와 리스너 연결
		p_login.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				user.getMemeber().addMember();
				openLogin();
			}

			public void mouseEntered(MouseEvent e) {
				p_login.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				p_login.setBackground(Color.GREEN);
			}
		});
		// p_join과 리스너 연결
		p_join.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				openJoin();
			}

			public void mouseEntered(MouseEvent e) {
				p_join.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				p_join.setBackground(Color.CYAN);
			}
		});

		setSize(1400, 1000);
		setLocationRelativeTo(null);
		setVisible(true);// 창띄우기
	}

	public Connection getCon() {
		return con;
	}

	public void openRegist() {
		registMain = new RegistMain(this, user);
	}

	public void openLogin() {
		loginMain = new LoginMain(this, user);
	}

	public void openJoin() {
		joinMain = new JoinMain(this, user);
	}

	// order 관련 데이터보내기
	public void getSendData(String requestType, String table_no, String menu_list_id, String menu_list_name,
			String order_detail_count,String entry_list_id) {
		clientMain.sendOrder(requestType, table_no, menu_list_id, menu_list_name, order_detail_count,entry_list_id);
		System.out.println("유저메인" + menu_list_id);
	}

	// 입장 정보 데이터 보내기
	public void getEntryData(String requestType, String user_list_id, String gender_type_id, String entry_list_total) {
		clientMain.sendEntry(requestType, user_list_id, gender_type_id, entry_list_total);
	}

	// ID 중복확인
	public void getCheckId(String requestType, String table_ip, String member_list_phone) {
		clientMain.sendCheckId(requestType, table_ip, member_list_phone);
	}

	// 회원가입
	public void getJoin(String requestType, String member_list_phone, String member_list_pw, String user_type_id) {
		clientMain.sendJoin(requestType, member_list_phone, member_list_pw, user_type_id);
	}

	// 로그인
	public void getLogin(String requestType, String table_ip, String member_list_phone, String member_list_pw) {
		clientMain.sendLogin(requestType, table_ip, member_list_phone, member_list_pw);
	}

	// Guest
	public void getInsertGuest(String requestType, String user_type_id,String guest_list_name) {
		clientMain.sendGuest(requestType, user_type_id,guest_list_name);
	}

	// sendordersummary
	public void getOrderSummary(String requestType, String entry_list_id) {
		clientMain.sendOrderSummary(requestType, entry_list_id);
	}
	
	public void getBill(String requestType, String table_ip,String entry_list_id){
		clientMain.sendBill(requestType,table_ip,entry_list_id);
	}
	
	public void getMemberId(String requestType,String phone) {
		clientMain.sendPhone(requestType,phone);
	}

	public static void main(String[] args) {
		new UserMain();
	}

}