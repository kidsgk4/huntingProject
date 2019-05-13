package com.hunter.user.chat.hapseok;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.hunter.user.chat.client.ChatClient;

public class AskJoin extends JFrame{
	ChatClient chatClient;
	CardLayout cards = new CardLayout();
	
	public AskJoin(ChatClient chatClient) {
		this.chatClient = chatClient;
		getContentPane().setLayout(cards);
		setResizable(false);

		getContentPane().add("One", new CardPanelOne(this));
		getContentPane().add("Two", new CardPanelTwo(this));
		getContentPane().add("Three", new CardPanelThree(this));
		getContentPane().add("Four", new CardPanelFour(this));
		getContentPane().add("Five", new CardPanelFive(this));
		getContentPane().add("Six", new CardPanelSix(this));
				
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(400, 300));
		setVisible(true);
	}
	public CardLayout getCardLayout() {
		return cards;
	}
}
