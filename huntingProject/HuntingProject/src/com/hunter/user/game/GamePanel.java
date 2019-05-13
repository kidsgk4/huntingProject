package com.hunter.user.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	public static final int WIDTH = 1400;
	public static final int HEIGHT = 800;
	Image bgImg;
	ObjectManager objectManager;
	String bgPath = "C:/java_developer/javaSE/HuntingProject/res/background.jpg";
	String first = "C:/java_developer/javaSE/HuntingProject/res/b.png";
	String[] list = { "C:/java_developer/javaSE/HuntingProject/res/p1.png",
			"C:/java_developer/javaSE/HuntingProject/res/p2.png", "C:/java_developer/javaSE/HuntingProject/res/p3.png",
			"C:/java_developer/javaSE/HuntingProject/res/p4.png", "C:/java_developer/javaSE/HuntingProject/res/p5.png",
			"C:/java_developer/javaSE/HuntingProject/res/p6.png",
			"C:/java_developer/javaSE/HuntingProject/res/p7.png" };
	JPanel wrapper, p_south;
	Player p1;
	Player p2;
	Player p3;
	Player p4;
	Player p5;
	Player p6;
	Player p7;
	// Enemy changeEn1;
	// Object[] obj = new Object[7];
	ArrayList<Integer> array = new ArrayList<Integer>();
	int random, count;

	GameMain gameMain;

	// array 에 담긴 ( 1등,2등) 테이블을 DB에 담아줘야함 Connection 얻어와야함
	public GamePanel(GameMain gameMain) {
		this.gameMain = gameMain;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		ImageIcon bgIcon = new ImageIcon(bgPath);
		bgImg = bgIcon.getImage();
		objectManager = new ObjectManager();
		// p_south = new JPanel();

		createHero();
		repaint();
	}

	public void createHero() {

		int[] arr = new int[7];
		for (int a = 0; a < arr.length; a++) {
			int i = (int) (Math.random() * 7 + 3);
			arr[a] = i;
			if (a > 0) {
				for (int j = 0; j < a; j++) {
					if (arr[a] == arr[j]) {
						arr[a] -= 1;
					}
				}
			}
			System.out.println(arr[a]);
		}

		for (int i = 0; i < list.length; i++) {
			// Image img = imageLoader.getImage(filename[i]);
			ImageIcon icon = new ImageIcon(list[i]);
			Image img = icon.getImage();
			Image newImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			// ImageIcon newImc = new ImageIcon(newImg);
			Player player = new Player(ObjectType.Player1, objectManager, 0, i * 115, 105, 105, arr[i], 0, img);
			objectManager.addObject(player);
			repaint();
		}

	}

	public void tick() {
		for (int i = 0; i < objectManager.objectList.size(); i++) {
			GameObject obj = objectManager.objectList.get(i);
			obj.tick();
		}
	}

	public void paint(Graphics g) {
		g.drawImage(bgImg, 0, 0, WIDTH, HEIGHT, this);
		for (int i = 0; i < objectManager.objectList.size(); i++) {
			GameObject obj = objectManager.objectList.get(i);
			obj.render(g);
		}
	}

	public void changeImg(String path) {

		// changeEn1 = new Enemy(ObjectType.Enemy, objectManager, 0, 690, 100, 90, 0, 0,
		// changeImg1);
		System.out.println();
	}

	public void check() {
		for (int i = 0; i < objectManager.objectList.size(); i++) {
			if (objectManager.objectList.get(i).x >= 1295) {
				ImageIcon icon = new ImageIcon(first);
				Image img = icon.getImage();
				objectManager.objectList.get(i).img = img;
				objectManager.objectList.get(i).velX = 0;
				
				if (array.size() == 0) {
					array.add(i + 1);
					count++;
				} else {
					if (array.get(0) != i + 1) {
						array.add(i + 1);
						count++;
					}
				}
				repaint();
			}
		}
	}

	public void finishGame() {
		if (count >= 2) {
			bgPath = "C:/java_developer/javaSE/HuntingProject/res/over.png";
			ImageIcon bgIcon = new ImageIcon(bgPath);
			bgImg = bgIcon.getImage();
			
			ImageIcon icon = new ImageIcon("");
			Image img = icon.getImage();
			for (int i = 0; i < objectManager.objectList.size(); i++) {
				objectManager.objectList.get(i).img = img;
			}
			repaint();
			gameMain.flag = false;
			System.out.println("array에 들어잇는애 " + array.get(0));
			System.out.println("array에 들어잇는애 " + array.get(1));
			JOptionPane.showMessageDialog(gameMain, "끝남 일등:" + array.get(0) + " 이등:" + array.get(1));
			//System.exit(0);

		}
	}

}