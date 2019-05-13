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

public class CardPanelTwo extends JPanel{
	AskJoin cardMain;
	JPanel p_center;
	JPanel p_south;
	JLabel la_text;
	JButton bt_yes;
	JButton bt_no;
	public CardPanelTwo(AskJoin cardMain) {
		this.cardMain = cardMain;
		this.cardMain.setTitle("합석신청 응답대기중");
		setLayout(new BorderLayout());
		p_center = new JPanel();
		la_text = new JLabel("상대방의 응답을 기다리고 있습니다.");
		p_south = new JPanel();
		bt_yes = new JButton("확인");
		bt_no = new JButton("취소");
		
		//크기,색상
		Color c1 = new Color(238,238,238);
		p_center.setPreferredSize(new Dimension(390, 200));
		la_text.setPreferredSize(new Dimension(390, 200));
		la_text.setBackground(c1);
		la_text.setOpaque(true);
		p_south.setPreferredSize(new Dimension(390, 90));
		bt_yes.setPreferredSize(new Dimension(180,90));
		bt_yes.setBackground(c1);
		bt_no.setPreferredSize(new Dimension(180,90));
		bt_no.setBackground(c1);
		
		//부착
		p_center.add(la_text);
		p_south.add(bt_yes);
		p_south.add(bt_no);
		add(p_center);
		add(p_south,BorderLayout.SOUTH);
		
		//이벤트 리스너
		bt_yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardPanelTwo.this.cardMain.getCardLayout().show(CardPanelTwo.this.cardMain.getContentPane(), "Three");
			}
		});
		bt_no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(CardPanelTwo.this, "취소하셨습니다.");
				CardPanelTwo.this.cardMain.dispose();
			}
		});
		
		setBackground(new Color(255, 153, 154));
		setPreferredSize(new Dimension(390, 290));
		setVisible(true);
	}
}
