package com.hunter.user.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.hunter.user.UserMain;

public class GameMain extends JPanel {
	UserMain userMain;
	String TAG = this.getClass().getName();
	JButton bt;
	JPanel stage, p_north;
	GamePanel gamePanel;
	Thread thread;
	boolean flag = false; // 게임의 pause/restart여부 결정 변수

	public GameMain(UserMain userMain) {
		this.userMain=userMain;
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(1400,900));
		p_north = new JPanel();
		bt = new JButton("시작/정지");
		gamePanel = new GamePanel(this);
		thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					if (flag) {
						gamePanel.check();
						gamePanel.tick();
						gamePanel.repaint(); 
						gamePanel.finishGame();
					}
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = !flag;

			}
		});

		add(p_north, BorderLayout.NORTH);
		add(gamePanel, BorderLayout.CENTER);
		p_north.add(bt);

		setVisible(true);// 창띄우기
		thread.start();
	}


}