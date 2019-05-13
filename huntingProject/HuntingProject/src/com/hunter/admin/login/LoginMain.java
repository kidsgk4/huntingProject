package com.hunter.admin.login;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class LoginMain extends JPanel{
	LoginMain main;
	public LoginMain(LoginMain main) {
		this.main=main;
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(1400, 900));
	}

}
