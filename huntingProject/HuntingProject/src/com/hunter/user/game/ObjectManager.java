package com.hunter.user.game;

import java.util.ArrayList;

public class ObjectManager {
	ArrayList<GameObject> objectList; //�迭�� ������ �ٸ�����?1.ũ�⸦ ���X 2.���� ��ü������
	
	public ObjectManager() {
		objectList= new ArrayList();
	}
	
	//��ü ���
	public void addObject(GameObject obj) {
		objectList.add(obj);
	}
	
	//��ü ����
	public void removeObject(GameObject obj) {
		objectList.remove(obj);
	}
}
