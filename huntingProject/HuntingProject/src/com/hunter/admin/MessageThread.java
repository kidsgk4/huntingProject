package com.hunter.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MessageThread extends Thread{
	ServerMain serverMain;
	Socket client;
	BufferedReader buffr;
	BufferedWriter buffw;
	boolean flag = true;
	
	public MessageThread(ServerMain serverMain,Socket client) {
		this.serverMain = serverMain;
		this.client = client;
		
		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void listen() {
		try {
			String msg = buffr.readLine(); // client�� �� ���
			// ������ ������ ��� �ƹ�Ÿ�� write�� ȣ������!!
			for (int i = 0; i < serverMain.list.size(); i++) {
				serverMain.list.get(i).send(msg);// client�� ����!!
				//MessageThread st = serverMain.list.get(i);
				//st.send(msg);
			}
			serverMain.dispatcher.dispatch(msg);
		} catch (IOException e) {
			System.out.println("Ŭ���̾�Ʈ�� �������ϴ�.");
			flag = false;
			serverMain.list.remove(this);
			System.out.println(("���� "+serverMain.list.size()+"�� ������"));
			//e.printStackTrace();
		}
	}
	public void send(String msg) {
		try {
			buffw.write(msg + "\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(flag) {
			listen();
		}
	}
}
