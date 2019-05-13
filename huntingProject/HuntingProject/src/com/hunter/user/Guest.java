package com.hunter.user;

public class Guest {
	User user;
	
	private int guestId=0; // 게스트테이블 pk
	private int type = 1; // 회원타입 //db에 디폴트를 1로 줌
	
	public Guest(User user) {
		this.user=user;
	}
	//게스트로 입장한 경우
	public void addGuest() {
		guestId++;
		user.setFlag(false);
	}
	// 게스트테이블 pk얻기
	public int getGuestId() {
		return guestId;
	}

	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}

	// 회원타입
	public int getType() {
		return type;
	}
	
}
