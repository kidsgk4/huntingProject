package com.hunter.user.init;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.hunter.user.User;
import com.hunter.user.UserMain;
/*
public class InitMain extends JPanel {
	UserMain userMain;
	User user;

	JPanel container;
	JPanel p_guest, p_login, p_join;
	JLabel la_guest, la_login, la_join;

	public InitMain(UserMain userMain, User user) {
		// 메모리에 올리기
		this.userMain = userMain;
		this.user = user;
		container = new JPanel();
		p_guest = new JPanel();
		p_login = new JPanel();
		p_join = new JPanel();
		la_guest = new JLabel("GUEST");
		la_login = new JLabel("LOGIN");
		la_join = new JLabel("JOIN");

		// 패널 사이즈 조정

		container.setPreferredSize(new Dimension(1300, 400));
		this.setBorder(BorderFactory.createEmptyBorder(300, 0, 0, 0));

		Dimension d = new Dimension(350, 350);
		p_guest.setPreferredSize(d);
		p_login.setPreferredSize(d);
		p_join.setPreferredSize(d);
		// 글자 사이즈 조정
		Dimension f = new Dimension(200, 350);
		la_guest.setPreferredSize(f);
		la_guest.setHorizontalAlignment(SwingConstants.CENTER);
		la_login.setPreferredSize(f);
		la_login.setHorizontalAlignment(SwingConstants.CENTER);
		la_join.setPreferredSize(f);
		la_join.setHorizontalAlignment(SwingConstants.CENTER);

		// 서식 조정
		la_guest.setFont(new Font("Verdana", Font.BOLD, 50));
		la_guest.setForeground(Color.WHITE);
		la_login.setFont(new Font("Verdana", Font.BOLD, 50));
		la_login.setForeground(Color.WHITE);
		la_join.setFont(new Font("Verdana", Font.BOLD, 50));
		la_join.setForeground(Color.WHITE);

		// 패널 배경색
		container.setBackground(Color.black);
		p_guest.setBackground(Color.blue);
		p_login.setBackground(Color.GREEN);
		p_join.setBackground(Color.CYAN);

		// 글자부>패널>컨테이너 패널 부착

		p_guest.add(la_guest);
		p_login.add(la_login);
		p_join.add(la_join);

		container.add(p_guest);
		container.add(p_login);
		container.add(p_join);
		add(container);
		

		// p_guest 리스너 연결
		p_guest.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				user.getGuest().addGuest();
				userMain.showPage(4);
			}

			public void mouseEntered(MouseEvent e) {
				p_guest.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				p_guest.setBackground(Color.BLUE);
			}
		});
		// p_login와 리스너 연결
		p_login.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				user.getMemeber().addMember();
				userMain.showPage(2);
			}

			public void mouseEntered(MouseEvent e) {
				p_login.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				p_login.setBackground(Color.GREEN);
			}
		});
		// p_join과 리스너 연결
		p_join.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				userMain.showPage(3);
			}

			public void mouseEntered(MouseEvent e) {
				p_join.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				p_join.setBackground(Color.CYAN);
			}
		});

		setBackground(Color.black);
		setPreferredSize(new Dimension(1400, 800));
	}
	

}*/
