package com.hunter.user.chat.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.text.BadLocationException;

public class ClientThread extends Thread{
	Socket client;//��ȭ�� ����
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
	
	//������ �޽��� ������ ���
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//������ �޽��� �ޱ� :: �Է½�Ʈ��
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
	
	//�޽����� �⼺ �޽��� ��Ÿ�Ϸ� ��ġ�� ����� �޼���
	public void displayMSG(String msg) {
		/*try {
			if() {//������ �� msg��				
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
