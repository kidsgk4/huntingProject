   package com.hunter.admin.game;
   
   import java.awt.Image;
   
   import javax.swing.ImageIcon;
   import javax.swing.table.AbstractTableModel;
   
   public class GameTableModel extends AbstractTableModel {
      String[] columnTitle = { "ȸ��", "1��","2��"};
      Object[][] data;   
      public String getColumnName(int col) {
         return columnTitle[col];
      }
      public int getColumnCount() {
         return columnTitle.length;
      }

      public int getRowCount() {
         return 12;
//         return data.length;
      }

      public Object getValueAt(int rowIndex, int columnIndex) {
         return "����";
//         return data[rowIndex][columnIndex];
      }
   
   }