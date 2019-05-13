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
		System.out.println("admin ���� ����!!");
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
		System.out.println("admin ���� runserver!!");
		try {
			server = new ServerSocket(port); // ���� ����
			while (true) {
				Socket client = server.accept(); // ���� ����( �����ڸ� ��ٸ� )

				ip = client.getInetAddress().getHostAddress();
				System.out.println(ip + "���� ����!!");
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
				System.out.println("sendIp���� ������ table_ip��"+table_ip);
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
						table_ip + "���� senddata ����\n ���� info_id��" + table_info_id + "\n ���� table_ip�� " + table_ip);
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
		System.out.println("�տ����ϴ� üũ�����" + idCheck);
		System.out.println("�տ����ϴ� table_ip" + table_ip);

		for (int i = 0; i < list.size(); i++) {
			System.out.println("ip_list�� ���ִ� ip" + ip_list.get(i));
			if (ip_list.get(i).equals(table_ip)) {
				list.get(i).send(sb.toString());
				System.out.println("üũ �����" + idCheck);
				// System.out.println(table_ip+"���� senddata ����\n ���� info_id��"+table_info_id+"\n
				// ���� table_ip�� "+table_ip);
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
			System.out.println("ip_list�� ���ִ� ip" + ip_list.get(i));
			if (ip_list.get(i).equals(table_ip)) {
				list.get(i).send(sb.toString());
				System.out.println("üũ �����" + check);
			}
		}
	}
	
	//�ٽ� order_summary_id�� ���������...��? entry_list�� �ش��ϴ� order_summary_id�� �ٽ� Ŭ���̾�Ʈ���� order_detail�� ������� �ϴϱ�...
	//�׷��� ip�� �����ڸ� �����ϸ�...�������� ������ ������.......
	//�� entry_list�� �ش��ϴ� order_summary_Id�� ���������� ���� �� �ִµ�...�׷� ��Ժ�����..�ݺ��� rs.next�� ��� send�� �ϰ� client���� �̰� �����Ҷ� �ݺ���??
	public void sendOrderSummaryBack(String requestType, String order_summary_id) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"order_summary_id\":\"" + order_summary_id + "\"");
		sb.append("}");
		
		for (int i = 0; i < list.size(); i++) {
			String table_ip = ip;
			System.out.println("ip_list�� ���ִ� ip" + ip_list.get(i));
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
		System.out.println("���ӽ����Ѵ�?");
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
