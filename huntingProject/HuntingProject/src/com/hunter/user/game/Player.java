package com.hunter.user.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Player extends GameObject {
	public Player(ObjectType type,ObjectManager objectManager,int x,int y,int width,int height,int velX,int velY,Image img) {
		super(type,objectManager,x,y,width,height,velX,velY,img);
		//사각형을 생성한다!! 충돌검사를 위해서
	}
	
	//jdk1.5부터 지원하기 시작함..역할 : 주석과 비슷하지만
	//일반적인 주석은 프로그램 실행시 사용되지 않고 무시되지만, 어노테이션 주석은 
	//프로그램 실행시 사용된다
	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		setRectBounds();
	}

	//아래 메서드에서의 g는 GamePanel의 g다 따라서 플레이어가 패널에 그려진다!!
	@Override
	public void render(Graphics g) {
		g.drawImage(img,x,y,width,height, null);
		//테두리 만들기
		showOutline(g);
	}

}
