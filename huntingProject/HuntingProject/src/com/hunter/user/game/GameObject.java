package com.hunter.user.game;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class GameObject {
	// ������Ʈ�� Ÿ�Ե� ���� ����...
	ObjectType type;//������� ��Ƴ��� ����
	ObjectManager objectManager;
	int x;
	int y;
	int width;
	int height;
	int velX;
	int velY;
	Image img;
	Rectangle rect;

	
	public GameObject(ObjectType type,ObjectManager objectManager,int x,int y,int width,int height,int velX,int velY,Image img) {
		this.type=type;
		this.objectManager=objectManager;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.velX=velX;
		this.velY=velY;
		this.img=img;
		rect = new Rectangle(x, y, width, height);
	}

	public abstract void tick();

	public abstract void render(Graphics g);
	
	public void setRectBounds() {
		rect.setBounds(x, y, width, height);
		
	}
	
	public void showOutline(Graphics g) {
		//�׵θ� �׸���
		g.setColor(Color.RED);
		//g.drawRect(x, y, width, height);
		//g.drawImage(img, 0, 0, width, height, null);
	}
}
