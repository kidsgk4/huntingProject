package com.hunter.admin.table;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.hunter.admin.Main;

public class TableMain extends JPanel{
	Main main;
	public TableMain(Main main) {
		this.main=main;
		setBackground(Color.yellow);
		setPreferredSize(new Dimension(1400, 900));
	}

}
