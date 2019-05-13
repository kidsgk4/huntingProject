package com.hunter.user.regist;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.hunter.user.ClientMain;
import com.hunter.user.User;
import com.hunter.user.UserMain;
import com.hunter.user.chat.ChadanList;
import com.hunter.user.home.HomeMain;

public class RegistMain extends JFrame {
	String TAG = this.getClass().getName();//����� ����ǥ
	UserMain userMain;
	User user;
	HomeMain homeMain;
	JPanel p_north, p_center;
	JLabel la_title, la_gender, la_num;
	Choice ch;
	JTextField t_num;
	JButton bt_enter;
	JButton bt_cancel;

	// ����
	String tableIp;
	String guestName;
	int tableIndex = 0;
	int userId; // ������ pk�� (�Խ�Ʈ�ų�, ���)
	int gender_type_id;// ������ ���� Ÿ���� �ε���
	int total;// �� �ο���
	int guest_list_id;
	public RegistMain(UserMain userMain, User user) {
		System.out.println(TAG+" regtMain�� �޸𸮿� �ö󰬴�!!");
		this.userMain = userMain;
		this.user = user;

		// �޸𸮿� �ø���
		p_north = new JPanel();
		p_center = new JPanel();
		
		la_title = new JLabel("REGIST");
		la_title.setHorizontalAlignment(SwingConstants.CENTER);
		la_gender = new JLabel("�� ��");
		la_num = new JLabel("�ο� ��");
		la_num.setBounds(39, 60, 200, 50);
		ch = new Choice();
		ch.setBounds(244, 15, 200, 30);
		t_num = new JTextField();
		t_num.setHorizontalAlignment(SwingConstants.CENTER);
		t_num.setBounds(244, 60, 200, 50);
		bt_enter = new JButton("����");
		bt_cancel = new JButton("���"); 

		// ������ �����ϱ�
		Dimension d = new Dimension(200, 50);
		la_title.setPreferredSize(new Dimension(200, 100));
		la_gender.setPreferredSize(d);
		la_num.setPreferredSize(d);
		ch.setPreferredSize(d);
		t_num.setPreferredSize(d);

		bt_enter.setPreferredSize(new Dimension(100, 30));
		bt_cancel.setPreferredSize(new Dimension(100, 30));

		la_title.setFont(new Font("����", Font.BOLD, 50));
		la_gender.setFont(new Font("����", Font.BOLD, 20));
		ch.setFont(new Font("����", Font.BOLD, 20));
		la_num.setFont(new Font("����", Font.BOLD, 20));
		bt_enter.setFont(new Font("����", Font.BOLD, 20));
		bt_cancel.setFont(new Font("����", Font.BOLD, 20));

		// �����ϱ�
		ch.addItem("====����====");
		ch.addItem("����");
		ch.addItem("����");
		ch.addItem("ȥ��");
		p_north.add(la_title);
		p_center.add(la_gender);
		p_center.add(ch);
		p_center.add(la_num);
		p_center.add(t_num);
		

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(p_north, BorderLayout.NORTH);
		getContentPane().add(p_center);
		bt_enter = new JButton("����");
		bt_enter.setBounds(130, 140, 100, 30);
		p_center.add(bt_enter);

		bt_enter.setPreferredSize(new Dimension(100, 30));
		bt_enter.setFont(new Font("����", Font.BOLD, 20));
		bt_cancel = new JButton("���");
		bt_cancel.setBounds(260, 140, 100, 30);
		p_center.add(bt_cancel);
		bt_cancel.setPreferredSize(new Dimension(100, 30));
		bt_cancel.setFont(new Font("����", Font.BOLD, 20));

		ch.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//gender_type_name = ch.getSelectedItem();
				//System.out.println("������ ���̽��� �̸���: " + gender_type_name);
				gender_type_id = ch.getSelectedIndex();
				System.out.println(TAG+" ���� ���� �ε��� : "+gender_type_id);
			}
		});
		bt_enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enter();
				//userMain.getHomeMain().setUserInfo();
			}
		});
		bt_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeRegist();
			}
		});

		setSize(500, 300);
		setLocationRelativeTo(null);
		setVisible(true);// â����
	}
	
	public void closeRegist() {
		setVisible(false);
	}

	public void enter() {
		// db�� �α����� ����(���̺��ȣ, �������̵�,����,���ο���,������) �ѱ��
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;

		// �������� Ŭ������ ���, ȭ����ȯ
		int result = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?");
		if (result == JOptionPane.OK_OPTION) {
			total = (Integer.parseInt(t_num.getText()));// �ۼ��� �� �ο���

			user.setGender(gender_type_id);// ���� ����
			System.out.println("���â���� ����� ������ �ε�����: " + gender_type_id);
			//System.out.println("���â���� ����� ������ �̸���: " + gender_type_name);
			
			user.setTotal(total);// �� �ο��� ����
			System.out.println("���â���� ����� �� �ο����� : " + total);
			
			setVisible(false);
			userMain.setVisible(false);
			
			save();
			// Ȩ ȭ�� ����
			homeMain = new HomeMain(userMain, user);
			
		}
	}

	// db�� entry_list�� ����ϴ� �޼���
	// db�� �α����� ����(���̺��ȣ, �������̵�,����,���ο���,������) �ѱ��
	// db�� entry_list�� ����(ȸ���̵�, ��ȸ���̵� ����ϴ� �̰����� db�� entry_list�� �߰���Ų��.)
	public void save() {
		int table = user.getTableNum(); // ���̺� �� ���ϱ�
		if (user.getFlag()) { // �������pk ���ϱ�
			userId = user.getMemeber().getMemberId();
		} else {// �Խ�Ʈ ����pk ���ϱ�
			userId = user.getGuest().getGuestId();
			guestName = "guest" + System.currentTimeMillis();
			userMain.getInsertGuest("guest","1",guestName);
		}
		userMain.getEntryData("entry",Integer.toString(userId), Integer.toString(gender_type_id), Integer.toString(total));
		addUserList();
	}
	public void addUserList() {
		
		System.out.println(guest_list_id);
	}
}