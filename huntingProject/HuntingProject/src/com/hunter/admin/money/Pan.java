package com.hunter.admin.money;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.hunter.admin.member.MemberTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.event.ActionListener;import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Pan extends JFrame{
	JPanel wrapper ;
	JPanel p_west,p_east;
	Choice choice;
	JLabel l_data;
	JTable monthTable,dayTable;
	JScrollPane scroll,scroll2;
	MoneyMonthTableModel monthTableModel;
	MoneyDayTableModel dayTableModel;
	
	public Pan() {
		setSize(1400,800);
		wrapper = new JPanel();
		getContentPane().add(wrapper, BorderLayout.CENTER);
		wrapper.setLayout(new BorderLayout(0, 0));
		wrapper.setPreferredSize(new Dimension(1400,800));
		
		p_west = new JPanel();
		wrapper.add(p_west, BorderLayout.WEST);
		p_west.setPreferredSize(new Dimension(480,800));
		p_west.setLayout(null);
		
		choice = new Choice();
		choice.setBounds(115, 30, 300, 20);
		p_west.add(choice);
		
		p_east = new JPanel();
		wrapper.add(p_east, BorderLayout.EAST);
		p_east.setPreferredSize(new Dimension(900,800));
		p_east.setLayout(null);
		
		l_data = new JLabel("¸î¿ù ¸îÀÏ");
		l_data.setBounds(100, 30, 100, 20);
		l_data.setFont(new Font("HY¿±¼­L", Font.PLAIN, 15));
		p_east.add(l_data);
		
		monthTable=new JTable();
		monthTableModel=new MoneyMonthTableModel();
		monthTable.setRowHeight(65);
		monthTable.setModel(monthTableModel=new MoneyMonthTableModel());
		monthTable.setFont(new Font("HY¿±¼­L", Font.PLAIN, 15));
		scroll=new JScrollPane(monthTable);
		scroll.setSize(330, 630);
		scroll.setLocation(100, 80);
		scroll.setPreferredSize(new Dimension(330,650));
		p_west.add(scroll);
		
		
		dayTable=new JTable();
		dayTableModel=new MoneyDayTableModel();
		dayTable.setRowHeight(65);
		dayTable.setModel(dayTableModel=new MoneyDayTableModel());
		dayTable.setFont(new Font("HY¿±¼­L", Font.PLAIN, 15));
		scroll2=new JScrollPane(dayTable);
		scroll2.setLocation(100, 60);
		scroll2.setSize(700, 600);
		scroll2.setPreferredSize(new Dimension(700,600));
		p_east.add(scroll2);
		
		choice.add("¸î³â ¸î¿ù");
		
		
		JButton bt_edit = new JButton("¼öÁ¤");
		bt_edit.setBounds(330, 680, 100, 30);
		bt_edit.setFont(new Font("HY¿±¼­L", Font.PLAIN, 15));
		p_east.add(bt_edit);
		
		JButton bt_detail = new JButton("»ó¼¼ º¸±â");
		bt_detail.setBounds(470, 680, 100, 30);
		bt_detail.setFont(new Font("HY¿±¼­L", Font.PLAIN, 15));
		p_east.add(bt_detail);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(20, 20, 1, 700);
		p_east.add(separator);
		
		bt_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		bt_detail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		monthTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new Pan();
	}
}
