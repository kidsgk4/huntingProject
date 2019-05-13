package com.hunter.admin.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.hunter.admin.Main;
import com.hunter.admin.member.MemberEdit;

public class MenuMain extends JPanel {
   Main main;
   // 연동 관련
   RegistMenu md;
   MenuDetail menuDetail;

   JPanel p_north; // 검색
   JPanel p_center; // JTable 출력 패널
   JPanel p_south; // 기능 버튼들 패널

   // JTable 관련
   JTable table;
   JScrollPane scroll;
   MenuTableModel model;
   JLabel l_search;
   JTextField t_search;
   JButton bt_regist; // 테이블에서 등록 버튼
   JButton bt_edit; // 테이블에서 수정 버튼
   JButton bt_del; // 테이블에서 삭제 버튼
   // JButton bt_search;
   DefaultTableModel defaultTableModel;
   String selImg; // 선택했을때 이미지명을 알아야하니깐
   ArrayList nameArray = new ArrayList();
   ArrayList list;
   MenuEdit menuEdit;
   String name,option,type;
   String userData = "C:/data";

   int menu_list_id;
   int row;
   int col;

   public MenuMain(Main main) {
      this.main = main;
      
      
      
      setPreferredSize(new Dimension(1400, 800));
      setLayout(new FlowLayout());
  
      p_center = new JPanel();
      p_south = new JPanel();
      p_north = new JPanel();

      table = new JTable();
      scroll = new JScrollPane(table);
      model = new MenuTableModel();
      table.setRowHeight(90);
      scroll.setPreferredSize(new Dimension(1000, 700));

      bt_regist = new JButton("신메뉴 등록");
      bt_edit = new JButton("선택 메뉴 수정");
      bt_del = new JButton("선택 메뉴 삭제");
      // bt_search = new JButton("검색");
      l_search = new JLabel("메뉴 검색");
      t_search = new JTextField();
      list = new ArrayList();

      
      Dimension d = new Dimension(145, 25);
      bt_regist.setPreferredSize(d);
      l_search.setPreferredSize(new Dimension(100, 25));
      t_search.setHorizontalAlignment(SwingConstants.CENTER);
      bt_edit.setPreferredSize(d);
      bt_del.setPreferredSize(d);
      // bt_search.setPreferredSize(d);
      // l_search.setPreferredSize(new Dimension(55, 25));
      t_search.setPreferredSize(d);

      p_north.add(l_search);
      p_north.add(t_search);

      p_center.add(scroll);
      p_south.add(bt_regist);
      p_south.add(bt_edit);
      p_south.add(bt_del);

      setLayout(new BorderLayout());
      add(p_north, BorderLayout.NORTH);
      add(p_center);
      add(p_south, BorderLayout.SOUTH);

      table.setModel(model = new MenuTableModel());
      table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      table.setFont(new Font("MD개성체", Font.PLAIN, 15));
      menuDetail=new MenuDetail(this);
      menuDetail.selectAll();

      table.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            row = table.getSelectedRow();
            col = table.getSelectedColumn();
            menu_list_id = (Integer) table.getValueAt(row, 0);
            selImg = (String) list.get(row);
            name = (String) table.getValueAt(row,2); // 이름과 비교
            type = (String) table.getValueAt(row, 3);
            option = (String)table.getValueAt(row, 5);
            System.out.println(selImg);

            // selImg = (String) table.getValueAt(row, 5);
         }
      });

      bt_regist.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showRegistMenu();
         }
      });

      bt_edit.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            edit();
         }
      });

      bt_del.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (menu_list_id == 0) {
               JOptionPane.showMessageDialog(md, "삭제할 상품을 선택하세요!");
               return;
            }
            if (JOptionPane.showConfirmDialog(md, "삭제 하시겠습니까?") == JOptionPane.OK_OPTION) {
               if (deleteFile()) {
                  delete();
                  menuDetail.selectAll();
                  table.updateUI();
                  //md.regist_img = null;
                  //md.can.repaint();

               }
            }
         }
      });
      
      /*
       * bt_search.addActionListener(new ActionListener() { public void
       * actionPerformed(ActionEvent e) { if (t_search.getText().length() > 0) {
       * String keyWord = t_search.getText(); searchMenu(keyWord); } else {
       * JOptionPane.showMessageDialog(main, "검색어를 입력해주세요!"); } } });
       */

      t_search.addKeyListener(new KeyAdapter() {
         @Override
         public void keyReleased(KeyEvent e) {
            search();

         }

      });
   }

   public boolean deleteFile() {
      // System.out.println(md.img + "를 삭제하시겠어요?");
      boolean result = false;
      //System.out.println(md.userData);
      File file = new File(userData + File.separator + selImg);
      
      result = file.delete();
      return result;
   }

   public void delete() {
      Connection con = main.getCon();
      PreparedStatement pstmt = null;

      String sql = "delete from menu_list where menu_list_id = " + menu_list_id;
      try {
         pstmt = con.prepareStatement(sql);

         int result = pstmt.executeUpdate();
         if (result == 0) {
            JOptionPane.showMessageDialog(this, "삭제 실패!");
            System.out.println(menu_list_id);
         } else {
            JOptionPane.showMessageDialog(this, "삭제 성공!");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void showRegistMenu() {
      md = new RegistMenu(this);
   }

   public void searchMenu(String keyWord) {
      for (int i = 0; i < table.getRowCount(); i++) {
         if (nameArray.size() < table.getRowCount()) {
            nameArray.add(table.getValueAt(i, 3));
         }
      }
      for (int i = 0; i < nameArray.size(); i++) {
         String name = (String) nameArray.get(i);
         if (keyWord.equals(name)) {
            if (table.getValueAt(i, 3) == name) {
               table.setRowSelectionInterval(0, i);
            }
         }
      }
   }

   public void search() {
      Connection con = main.getCon();
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String typed = t_search.getText();

      String sql = "select l.menu_list_id,l.menu_list_img,t.menu_type_name as type_name,l.menu_list_name,l.menu_list_price,o.menu_option_name as option_name from menu_list l, menu_type t,menu_option o where l.menu_type_id = t.menu_type_id and l.menu_option_id = o.menu_option_id and l.menu_list_name like '%"
            + typed + "%'";
      // System.out.println(sql);
      list.removeAll(list);
      try {
         pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         rs = pstmt.executeQuery();
         rs.last();
         int total = rs.getRow();

         Object[][] data = new Object[total][model.columnTitle.length];
         rs.beforeFirst();
         for (int i = 0; i < total; i++) {
            rs.next();
            list.add(rs.getString("menu_list_img"));
            ImageIcon icon = new ImageIcon(model.path + rs.getString("menu_list_img"));
            icon = new ImageIcon(icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH));
            data[i][0] = rs.getInt("menu_list_id");
            data[i][1] = icon;
            data[i][2] = rs.getString("menu_list_name");
            data[i][3] = rs.getString("type_name");
            data[i][4] = rs.getString("menu_list_price");
            data[i][5] = rs.getString("option_name");

         }
         model.data = data;
         table.updateUI();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if (rs != null) {
            try {
               rs.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
         if (pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
      }
   }
   public void edit() {
      menuEdit = new MenuEdit(this, menu_list_id);
   }
}