package com.hunter.user;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {
	Socket socket;
	int port = 7777;
	MessageThread messageThread;
	String getSql;
	Dispatcher dispatcher;
	UserMain userMain;
	public ClientMain(UserMain userMain) {
		this.userMain = userMain;
		dispatcher = new Dispatcher(userMain, this);
		connect();
	}

	public void connect() {
		String ip = "192.168.0.29"; // #######나중에 관리자용 컴퓨터 ip 받아오기( 선생님 컴퓨터 )
		try {
			socket = new Socket(ip, port);
			messageThread = new MessageThread(this, socket);
			messageThread.start();
			System.out.println("client 연결 성공!");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendOrder(String requestType, String table_no, String menu_list_id, String menu_list_name,
		String order_detail_count,String entry_list_id) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"table_no\":\"" + table_no + "\",");
		sb.append("   \"menu_list_id\":\"" + menu_list_id + "\",");
		sb.append("   \"menu_list_name\":\"" + menu_list_name + "\",");
		sb.append("   \"order_detail_count\":\"" + order_detail_count + "\",");
		sb.append("   \"entry_list_id\":\"" + entry_list_id + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
	}

	public void sendEntry(String requestType, String user_list_id,
		String gender_type_id, String entry_list_total) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"user_list_id\":\"" + user_list_id + "\",");
		sb.append("   \"gender_type_id\":\"" + gender_type_id + "\",");
		sb.append(" \"entry_list_total\":\"" + entry_list_total + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
	}
	
	public void sendCheckId(String requestType,String table_ip,String member_list_phone) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"table_ip\":\"" + table_ip + "\",");
		sb.append("   \"member_list_phone\":\"" + member_list_phone + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
	}
	
	public void sendJoin(String requestType,String member_list_phone,String member_list_pw,String user_type_id) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"member_list_phone\":\"" + member_list_phone + "\",");
		sb.append("   \"member_list_pw\":\"" + member_list_pw + "\",");
		sb.append("   \"user_type_id\":\"" + user_type_id + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
	}
	
	public void sendLogin(String requestType,String table_ip,String member_list_phone,String member_list_pw) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"table_ip\":\"" + table_ip + "\",");
		sb.append("   \"member_list_phone\":\"" + member_list_phone + "\",");
		sb.append("   \"member_list_pw\":\"" + member_list_pw + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
	}
	
	public void sendGuest(String requestType,String user_type_id,String guest_list_name) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"user_type_id\":\"" + user_type_id + "\",");
		sb.append("   \"guest_list_name\":\"" + guest_list_name + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
	}
	
	public void sendOrderSummary(String requestType,String entry_list_id) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"entry_list_id\":\"" + entry_list_id + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
		
	}
	
	public void sendBill(String requestType,String table_ip,String entry_list_id) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"table_ip\":\"" + table_ip + "\",");
		sb.append("   \"entry_list_id\":\"" + entry_list_id + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
	}
	
	public void sendPhone(String requestType,String phone) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"phone\":\"" + phone + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
	}
}