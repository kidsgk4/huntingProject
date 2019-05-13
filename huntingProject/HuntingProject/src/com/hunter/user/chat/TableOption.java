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
	JButton bt_left;//Ÿ�� ��ư Ŭ�� �� ��ȭ�ɱ�(��ȭ�� ������ ����), �� ��ư Ŭ�� �� �� ��ȭ�� ����
	JButton bt_right;//Ÿ�� ��ư Ŭ�� �� �����ϱ�, �� ��ư Ŭ�� �� bill check
	JPanel p_south;
	JButton bt_close;
	//������ ���
	ChatMain chatMain;
	TableMap tableMap;
	//ä�ÿ�
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
		bt_left = new JButton("���ʹ�ư");
		bt_right = new JButton("�����ʹ�ư");
		p_south = new JPanel();
		bt_close = new JButton("�ݱ�");
		
		//ũ�� ����
		p_north.setPreferredSize(new Dimension(280, 40));
		la_title.setPreferredSize(new Dimension(280, 40));
		p_center.setPreferredSize(new Dimension(280, 100));
		bt_left.setPreferredSize(new Dimension(135, 100));
		bt_right.setPreferredSize(new Dimension(135, 100));
		p_south.setPreferredSize(new Dimension(280, 40));
		bt_close.setPreferredSize(new Dimension(270, 40));
		bt_close.setBorder(BorderFactory.createEmptyBorder(0, 135, 0, 0));
		
		//����
		la_title.setBackground(Color.ORANGE);
		la_title.setForeground(new Color(34,61,75));
		bt_left.setBackground(new Color(27,188,155));
		bt_right.setBackground(new Color(27,188,155));
		bt_close.setBackground(Color.ORANGE);
		bt_left.setForeground(new Color(34,61,75));
		bt_right.setForeground(new Color(34,61,75));
		bt_close.setForeground(new Color(34,61,75));
		
		//���� 
		p_north.add(la_title);
		add(p_north,BorderLayout.NORTH);
		p_center.add(bt_left);
		p_center.add(bt_right);
		add(p_center);
		p_south.add(bt_close);
		add(p_south,BorderLayout.SOUTH);
		
		//������
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
		if(myTable) {//���� �� ���̺� Ŭ������ ��� :: �α����� ���̵� ������ ���̺� ��ȣ, Ŭ������ ���̺��ȣ�� �������� TableMap���� �Ǵ��ؼ� �÷��׸� �Ѱ���
			la_title.setText("My Table �ɼ�");
			bt_left.setText("�� ��ȭ�� ����");
			bt_right.setText("���ܿ��κ���");
			bt_left.addActionListener((e)-> {
				createChat(TableOption.this.tableIp);					
			});
			bt_right.addActionListener((e)-> {
				showBlockList();
			});
		}else {//�ٸ� ���̺� Ŭ������ ���
			la_title.setText("Selected Table �ɼ�");
			bt_left.setText("��뿡�� ��ȭ�ɱ�");
			bt_right.setText("�����ϱ�");
			
			bt_left.addActionListener((e)->{
				createChat(TableOption.this.tableIp);
			});
			bt_right.addActionListener((e)->{
				blockUser();
			});
		}
	}
	
	//ä��ȭ�� ����,ȣ�� �޼���
	public void createChat(String ip) {
		JOptionPane.showMessageDialog(chatMain,"ä�ý����ҰŴ�?");
		//chatServer = new ChatServer();
		//chatClient = new ChatClient(ip);//����
		//chatClient = new ChatClient(ip);//����
		TableOption.this.dispose();
	}
	
	//�����ϱ�
	public void blockUser() {//�μ��� ���� ���̺� ��ȣ �޾ƾ� ��
		//���̺� ��ȣ�� ��Ī�Ǵ� ip�� ���ܸ�Ͽ� �ִ´�.
		
		//ip�� ������ iterator�� chadan ���� ��ȸ�ؼ� ��ġ���� ��
		//����� true(������) �׳� �ְ�,
		//false(������) ��Ͽ� �߰�
	}
	
	//���ܸ�Ϻ���
	public void showBlockList() {
		Map<String,String> blockList=null;
		//blockList = this.chadanList.getChadanList();//Map<String,String>Ÿ���� chadan ����
		String key = null;
		String obj = null;
		//iterator�� ����ϱ� ���� set�� ����
		Set set = blockList.keySet();
		
		//iterator
		Iterator<String> it = set.iterator();//key�� ��� �Ϸķ� �þ����
		while(it.hasNext()) {
			key = it.next();
			obj = blockList.get(key);
		}
		JOptionPane.showMessageDialog(chatMain,"������ �����\n"+key+", "+obj+"�Դϴ�.");
	}
}
