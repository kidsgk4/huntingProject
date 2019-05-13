package com.hunter.admin.order;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.hunter.admin.Main;
import java.awt.Component;

public class OrderMain extends JPanel {
	Main main;

	JLabel l_order;
	JTable orderTable;
	JScrollPane scroll;
	OrderTableModel orderTableModel;
	int order_id;
	JButton bt_ok;
	JButton bt_delete;
	int row;
	public OrderMain(Main main) {
		setBackground(new Color(230, 230, 250));
		this.main = main;
		
		setPreferredSize(new Dimension(1400, 800));
		setLayout(null);

		l_order = new JLabel("실시간 주문현황");
		l_order.setHorizontalAlignment(SwingConstants.CENTER);
		l_order.setBounds(550, 25, 300, 30);
		l_order.setFont(new Font("MD개성체", Font.PLAIN, 30));
		add(l_order);

		orderTable = new JTable();
		orderTableModel = new OrderTableModel();
		orderTable.setRowHeight(65);
		orderTable.setModel(orderTableModel = new OrderTableModel());
		orderTable.setFont(new Font("MD개성체", Font.PLAIN, 15));
		scroll = new JScrollPane(orderTable);
		scroll.setLocation(350, 60);
		scroll.setSize(700, 650);
		scroll.setPreferredSize(new Dimension(700, 600));
		add(scroll);

		bt_ok = new JButton("확인");
		bt_ok.setBounds(720, 732, 100, 30);
		bt_ok.setFont(new Font("MD개성체", Font.PLAIN, 15));
		add(bt_ok);

		bt_delete = new JButton("주문 취소");
		bt_delete.setBounds(580, 730, 100, 30);
		bt_delete.setFont(new Font("MD개성체", Font.PLAIN, 15));
		add(bt_delete);

		bt_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (order_id == 0) {
					JOptionPane.showMessageDialog(main, "확인한 주문을 선택해 주세요!");
					return;
				}else{
		               changeCellColor();
	            }
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

		orderTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				row = orderTable.getSelectedRow();

				// table_no = (Integer) dayTable.getValueAt(row, 1);
				// member_id = (Integer) dayTable.getValueAt(row, 2);

				// 임시로 이거 나중에 지워야함
				order_id = 1;
			}
		});
		getOrderDetail();
	}
	public void changeCellColor() {
	      orderTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
	          public Component getTableCellRendererComponent(JTable table,
	                  Object value, boolean isSelected, boolean hasFocus, int row, int col) {

	              super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

	              String status = (String)table.getModel().getValueAt(row, 0);
	              
	              if ((Integer.toString(row)).equals(status)) {
	                  setBackground(Color.BLACK);
	                  setForeground(Color.WHITE);
	              }else {
	                  setBackground(table.getBackground());
	                  setForeground(table.getForeground());
	              }    
	              return this;
	          }   
	      });
	   } 
	public void updateTable() {
		orderTable.updateUI();
	}
    
	
	public void getOrderDetail() {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sb = new StringBuffer();
		sb.append("select t.table_info_id, m.menu_list_name, od.order_detail_count, os.order_summary_date");
		sb.append(" from table_info t, entry_list e, menu_list m, order_detail od, order_summary os");
		sb.append(" where t.table_info_id=e.table_info_id and e.entry_list_id=os.entry_list_id and os.order_summary_id=od.order_summary_id and od.menu_list_id=m.menu_list_id");

		
//		StringBuffer sb2 = new StringBuffer();
//	    sb2.append("select table_no,menu_list_name, menu_list_price,order_detail_count,order_summary_date");
//	    sb2.append(" from table_info t,entry_list e,order_detail od, menu_list m,order_summary os");
//	    sb2.append(" where od.menu_list_id=m.menu_list_id and where od.order_summary_id = os.order_summary_id and t.table_info_id=e.table_info_id");
		//System.out.println(sb.toString());
		try {
			pstmt = con.prepareStatement(sb.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();
			System.out.println("토탈은"+total);
			System.out.println("길이는"+orderTableModel.columnTitle.length);
			Object[][] data = new Object[total][orderTableModel.columnTitle.length];
			rs.beforeFirst();

			for (int i = 0; i < total; i++) {
				rs.next();
				data[i][0] = (i + 1);
				data[i][1] = rs.getString("table_info_id");
				data[i][2] = rs.getString("menu_list_name");
				data[i][3] = rs.getString("order_detail_count");
				data[i][4] = rs.getString("order_summary_date");
			}
			orderTableModel.data = data;// 교체!!

			orderTable.setModel(orderTableModel);
			orderTable.updateUI();
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

}
