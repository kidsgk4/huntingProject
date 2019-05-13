package com.hunter.admin.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JButton;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTable;

import com.hunter.admin.Main;
import java.awt.Component;

public class GameMain extends JPanel {
   Main main;
   JTable gameTable;
   JScrollPane scroll;
   GameTableModel gameTableModel;
   int order_id;
    JButton bt_ok;
    JButton bt_delete;

   public GameMain(Main main) {
      setBackground(new Color(230, 230, 250));
      this.main = main;
      setPreferredSize(new Dimension(1400, 800));
      setLayout(null);


      gameTable = new JTable();
      gameTableModel = new GameTableModel();
      gameTable.setRowHeight(65);
      gameTable.setModel(gameTableModel = new GameTableModel());
      gameTable.setFont(new Font("MD개성체", Font.PLAIN, 15));
      scroll = new JScrollPane(gameTable);
      scroll.setLocation(350, 114);
      scroll.setSize(700, 650);
      scroll.setPreferredSize(new Dimension(700, 600));
      add(scroll);


      
      bt_ok = new JButton("게임생성");
      bt_ok.setBounds(722, 44, 100, 30);
      bt_ok.setFont(new Font("MD개성체", Font.PLAIN, 15));
      add(bt_ok);
      
      bt_delete = new JButton("게임시작");
      bt_delete.setBounds(582, 42, 100, 30);
      bt_delete.setFont(new Font("MD개성체", Font.PLAIN, 15));
      add(bt_delete);

      bt_ok.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 sendOpen();
         }
      });

      bt_delete.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (order_id == 0) {
               JOptionPane.showMessageDialog(main, "취소할 주문을 선택해 주세요!");
               return;
            }
            
         }
      });


      gameTable.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            int row = gameTable.getSelectedRow();
            
            //table_no = (Integer) dayTable.getValueAt(row, 1);
            //member_id = (Integer) dayTable.getValueAt(row, 2);
            
            //임시로 이거 나중에 지워야함
            order_id=1;
         }
      });
      

   }
   public void sendOpen() {
	 main.test();
   }

}