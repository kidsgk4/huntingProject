package com.hunter.admin;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class ServerMain {
	int port = 7777;
	Thread thread;
	ServerSocket server;
	Dispatcher dispatcher;
	Main main;
	Socket socket;
	MessageThread messageThread;
	int count = 0;
	String ip;
	Vector<MessageThread> list = new Vector();
	ArrayList ip_list = new ArrayList();

	public ServerMain(Main main) {
		this.main = main;
		System.out.println("admin 서버 생성!!");
		thread = new Thread() {
			@Override
			public void run() {
				runServer();
			}
		};
		dispatcher = new Dispatcher(main, this);
		thread.start();
	}

	public void runServer() {
		System.out.println();
		System.out.println("admin 서버 runserver!!");
		try {
			server = new ServerSocket(port); // 서버 생성
			while (true) {
				Socket client = server.accept(); // 서버 가동( 접속자를 기다림 )

				ip = client.getInetAddress().getHostAddress();
				System.out.println(ip + "님이 접속!!");
				MessageThread st = new MessageThread(this, client);
				st.start();
				list.add(st);
				ip_list.add(ip);
				sendIp("sendip",ip);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendIp(String requestType,String table_ip) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"table_ip\":\"" + table_ip + "\"");
		sb.append("}");
		// messageThread.send(sb.toString());

		for (int i = 0; i < list.size(); i++) {
			if (ip_list.get(i).equals(table_ip)) {
				list.get(i).send(sb.toString());
				System.out.println("sendIp에서 보내는 table_ip는"+table_ip);
			}
		}
	}

	public void sendData(String requestType, String table_info_id, String table_ip, String entry_list_id) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"table_info_id\":\"" + table_info_id + "\",");
		sb.append("   \"table_ip\":\"" + table_ip + "\",");
		sb.append("   \"entry_list_id\":\"" + entry_list_id + "\"");
		sb.append("}");
		// messageThread.send(sb.toString());

		for (int i = 0; i < list.size(); i++) {
			if (ip_list.get(i).equals(table_ip)) {
				list.get(i).send(sb.toString());
				System.out.println(
						table_ip + "에게 senddata 실행\n 보낼 info_id는" + table_info_id + "\n 보낼 table_ip는 " + table_ip);
			}
		}
	}

	public void sendCheckIdBack(String requestType, String table_ip, String idCheck) {
		StringBuffer sb = new StringBuffer();
		//table_ip = ip;
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"table_ip\":\"" + table_ip + "\",");
		sb.append("   \"idCheck\":\"" + idCheck + "\"");
		sb.append("}");
		// messageThread.send(sb.toString());
		System.out.println("앞에서하는 체크결과는" + idCheck);
		System.out.println("앞에서하는 table_ip" + table_ip);

		for (int i = 0; i < list.size(); i++) {
			System.out.println("ip_list에 들어가있는 ip" + ip_list.get(i));
			if (ip_list.get(i).equals(table_ip)) {
				list.get(i).send(sb.toString());
				System.out.println("체크 결과는" + idCheck);
				// System.out.println(table_ip+"에게 senddata 실행\n 보낼 info_id는"+table_info_id+"\n
				// 보낼 table_ip는 "+table_ip);
			}
		}
	}
	public void sendLoginBack(String requestType, String table_ip, String check) {
		StringBuffer sb = new StringBuffer();
		table_ip = ip;
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"table_ip\":\"" + table_ip + "\",");
		sb.append("   \"check\":\"" + check + "\"");
		sb.append("}");
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println("ip_list에 들어가있는 ip" + ip_list.get(i));
			if (ip_list.get(i).equals(table_ip)) {
				list.get(i).send(sb.toString());
				System.out.println("체크 결과는" + check);
			}
		}
	}
	
	//다시 order_summary_id를 보내줘야지...왜? entry_list에 해당하는 order_summary_id를 다시 클라이언트에서 order_detail로 보내줘야 하니까...
	//그런데 ip로 수신자를 지정하면...문제점이 생기지 않을까.......
	//또 entry_list에 해당하는 order_summary_Id가 복수형으로 나올 수 있는데...그럼 어떻게보내지..반복문 rs.next로 계속 send를 하고 client에서 이걸 수신할때 반복문??
	public void sendOrderSummaryBack(String requestType, String order_summary_id) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"order_summary_id\":\"" + order_summary_id + "\"");
		sb.append("}");
		
		for (int i = 0; i < list.size(); i++) {
			String table_ip = ip;
			System.out.println("ip_list에 들어가있는 ip" + ip_list.get(i));
			if (ip_list.get(i).equals(table_ip)) {
				list.get(i).send(sb.toString());
			}
		}
	}
	public void sendBillData(String requestType,String menu_list_name,String menu_list_price,String order_detail_count,String row) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"menu_list_name\":\"" + menu_list_name + "\",");
		sb.append("   \"menu_list_price\":\"" + menu_list_price + "\",");
		sb.append("   \"order_detail_count\":\"" + order_detail_count + "\",");
		sb.append("   \"row\":\"" + row + "\"");
		sb.append("}");
		
		for (int i = 0; i < list.size(); i++) {
			String table_ip = ip;
			if (ip_list.get(i).equals(table_ip)) {
				list.get(i).send(sb.toString());
			}
		}
	}
	
	public void sendMemberId(String requestType,String memberId) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"memberId\":\"" + memberId + "\"");
		sb.append("}");
	
		for (int i = 0; i < list.size(); i++) {
			String table_ip = ip;
			if (ip_list.get(i).equals(table_ip)) {
				list.get(i).send(sb.toString());
			}
		}
	}
	public void sendGuestListIdBack(String requestType, String guest_list_id) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"guest_list_id\":\"" + guest_list_id + "\"");
		sb.append("}");
	
		for (int i = 0; i < list.size(); i++) {
			String table_ip = ip;
			if (ip_list.get(i).equals(table_ip)) {
				list.get(i).send(sb.toString());
			}
		}
	}
	public void sendStart(String requestType,String start) {
		System.out.println("게임시작한다?");
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"start\":\"" + start + "\"");
		sb.append("}");
	
		for (int i = 0; i < list.size(); i++) {
			list.get(i).send(sb.toString());
		}
	}
}
