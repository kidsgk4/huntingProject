package com.hunter.user.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.hunter.user.UserMain;

public class ChatMain_old extends JPanel{
	UserMain main;
	JPanel p_west;//테이블맵,채팅현황 나올 패널
	JPanel p_west_north;//west중 테이블맵 패널
	JPanel p_west_center;//west중 채팅현황 패널
	JPanel p_east;//
	JPanel p_east_north;
	JPanel p_east_center;
	public ChatMain_old(UserMain main) {
		this.main=main;
		setLayout(new BorderLayout());
		
		//p_west처리
		p_west = new JPanel();
		p_west.setLayout(new BorderLayout());
		p_west.setBackground(Color.orange);
		p_west.setPreferredSize(new Dimension(700, 900));
		p_west_north = new JPanel();
		p_west_north.setPreferredSize(new Dimension(700,450));
		p_west_center = new JPanel();
		p_west_center.setPreferredSize(new Dimension(700,450));
		
		//p_east처리
		p_east = new JPanel();
		p_east.setLayout(new BorderLayout());
		p_east.setBackground(Color.PINK);
		p_east.setPreferredSize(new Dimension(700, 900));
		p_east_north = new JPanel();
		p_east_north.setPreferredSize(new Dimension(700, 450));
		p_east_center = new JPanel();
		p_east_center.setPreferredSize(new Dimension(700, 450));
		
		//부착
		p_west.add(p_west_north,BorderLayout.NORTH);
		p_west.add(p_west_center);
		add(p_west,BorderLayout.WEST);
		
		p_east.add(p_east_north,BorderLayout.EAST);
		p_east.add(p_east_center);
		add(p_east,BorderLayout.EAST);
		
		setBackground(Color.RED);
		setVisible(true);
		setSize(1400,900);
	}
}
