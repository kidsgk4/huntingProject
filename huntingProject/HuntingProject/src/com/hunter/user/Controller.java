package com.hunter.user;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import com.hunter.user.home.BillModel;
import com.hunter.user.regist.RegistMain;

public class Controller {
	UserMain userMain;
	BillModel billModel;
	public Controller(UserMain userMain) {
		this.userMain = userMain;
		
		// TODO Auto-generated constructor stub
	}
	//���� ���� ����
	public void setUserInfo(int table_info_id,String table_ip,int entry_list_id) {
		System.out.println("userinfo����\n");
		userMain.user.setTableNum(table_info_id);
		userMain.user.setTableIp(table_ip);
		userMain.user.setEntryListId(entry_list_id);
		System.out.println("��Ʈ�ѷ�����"+table_info_id);
		System.out.println("��Ʈ�ѷ�����"+table_ip);
		
	}
	
	public void setCheckIdBack(String check) {
		if(check.equals("true")) {
			JOptionPane.showMessageDialog(userMain,"��� ������ ID�Դϴ�!!" );
			userMain.joinMain.check=true;
		}else {
			JOptionPane.showMessageDialog(userMain,"�ߺ��� ID�Դϴ�!!" );
		}
	}
	public void setCheckLoginBack(String check) {
		if(check.equals("true")) {
			JOptionPane.showMessageDialog(userMain,"�α��� ����" );
			
			userMain.loginMain.registMain = new RegistMain(userMain,userMain.user);
		}else {
			JOptionPane.showMessageDialog(userMain,"�α��� ����" );
		}
	}
	public void setOrderSummaryId(int order_summary_id) {
		System.out.println("��Ʈ�ѷ����� ���Ӹ����̵�"+order_summary_id);
		userMain.user.setOrderSummaryId(order_summary_id);
	}
	public void setTableIp(String table_ip) {
		userMain.user.setTableIp(table_ip);
	}
	
	public void setBillBack(String menu_list_name,String menu_list_price,int order_detail_count,int row) {
		String[][] data = new String[row][4];
		userMain.homeMain.getBillMain().row=row;
		
		int sum=0;
		for (int i = 0; i < row; i++) {
			data[i][0] = (Integer.toString(i+1));
			data[i][1] = menu_list_name;
			data[i][2] = menu_list_price;
			data[i][3] = (Integer.toString(order_detail_count));
			
			sum+=Integer.parseInt(menu_list_price)*order_detail_count;
			userMain.homeMain.getBillMain().sum=sum;
		
		}
		//billModel=new BillModel();
		userMain.homeMain.getBillMain().billModel.data=data;
		//userMain.homeMain.getBillMain().table.setModel(billModel);
	}
	public void setMemberId(int memberId) {
		userMain.user.setMemberId(memberId);
		System.out.println("��Ʈ�ѷ������� ������̵��"+memberId);
	}
	public void setGuestListId(int guest_list_id) {
		userMain.user.setGuestListId(guest_list_id);
		System.out.println("��Ʈ�ѷ������� �Խ�Ʈ����Ʈ���̵�� "+guest_list_id);
	}
}
