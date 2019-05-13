package com.hunter.user.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Player extends GameObject {
	public Player(ObjectType type,ObjectManager objectManager,int x,int y,int width,int height,int velX,int velY,Image img) {
		super(type,objectManager,x,y,width,height,velX,velY,img);
		//�簢���� �����Ѵ�!! �浹�˻縦 ���ؼ�
	}
	
	//jdk1.5���� �����ϱ� ������..���� : �ּ��� ���������
	//�Ϲ����� �ּ��� ���α׷� ����� ������ �ʰ� ���õ�����, ������̼� �ּ��� 
	//���α׷� ����� ���ȴ�
	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		setRectBounds();
	}

	//�Ʒ� �޼��忡���� g�� GamePanel�� g�� ���� �÷��̾ �гο� �׷�����!!
	@Override
	public void render(Graphics g) {
		g.drawImage(img,x,y,width,height, null);
		//�׵θ� �����
		showOutline(g);
	}

}
