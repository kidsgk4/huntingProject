package com.hunter.user;

import java.util.Date;

//��Ͻ� ���� �ο���..
public class User {
	String TAG = this.getClass().getName();
	private Member member; // ���� �� ����� ����
	private Guest guest; // ���� �� �Խ�Ʈ�� ����

	private boolean flag = true; // ������ �������, �Խ�Ʈ���� �˾ƺ��� ����
	private int userId = 0; // �������̺� pk
	private int tableNum; // ���̺� ��ȣ //�����Ұ� �ƴϴϱ� string
	private int gender; // ����
	private int total; // �� �ο���
	private Date date; // ���� ��¥ //����Ʈ �ý�����Ʈ
	// ip�� ���� �߰�
	private String tableIp;
	private int entry_list_id;
	private String checkId;
	private int order_summary_id;
	private int memberId;
	private int guest_list_id;
	public User() {
		System.out.println(TAG + " User.java�� �޸𸮿� �ö󰬴�.");
		System.out.println(TAG+" ,"+this.getTableNum());
		// ���� �� ����� �Խ�Ʈ �޸𸮿� �ø���
		guest = new Guest(this);
		member = new Member(this);
		setUserId(userId++);
	}

	// �������
	public Member getMemeber() {
		return member;
	}

	// �Խ�Ʈ����
	public Guest getGuest() {
		return guest;
	}

	// �÷��� �� ���
	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	// �������̺� pk���
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	// ���̺�
	public int getTableNum() {
		return tableNum;
	}

	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}

	// ����
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	// �� �ο�
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	// ��¥
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	// tableIp
	public String getTableIp() {
		return tableIp;
	}

	public void setTableIp(String tableIp) {
		this.tableIp = tableIp;
	}

	public int getEntryListId() {
		return entry_list_id;
	}

	public void setEntryListId(int entry_list_id) {
		this.entry_list_id = entry_list_id;
	}
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	
	public int getOrderSummaryId() {
		return order_summary_id;
	}
	public void setOrderSummaryId(int order_summary_id) {
		this.order_summary_id = order_summary_id;
	}
	public int getMemberId() {
		System.out.println("getter�� memberId��"+memberId);
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
		System.out.println("this.memberId��"+this.memberId);
	}
	public int getGuestListId() {
		return guest_list_id;
	}
	public void setGuestListId(int guest_list_id) {
	this.guest_list_id = guest_list_id;
	}
}