package com.hunter.user.chat.hapseok;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CardPanelThree extends JPanel{
	AskJoin cardMain;
	JPanel p_center;
	JPanel p_south;
	JLabel la_text;
	JButton bt_confirm;
	public CardPanelThree(AskJoin cardMain) {
		this.cardMain = cardMain;
		this.cardMain.setTitle("짝짝짝~");
		setLayout(new BorderLayout());
		p_center = new JPanel();
		la_text = new JLabel("상대방이 합석을 수락했습니다.");
		p_south = new JPanel();
		bt_confirm = new JButton("확인 및 테이블선택");
		
		//크기,색상
		Color c1 = new Color(238,238,238);
		p_center.setPreferredSize(new Dimension(390, 200));
		la_text.setPreferredSize(new Dimension(390, 200));
		la_text.setBackground(c1);
		la_text.setOpaque(true);
		p_south.setPreferredSize(new Dimension(390, 90));
		bt_confirm.setPreferredSize(new Dimension(180,90));
		bt_confirm.setBackground(c1);
		
		//부착
		p_center.add(la_text);
		p_south.add(bt_confirm);
		add(p_center);
		add(p_south,BorderLayout.SOUTH);
		
		//이벤트 리스너
		bt_confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardPanelThree.this.cardMain.getCardLayout().show(CardPanelThree.this.cardMain.getContentPane(), "Five");
			}
		});
		
		setBackground(new Color(255, 153, 154));
		setPreferredSize(new Dimension(390, 290));
		setVisible(true);
	}
}
