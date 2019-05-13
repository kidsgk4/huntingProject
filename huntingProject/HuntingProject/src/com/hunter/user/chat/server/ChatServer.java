package com.hunter.user.chat.server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatServer{
	ServerSocket server;//접속자 감지용 소켓
	Thread serverThread;//메인스레드가 대기상태에 빠지지 않게 하는 스레스
	int port = 9876;
	//서버의 대화용 스레드들을 담을 객체
	Vector<ServerThread> list = new Vector();
	
	public ChatServer() {
		serverThread = new Thread() {
			public void run() {
				runServer();
			}
		};
		//이번 프로젝트에서는 bt같은 트리거가 없어도 시작되어있어야함
		serverThread.start();
	}
	
	//서버연결 메서드
	public void runServer() {
		try {
			server = new ServerSocket(port);//서버 생성
			
			while(true) {
				Socket client = server.accept();//서버가동::접속자 기다림
				String ip = client.getInetAddress().getHostAddress();
				//접속과 동시에 대화를 나눌 Thread아바타 새성
				ServerThread st = new ServerThread(this,client);
				st.start();
				
				list.add(st);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
