package com.hunter.user.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.hunter.ui.map.TableMap;
import com.hunter.user.UserMain;
import com.hunter.user.chat.client.ChatClient;
//import com.hunter.user.chat.server.ChatServer;


public class TableOption extends JFrame{
	JPanel p_north;
	JLabel la_title;
	JPanel p_center;
	JButton bt_left;//타인 버튼 클릭 시 대화걸기(대화방 개설로 연결), 내 버튼 클릭 시 빈 대화방 개설
	JButton bt_right;//타인 버튼 클릭 시 차단하기, 내 버튼 클릭 시 bill check
	JPanel p_south;
	JButton bt_close;
	//페이지 얻기
	ChatMain chatMain;
	TableMap tableMap;
	//채팅용
	ChatClient chatClient;
	//ChatServer chatServer;
	
	String tableIp;
	
	public TableOption(ChatMain chatMain,TableMap tableMap,String tableIp,boolean sameTable) {
		this.chatMain = chatMain;
		this.tableMap = tableMap;
		this.tableIp = tableIp;
		
		
		setLayout(new BorderLayout(0,0));
		p_north = new JPanel();
		la_title = new JLabel();
		p_center = new JPanel();
		bt_left = new JButton("왼쪽버튼");
		bt_right = new JButton("오른쪽버튼");
		p_south = new JPanel();
		bt_close = new JButton("닫기");
		
		//크기 조정
		p_north.setPreferredSize(new Dimension(280, 40));
		la_title.setPreferredSize(new Dimension(280, 40));
		p_center.setPreferredSize(new Dimension(280, 100));
		bt_left.setPreferredSize(new Dimension(135, 100));
		bt_right.setPreferredSize(new Dimension(135, 100));
		p_south.setPreferredSize(new Dimension(280, 40));
		bt_close.setPreferredSize(new Dimension(270, 40));
		bt_close.setBorder(BorderFactory.createEmptyBorder(0, 135, 0, 0));
		
		//색상
		la_title.setBackground(Color.ORANGE);
		la_title.setForeground(new Color(34,61,75));
		bt_left.setBackground(new Color(27,188,155));
		bt_right.setBackground(new Color(27,188,155));
		bt_close.setBackground(Color.ORANGE);
		bt_left.setForeground(new Color(34,61,75));
		bt_right.setForeground(new Color(34,61,75));
		bt_close.setForeground(new Color(34,61,75));
		
		//부착 
		p_north.add(la_title);
		add(p_north,BorderLayout.NORTH);
		p_center.add(bt_left);
		p_center.add(bt_right);
		add(p_center);
		p_south.add(bt_close);
		add(p_south,BorderLayout.SOUTH);
		
		//리스너
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				TableOption.this.dispose();
			}
		});
		bt_left.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createChat(TableOption.this.tableIp);
			}
		});
		bt_close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableOption.this.dispose();
			}
		});
		setLaBt(sameTable);
		setBackground(new Color(30,144,255));
		setSize(new Dimension(300, 200));
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void setLaBt(boolean myTable) {
		if(myTable) {//내가 내 테이블 클릭했을 경우 :: 로그인한 아이디가 점유한 테이블 번호, 클릭당한 테이블번호가 같으면은 TableMap에서 판단해서 플래그를 넘겨줌
			la_title.setText("My Table 옵션");
			bt_left.setText("빈 대화방 개설");
			bt_right.setText("차단여부보기");
			bt_left.addActionListener((e)-> {
				createChat(TableOption.this.tableIp);					
			});
			bt_right.addActionListener((e)-> {
				showBlockList();
			});
		}else {//다른 테이블 클릭했을 경우
			la_title.setText("Selected Table 옵션");
			bt_left.setText("상대에게 대화걸기");
			bt_right.setText("차단하기");
			
			bt_left.addActionListener((e)->{
				createChat(TableOption.this.tableIp);
			});
			bt_right.addActionListener((e)->{
				blockUser();
			});
		}
	}
	
	//채팅화면 생성,호출 메서드
	public void createChat(String ip) {
		JOptionPane.showMessageDialog(chatMain,"채팅시작할거니?");
		//chatServer = new ChatServer();
		//chatClient = new ChatClient(ip);//내꺼
		//chatClient = new ChatClient(ip);//쟤꺼
		TableOption.this.dispose();
	}
	
	//차단하기
	public void blockUser() {//인수로 상대방 테이블 번호 받아야 함
		//테이블 번호와 매칭되는 ip를 차단목록에 넣는다.
		
		//ip를 가지고 iterator로 chadan 맵을 조회해서 일치여부 비교
		//결과가 true(있으면) 그냥 있고,
		//false(없으면) 목록에 추가
	}
	
	//차단목록보기
	public void showBlockList() {
		Map<String,String> blockList=null;
		//blockList = this.chadanList.getChadanList();//Map<String,String>타입의 chadan 리턴
		String key = null;
		String obj = null;
		//iterator를 사용하기 위해 set에 담음
		Set set = blockList.keySet();
		
		//iterator
		Iterator<String> it = set.iterator();//key만 모아 일렬로 늘어놓음
		while(it.hasNext()) {
			key = it.next();
			obj = blockList.get(key);
		}
		JOptionPane.showMessageDialog(chatMain,"차단한 목록은\n"+key+", "+obj+"입니다.");
	}
}
