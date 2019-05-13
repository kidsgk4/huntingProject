package com.hunter.admin.chat;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.hunter.admin.Main;

public class ChatMain extends JPanel{
	Main main;
	public ChatMain(Main main) {
		this.main=main;
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(1400, 900));
	}

}
