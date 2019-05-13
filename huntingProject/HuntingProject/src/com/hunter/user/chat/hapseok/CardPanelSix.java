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

public class CardPanelSix extends JPanel{
	AskJoin cardMain;
	JPanel p_center;
	JPanel p_south;
	JLabel la_text;
	JButton bt_confirm;
	public CardPanelSix(AskJoin cardMain) {
		this.cardMain = cardMain;
		this.cardMain.setTitle("계산 안내");
		setLayout(new BorderLayout());
		p_center = new JPanel();
		String infoText = "즐거운 시간 되시길 바라며\n";
		infoText += "합석하실 좌석으로 이동하시기 전에\n";
		infoText += "카운터에서 기존 좌석 결제를\n";
		infoText += "완료해주세요^^";
		la_text = new JLabel();
		la_text.setText(infoText);
		p_south = new JPanel();
		bt_confirm = new JButton("확인");
		
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
				CardPanelSix.this.cardMain.dispose();
			}
		});
		
		setBackground(new Color(255, 153, 154));
		setPreferredSize(new Dimension(390, 290));
		setVisible(true);
	}
}
