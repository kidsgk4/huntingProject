package com.hunter.user.order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.hunter.user.User;
import com.hunter.user.UserMain;

public class OrderMain extends JPanel{
	String TAG = this.getClass().getName();
	UserMain main;
	User user;
	JPanel adver; // 광고나올 패널
	JPanel container; // 안주류/주류/사이드 패널 나올 컨테이너
	Bag bag; // 장바구니 / 주문내역 나올 패널
	int table_no;
	public OrderMain(UserMain main,User user) {
		System.out.println(TAG +"가 메모리에 올라갔다."+" OrderMAin에서 구한 테이블 번호는"+user.getTableNum());
		this.main=main;
		this.user=user;
		System.out.println("현재 테이블 번호는 "+user.getTableNum());
		table_no = user.getTableNum();
		setLayout(new BorderLayout());
		adver = new Animation();
		bag = new Bag(main,this);
		container = new OrderList(main,bag);

		add(adver,BorderLayout.WEST);
		add(container);
		add(bag,BorderLayout.EAST);
		this.setBackground(Color.PINK);
		this.setPreferredSize(new Dimension(1400,900));
	}
}