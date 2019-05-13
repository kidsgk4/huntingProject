package com.hunter.user.order;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Animation extends JPanel{
	String[] list = { 
			"C:/java_developer/javaSE/HuntingProject/res/new1.png",
			"C:/java_developer/javaSE/HuntingProject/res/new2.png", 
			"C:/java_developer/javaSE/HuntingProject/res/new3.png",
			"C:/java_developer/javaSE/HuntingProject/res/new4.png", 
			"C:/java_developer/javaSE/HuntingProject/res/new5.png",
			"C:/java_developer/javaSE/HuntingProject/res/new6.png" 
			};// 슬라이드 이미지 배열
	JLabel slidePic,slidePic2;
	Timer timer;
	int s = 0;
	int s1 = 1;
	public Animation() {
		slidePic = new JLabel();
		slidePic2 = new JLabel();
		timer = new Timer(2000,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slideImg(s,slidePic);
				slideImg(s1,slidePic2);
				s += 1;
				s1 +=1;
				if(s>=list.length) {
					s = 0;
				}
				if( s1>=list.length) {
					s1=0;
				}
			}
		});
		setLayout(new FlowLayout(FlowLayout.LEFT,0,0)); // 사진 위아래 배치용
		add(slidePic);
		add(slidePic2);
		
		slideImg(5,slidePic);
		slideImg(5,slidePic2);
		timer.start();
		setPreferredSize(new Dimension(410,425)); // 지정 안해주면 flowlayout이 안먹히나..
	}
	//downpanel 크기 바뀌면 이쪽 사이즈 2개 조정
	public void slideImg(int i,JLabel label) {
		ImageIcon icon = new ImageIcon(list[i]);
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(400,430, Image.SCALE_SMOOTH);
		ImageIcon newImc = new ImageIcon(newImg);
		label.setIcon(newImc);
	}
}
