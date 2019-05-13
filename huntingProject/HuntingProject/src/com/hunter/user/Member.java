package com.hunter.user;

import java.util.Date;

//로그인시 값이 부여됨..
public class Member {
	User user;
	
	private int memberId = 0; // 멤버테이블 pk
	private int type = 2; // 회원타입 디폴트로 2줌
	private String id; // 아이디
	private String phone; // 전화번호
	private String pw; // 비밀번호
	private Date date; // 등록날짜 sysdate

	public Member(User user) {
		this.user=user;
	}
	//멤버로 입장한 경우
	public void addMember() {
		memberId++;
		user.setFlag(true);
	}

	// 멤버테이블 pk얻기
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	// 회원타입
	public int getType() {
		return type;
	}

	// 아이디
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// 전화
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	// 비밀번호
	public String getPw() {
		return pw;
	}

	public void setPwe(String pw) {
		this.pw = pw;
	}

	// 비밀번호
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
