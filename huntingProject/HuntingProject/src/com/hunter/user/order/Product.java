package com.hunter.user.order;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Product extends JPanel {
	Canvas can;
	ImageIcon icon;
	Image img;
	JLabel la_name, la_price;
	String path;
	int product_id;
	String name;
	String price;
	OrderList orderList;
	Bag bag;
	int table_no;
	public Product(OrderList orderList, Bag bag, String path, int product_id, String name, String price) {
		this.orderList = orderList;
		this.bag = bag;
		this.path = path;
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		//this.bag.orderMain.table_no;
		this.table_no = bag.orderMain.user.getTableNum();
		icon = new ImageIcon(path);
		img = icon.getImage();

		can = new Canvas() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, 200, 180, this);
			}
		};

		can.setPreferredSize(new Dimension(200, 180));
		la_name = new JLabel(name, SwingConstants.CENTER);
		la_price = new JLabel(price, SwingConstants.CENTER);

		/*
		 * ch.addItemListener(new ItemListener() { public void
		 * itemStateChanged(ItemEvent e) { if (ch.getState()) {
		 * orderList.cartList.add(product_id); } else {
		 * orderList.cartList.remove(product_id); }
		 * System.out.println("장바구니에 담겨질 상품의 갯수는 " + orderList.cartList.size()); } });
		 */
		la_name.setFont(new Font("MD개성체", Font.PLAIN, 30));
		la_price.setFont(new Font("MD개성체", Font.PLAIN, 40));
		la_name.setPreferredSize(new Dimension(200, 70)); // 각각 사이즈정해주기
		la_price.setPreferredSize(new Dimension(155, 70)); // 각각 사이즈정해주기
		this.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new LineBorder(Color.BLACK)));

		add(can);
		add(la_name);
		add(la_price);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null, product_id+"를 장바구니에 담으시겠어요?");
				if(result == JOptionPane.OK_OPTION) {
					System.out.println("패널 클릭했을때"+table_no);
					addCart(product_id);
				}
			}		
		});
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(220, 200));
	}
		
	//장바구니에 담기!!
	public void addCart(int product_id) {
		int cart_id=orderList.dupulicateCheck(table_no,product_id);//중복 여부 체크
		if(cart_id!=0){
			JOptionPane.showMessageDialog(this, "수량을 증가시켰습니다!");
			orderList.updateEa(cart_id);
		}else {
			orderList.goCart(table_no, product_id);
			//int result2=orderList.goCart(table_no,product_id);
			System.out.println("장바구니에 담기전 최종확인! 테이블 넘버는 "+ table_no);
			if(cart_id==0) {
				JOptionPane.showMessageDialog(this, "장바구니에 담았습니다");
				System.out.println("장바구니에 담았습니다에서 !!"+table_no);
			}else {
				JOptionPane.showMessageDialog(this, "장바구니 담기 실패");
			}
		}		
		bag.getCartList();
		bag.getSum();
		bag.la_money.setText(Integer.toString(bag.sum));
		System.out.println(bag.getSum());
	}
}



