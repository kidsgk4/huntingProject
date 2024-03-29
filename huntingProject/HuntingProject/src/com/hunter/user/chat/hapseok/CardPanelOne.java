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

public class CardPanelOne extends JPanel{
	AskJoin cardMain;
	JPanel p_center;
	JPanel p_south;
	JLabel la_text;
	JButton bt_yes;
	JButton bt_no;
	public CardPanelOne(AskJoin cardMain) {
		this.cardMain = cardMain;
		this.cardMain.setTitle("상대 테이블에 합석신청");
		setLayout(new BorderLayout());
		p_center = new JPanel();
		la_text = new JLabel("합석을 신청하시겠습니까?");
		p_south = new JPanel();
		bt_yes = new JButton("예");
		bt_no = new JButton("아니오");
		
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
				CardPanelOne.this.cardMain.getCardLayout().show(CardPanelOne.this.cardMain.getContentPane(), "Two");
			}
		});
		bt_no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(CardPanelOne.this, "취소하셨습니다.");
				CardPanelOne.this.cardMain.dispose();
			}
		});
		
		setBackground(new Color(255, 153, 154));
		setPreferredSize(new Dimension(390, 290));
		setVisible(true);
	}
}
