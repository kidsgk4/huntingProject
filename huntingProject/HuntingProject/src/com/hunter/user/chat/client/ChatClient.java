package com.hunter.user.chat.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.hunter.user.chat.hapseok.AskJoin;

public class ChatClient extends JPanel{
	JPanel p_center;//chatting zone
	JPanel p_center_north;
	JPanel p_center_center;
	JPanel p_center_south;
	
	JLabel la_title;
	JTextPane area;//채팅 메시지가 뿌려질 곳
	StyledDocument doc = area.getStyledDocument();
	SimpleAttributeSet left;
	SimpleAttributeSet right;
	JScrollPane scroll;
	
	JTextArea ta_insertText;
	JButton bt_emoticon;
	JButton bt_insert;
	
	JPanel p_east;//btn zone
	JButton bt_exit;//나가기
	JButton bt_chadan;//차단
	JButton bt_joinTable;//합석신청
	
	//대화용 소켓
	Socket client;
	ClientThread ct;
	
	//합석신청관련
	AskJoin askJoin;
	
	public ChatClient(AskJoin askJoin) {
		this.askJoin = askJoin;
		setLayout(new BorderLayout());
		p_center = new JPanel();
		p_center_north = new JPanel();
		p_center_center = new JPanel();
		p_center_south = new JPanel();
		
		la_title = new JLabel("untitled");
		area = new JTextPane();
		scroll = new JScrollPane(area);
		left = new SimpleAttributeSet();
		right = new SimpleAttributeSet();
		
		ta_insertText = new JTextArea();
		bt_emoticon = new JButton("이모티콘");
		bt_insert = new JButton("입력");
		
		p_east = new JPanel();
		bt_exit = new JButton("나가기");
		bt_chadan = new JButton("상대차단");
		bt_joinTable = new JButton("합석신청");
		
		//레이아웃등 세팅
		p_center.setLayout(new BorderLayout(0, 0));
		//메신저 스타일
		StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
		StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);

		//부착
		p_center_north.add(la_title);
		p_center.add(p_center_north,BorderLayout.NORTH);
		p_center_center.add(scroll);
		p_center.add(p_center_center);
		p_center_south.add(ta_insertText);
		p_center_south.add(bt_emoticon);
		p_center_south.add(bt_insert);
		p_center.add(p_center_south,BorderLayout.SOUTH);
		this.add(p_center, BorderLayout.CENTER);
		
		p_east.add(bt_exit);
		p_east.add(bt_chadan);
		p_east.add(bt_joinTable);
		this.add(p_east, BorderLayout.EAST);
		
		ta_insertText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent k) {
				int key = k.getKeyCode();
				if(key==KeyEvent.VK_ENTER) {
					ct.send(ta_insertText.getText());
				}
			}
		});
		bt_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeChat();
			}
		});
		bt_chadan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				blockThisTable();
			}
		});
		bt_joinTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				proposeJoin();
			}
		});
		setVisible(true);
	}
	
	//소켓 생성 시 접속 메서드
	public void connect() {
		try {
			client = new Socket();
			ct = new ClientThread(this, client);
			ct.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//채팅 종료, 창 닫기 메서드
	public void closeChat() {
		//대화내용이 있다면 닫기전까지 대화 db에 저장
		//clientThread 종료(자원회수)
		//창 닫아야하고--> visible false로
	}
	
	//차단 메서드
	public void blockThisTable() {}
	
	//합석신청 메서드
	public void proposeJoin() {
		this.askJoin = new AskJoin(this);
		/*
		String[] options= {"네, 신청할래요","아니요, 취소할래요"};
		int ask = JOptionPane.showOptionDialog(this, "합석을 신청하시겠습니까?", "합석신청", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		switch(ask) {
		case JOptionPane.OK_OPTION:
			JOptionPane.showMessageDialog(this, "상대방의 의사를 묻는중입니다.");
			//상대방에게 수락하시겠습니까?를 띄워야함
			break;
		case JOptionPane.CANCEL_OPTION:
			JOptionPane.showMessageDialog(this, "신청을 취소하셨습니다.");
			break;
		}
		*/
	}
}
