package com.hunter.user.chat.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.text.BadLocationException;

public class ClientThread extends Thread{
	Socket client;//대화용 소켓
	BufferedReader buffr = null;
	BufferedWriter buffw = null;
	ChatClient chatClient;
	public ClientThread(ChatClient chatClient,Socket client) {
		this.chatClient = chatClient;
		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//서버의 메시지 보내기 기능
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//서버의 메시지 받기 :: 입력스트림
	public void listen() {
		String msg = null;
		try {
			msg = buffr.readLine();
			//chatClient.area.append(msg+"\n");
			displayMSG(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//메시지를 기성 메신저 스타일로 배치해 출력할 메서드
	public void displayMSG(String msg) {
		/*try {
			if() {//상대방이 쓴 msg면				
				chatClient.doc.insertString(chatClient.doc.getLength(), msg, chatClient.left);
				chatClient.doc.i;
				
				chatClient.doc.setParagraphAttributes(chatClient.doc.getLength(), 1,chatClient.left, false);
			}else {
				chatClient.doc.insertString(chatClient.doc.getLength(), msg, chatClient.right);
				chatClient.doc.setParagraphAttributes(chatClient.doc.getLength(), 1, chatClient.right, false);				
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}*/
		
	}
	
	public void run() {
		while(true) {
			listen();
		}
	}
}
