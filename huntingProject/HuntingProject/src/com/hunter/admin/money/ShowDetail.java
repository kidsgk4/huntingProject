package com.hunter.admin.money;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Rectangle;

public class ShowDetail extends JFrame {
	MoneyMain moneyMain;
	int table_no;
	int member_id;
	
	JPanel p_west;
	JLabel l_table;
	JPanel p_east;
	JTable detailTable;
	MoneyDetailTableModel detailTableModel;
	
	JScrollPane scroll;
	JButton bt_edit;
	JButton bt_delete;
	int order_id;
	private JLabel l_total,l_dc,l_dcType,l_pay,l_how;
	private JTextField t_total,t_dc,t_dcType,t_pay,t_how;
	private JButton bt_ok;

	public ShowDetail(MoneyMain moneyMain, int table_no, int member_id) {
		this.moneyMain = moneyMain;
		this.table_no = table_no;
		this.member_id = member_id;

		p_west = new JPanel();
		getContentPane().add(p_west, BorderLayout.WEST);
		p_west.setPreferredSize(new Dimension(391, 500));
		p_west.setLayout(null);
		
		l_table = new JLabel("00번 테이블 상세 주문 내역");
		l_table.setFont(new Font("HY엽서L", Font.PLAIN, 15));
		l_table.setHorizontalAlignment(SwingConstants.CENTER);
		l_table.setBounds(30, 20, 330, 20);
		p_west.add(l_table);
		
		detailTable=new JTable();
		detailTable.setBackground(new Color(255, 218, 185));
		detailTableModel=new MoneyDetailTableModel();
		detailTable.setRowHeight(40);
		detailTable.setModel(detailTableModel=new MoneyDetailTableModel());
		detailTable.setFont(new Font("HY엽서L", Font.PLAIN, 15));
		scroll=new JScrollPane(detailTable);
		scroll.setBackground(new Color(255, 255, 255));
		scroll.setSize(330, 340);
		scroll.setLocation(30, 50);
		scroll.setPreferredSize(new Dimension(330, 700));
		p_west.add(scroll);
		
		bt_edit = new JButton("수정");
		bt_edit.setFont(new Font("MD개성체", Font.PLAIN, 15));
		bt_edit.setBounds(80, 410, 100, 30);
		p_west.add(bt_edit);
		
		bt_delete = new JButton("삭제");
		bt_delete.setFont(new Font("MD개성체", Font.PLAIN, 15));
		bt_delete.setBounds(210, 410, 100, 30);
		p_west.add(bt_delete);

		p_east = new JPanel();
		getContentPane().add(p_east, BorderLayout.EAST);
		p_east.setPreferredSize(new Dimension(391, 500));
		p_east.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 218, 185));
		panel.setBounds(30, 50, 330, 340);
		p_east.add(panel);
		panel.setLayout(null);
		
		l_total = new JLabel("합계금액");
		l_total.setBounds(30, 30, 60, 18);
		panel.add(l_total);
		l_total.setBackground(new Color(230, 230, 250));
		l_total.setFont(new Font("HY강M", Font.PLAIN, 15));
		l_total.setHorizontalAlignment(SwingConstants.CENTER);
		
		t_total = new JTextField();
		t_total.setBounds(120, 30, 180, 21);
		panel.add(t_total);
		t_total.setHorizontalAlignment(SwingConstants.CENTER);
		t_total.setEditable(false);
		t_total.setColumns(10);
		t_total.setText("60000원");
		
		l_dc = new JLabel("할인금액");
		l_dc.setBounds(30, 90, 60, 18);
		panel.add(l_dc);
		l_dc.setBackground(new Color(230, 230, 250));
		l_dc.setFont(new Font("HY강M", Font.PLAIN, 15));
		l_dc.setHorizontalAlignment(SwingConstants.CENTER);
		
		t_dc = new JTextField();
		t_dc.setBounds(120, 90, 180, 21);
		panel.add(t_dc);
		t_dc.setHorizontalAlignment(SwingConstants.CENTER);
		t_dc.setEditable(false);
		t_dc.setColumns(10);
		
		l_dcType = new JLabel("할인종류");
		l_dcType.setBounds(30, 150, 60, 18);
		panel.add(l_dcType);
		l_dcType.setBackground(new Color(230, 230, 250));
		l_dcType.setFont(new Font("HY강M", Font.PLAIN, 15));
		l_dcType.setHorizontalAlignment(SwingConstants.CENTER);
		
		t_dcType = new JTextField();
		t_dcType.setBounds(120, 150, 180, 21);
		panel.add(t_dcType);
		t_dcType.setHorizontalAlignment(SwingConstants.CENTER);
		t_dcType.setEditable(false);
		t_dcType.setColumns(10);
		
		l_pay = new JLabel("결제금액");
		l_pay.setBounds(30, 210, 60, 18);
		panel.add(l_pay);
		l_pay.setBackground(new Color(230, 230, 250));
		l_pay.setFont(new Font("HY강M", Font.PLAIN, 15));
		l_pay.setHorizontalAlignment(SwingConstants.CENTER);
		
		t_pay = new JTextField();
		t_pay.setBounds(120, 210, 180, 21);
		panel.add(t_pay);
		t_pay.setHorizontalAlignment(SwingConstants.CENTER);
		t_pay.setEditable(false);
		t_pay.setColumns(10);
		
		l_how = new JLabel("결제수단");
		l_how.setBounds(30, 270, 60, 18);
		panel.add(l_how);
		l_how.setBackground(new Color(230, 230, 250));
		l_how.setFont(new Font("HY강M", Font.PLAIN, 15));
		l_how.setHorizontalAlignment(SwingConstants.CENTER);
		
		t_how = new JTextField();
		t_how.setBounds(120, 270, 180, 21);
		panel.add(t_how);
		t_how.setHorizontalAlignment(SwingConstants.CENTER);
		t_how.setEditable(false);
		t_how.setColumns(10);
		
		bt_ok = new JButton("확인");
		bt_ok.setFont(new Font("MD개성체", Font.PLAIN, 15));
		bt_ok.setBounds(145, 410, 100, 30);
		p_east.add(bt_ok);
		
		
		bt_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		bt_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		bt_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		
		detailTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = detailTable.getSelectedRow();
				int col = 0;
				order_id = (Integer) detailTable.getValueAt(row, col);
				
			}
		});

		setVisible(true);
		setSize(800, 500);
		setLocationRelativeTo(null);
	}
	
	public void close() {
		setVisible(false);
	}
}