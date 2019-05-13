package com.hunter.user.game;

import java.util.ArrayList;

public class ObjectManager {
	ArrayList<GameObject> objectList; //배열과 같지만 다른점은?1.크기를 명시X 2.오직 객체만담음
	
	public ObjectManager() {
		objectList= new ArrayList();
	}
	
	//객체 등록
	public void addObject(GameObject obj) {
		objectList.add(obj);
	}
	
	//객체 제거
	public void removeObject(GameObject obj) {
		objectList.remove(obj);
	}
}
