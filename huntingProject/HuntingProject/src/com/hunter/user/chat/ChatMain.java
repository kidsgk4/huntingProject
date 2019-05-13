package com.hunter.user.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.hunter.ui.map.TableMap;
import com.hunter.user.User;
import com.hunter.user.UserMain;


public class ChatMain extends JPanel{
	String TAG=this.getClass().getName();
	UserMain userMain;
	User user;
	
	JPanel p_west;//테이블맵,채팅현황 나올 패널
	JPanel p_west_north;//west중 테이블맵 패널
	JPanel p_west_center;//west중 채팅현황 패널
		
	JPanel p_center;
	JPanel p_center_center;//my chat list놓일 패널
	JPanel p_center_south;//btns놓일 패널
	
	//TableMap
	TableMap tableMap;//끌어올 테이블맵
	//current chat list
	CurrentChatList currentChatList;
	
	//my chat list
	MyChatList myChatList;
	
	//p_center_center에 놓일 버튼들
	JButton bt_chat_enter;//선택 대화방 입장
	JButton bt_chat_create;//대화방 만들기
	JButton bt_exit;//대화방 나가기(홈으로)
		
	public ChatMain(UserMain userMain,User user) {
		System.out.println(TAG+" chatMAin이 메모리에 올라갔다.");
		this.userMain=userMain;
		this.user = user;
		
		setLayout(new BorderLayout());
		
		//메모리 올리기
		p_west = new JPanel();
		//p_west_north = new JPanel();
		//p_west_north에 tableMap을 불러옴
		p_west_north = new TableMap(this,this.userMain,this.user);
		//tableMap = new TableMap(this,this.userMain);
		//p_west_center에 currentChatList를 불러옴
		currentChatList = new CurrentChatList(this,this.userMain);
		p_west_center = new JPanel();
		
		p_center = new JPanel();
		p_center_center = new JPanel();
		//p_center_center에 myChatList불러오기
		myChatList = new MyChatList(this, this.userMain);
		p_center_south = new JPanel();
		bt_chat_enter = new JButton("선택 대화방 입장");
		bt_chat_create = new JButton("대화방 생성");
		bt_exit = new JButton("나가기");

		//레이아웃,크기,색상
		p_west.setLayout(new BorderLayout(0,0));
		p_west.setPreferredSize(new Dimension(700, 900));

		p_west_north.setLayout(new FlowLayout(HEIGHT, 0, 0));
		p_west_north.setPreferredSize(new Dimension(700,450));
		p_west_north.setBackground(Color.GRAY);
		p_west_center.setPreferredSize(new Dimension(700,450));
		p_west_center.setBackground(Color.PINK);
		
		p_center.setLayout(new BorderLayout());
		p_center.setPreferredSize(new Dimension(700, 900));

		p_center_center.setPreferredSize(new Dimension(700,750));
		p_center_south.setLayout(new FlowLayout(WIDTH,0,0));
		p_center_south.setPreferredSize(new Dimension(700, 150));
		p_center_south.setBackground(Color.BLACK);
		
		Dimension d_bt = new Dimension(180, 80);
		bt_chat_enter.setPreferredSize(d_bt);
		bt_chat_create.setPreferredSize(d_bt);
		bt_exit.setPreferredSize(d_bt);
		bt_chat_enter.setBackground(Color.ORANGE);
		bt_chat_create.setBackground(Color.ORANGE);
		bt_exit.setBackground(Color.ORANGE);
		bt_chat_enter.setForeground(new Color(34,61,75));
		bt_chat_create.setForeground(new Color(34,61,75));
		bt_exit.setForeground(new Color(34,61,75));
		
		//부착
		//p_west_north.add(tableMap);
		p_west_center.add(currentChatList);
		p_west.add(p_west_north,BorderLayout.NORTH);
		p_west.add(p_west_center,BorderLayout.CENTER);
		add(p_west,BorderLayout.WEST);
		
		p_center_center.add(myChatList);
		p_center.add(p_center_center,BorderLayout.CENTER);
		
		p_center_south.add(bt_chat_enter);
		p_center_south.add(bt_chat_create);
		p_center_south.add(bt_exit);
		p_center.add(p_center_center);
		p_center.add(p_center_south,BorderLayout.SOUTH);
		add(p_center);
				
		setBackground(Color.RED);
		setPreferredSize(new Dimension(1400, 900));
		setVisible(true);
		System.out.println(TAG+" 의 유저 tablenum는 "+user.getTableNum()+","+user);//user.getTableNum()에서 받아오지 못함
			//즉, ChatMain이 User보다 탄생시점이 빠른듯
	}
}
