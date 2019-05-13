package com.hunter.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MessageThread extends Thread{
	ClientMain clientMain;
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	
	boolean flag = true;
	
	public MessageThread(ClientMain clientMain, Socket socket) {
		this.clientMain = clientMain;
		this.socket = socket;
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listen() {
		String msg = null;
		try {
			msg = buffr.readLine();
			clientMain.dispatcher.dispatch(msg);
			//채팅방.area.append(msg+"\n"); #####채팅?
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
