package com.hunter.user.regist;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.hunter.user.ClientMain;
import com.hunter.user.User;
import com.hunter.user.UserMain;
import com.hunter.user.chat.ChadanList;
import com.hunter.user.home.HomeMain;

public class RegistMain extends JFrame {
	String TAG = this.getClass().getName();//디버깅 꼬리표
	UserMain userMain;
	User user;
	HomeMain homeMain;
	JPanel p_north, p_center;
	JLabel la_title, la_gender, la_num;
	Choice ch;
	JTextField t_num;
	JButton bt_enter;
	JButton bt_cancel;

	// 정보
	String tableIp;
	String guestName;
	int tableIndex = 0;
	int userId; // 유저의 pk값 (게스트거나, 멤버)
	int gender_type_id;// 선택한 성별 타입의 인덱스
	int total;// 총 인원수
	int guest_list_id;
	public RegistMain(UserMain userMain, User user) {
		System.out.println(TAG+" regtMain이 메모리에 올라갔댜!!");
		this.userMain = userMain;
		this.user = user;

		// 메모리에 올리기
		p_north = new JPanel();
		p_center = new JPanel();
		
		la_title = new JLabel("REGIST");
		la_title.setHorizontalAlignment(SwingConstants.CENTER);
		la_gender = new JLabel("성 별");
		la_num = new JLabel("인원 수");
		la_num.setBounds(39, 60, 200, 50);
		ch = new Choice();
		ch.setBounds(244, 15, 200, 30);
		t_num = new JTextField();
		t_num.setHorizontalAlignment(SwingConstants.CENTER);
		t_num.setBounds(244, 60, 200, 50);
		bt_enter = new JButton("입장");
		bt_cancel = new JButton("취소"); 

		// 사이즈 조정하기
		Dimension d = new Dimension(200, 50);
		la_title.setPreferredSize(new Dimension(200, 100));
		la_gender.setPreferredSize(d);
		la_num.setPreferredSize(d);
		ch.setPreferredSize(d);
		t_num.setPreferredSize(d);

		bt_enter.setPreferredSize(new Dimension(100, 30));
		bt_cancel.setPreferredSize(new Dimension(100, 30));

		la_title.setFont(new Font("돋움", Font.BOLD, 50));
		la_gender.setFont(new Font("돋움", Font.BOLD, 20));
		ch.setFont(new Font("돋움", Font.BOLD, 20));
		la_num.setFont(new Font("돋움", Font.BOLD, 20));
		bt_enter.setFont(new Font("돋움", Font.BOLD, 20));
		bt_cancel.setFont(new Font("돋움", Font.BOLD, 20));

		// 부착하기
		ch.addItem("====성별====");
		ch.addItem("남성");
		ch.addItem("여성");
		ch.addItem("혼성");
		p_north.add(la_title);
		p_center.add(la_gender);
		p_center.add(ch);
		p_center.add(la_num);
		p_center.add(t_num);
		

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(p_north, BorderLayout.NORTH);
		getContentPane().add(p_center);
		bt_enter = new JButton("입장");
		bt_enter.setBounds(130, 140, 100, 30);
		p_center.add(bt_enter);

		bt_enter.setPreferredSize(new Dimension(100, 30));
		bt_enter.setFont(new Font("돋움", Font.BOLD, 20));
		bt_cancel = new JButton("취소");
		bt_cancel.setBounds(260, 140, 100, 30);
		p_center.add(bt_cancel);
		bt_cancel.setPreferredSize(new Dimension(100, 30));
		bt_cancel.setFont(new Font("돋움", Font.BOLD, 20));

		ch.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//gender_type_name = ch.getSelectedItem();
				//System.out.println("선택한 초이스의 이름은: " + gender_type_name);
				gender_type_id = ch.getSelectedIndex();
				System.out.println(TAG+" 선택 성별 인덱스 : "+gender_type_id);
			}
		});
		bt_enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enter();
				//userMain.getHomeMain().setUserInfo();
			}
		});
		bt_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeRegist();
			}
		});

		setSize(500, 300);
		setLocationRelativeTo(null);
		setVisible(true);// 창띄우기
	}
	
	public void closeRegist() {
		setVisible(false);
	}

	public void enter() {
		// db에 로그인한 정보(테이블번호, 유저아이디,성별,총인원수,입장일) 넘기기
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;

		// 입장정보 클래스에 등록, 화면전환
		int result = JOptionPane.showConfirmDialog(this, "입장하시겠습니까?");
		if (result == JOptionPane.OK_OPTION) {
			total = (Integer.parseInt(t_num.getText()));// 작성한 총 인원수

			user.setGender(gender_type_id);// 성별 저장
			System.out.println("등록창에서 등록할 성별의 인덱스는: " + gender_type_id);
			//System.out.println("등록창에서 등록할 성별의 이름은: " + gender_type_name);
			
			user.setTotal(total);// 총 인원수 저장
			System.out.println("등록창에서 등록할 총 인원수는 : " + total);
			
			setVisible(false);
			userMain.setVisible(false);
			
			save();
			// 홈 화면 생성
			homeMain = new HomeMain(userMain, user);
			
		}
	}

	// db에 entry_list에 등록하는 메서드
	// db에 로그인한 정보(테이블번호, 유저아이디,성별,총인원수,입장일) 넘기기
	// db에 entry_list에 저장(회원이든, 비회원이든 등록하는 이곳에서 db에 entry_list에 추가시킨다.)
	public void save() {
		int table = user.getTableNum(); // 테이블 값 구하기
		if (user.getFlag()) { // 멤버유저pk 구하기
			userId = user.getMemeber().getMemberId();
		} else {// 게스트 유저pk 구하기
			userId = user.getGuest().getGuestId();
			guestName = "guest" + System.currentTimeMillis();
			userMain.getInsertGuest("guest","1",guestName);
		}
		userMain.getEntryData("entry",Integer.toString(userId), Integer.toString(gender_type_id), Integer.toString(total));
		addUserList();
	}
	public void addUserList() {
		
		System.out.println(guest_list_id);
	}
}