package com.hunter.admin;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Dispatcher {
	JSONParser jsonParser;
	ServerMain serverMain;
	Controller controller;
	Main main;

	public Dispatcher(Main main, ServerMain serverMain) {
		this.main = main;
		this.serverMain = serverMain;
		controller = new Controller(main,serverMain);
	}

	public void dispatch(String msg) {
		jsonParser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) jsonParser.parse(msg);
			String requestType = (String) obj.get("requestType");
			if (requestType.equals("order")) {
				int table_no = Integer.valueOf((String) obj.get("table_no"));
				int menu_list_id = Integer.valueOf((String) obj.get("menu_list_id"));
				String menu_list_name = (String) obj.get("menu_list_name");
				int order_detail_count = Integer.valueOf((String) obj.get("order_detail_count"));
				int entry_list_id = Integer.valueOf((String)obj.get("entry_list_id"));
				System.out.println("서버에 주문이 왔어요!\n");
				System.out.println(table_no);
				System.out.println(menu_list_id);
				System.out.println(menu_list_name);
				System.out.println(order_detail_count);
				System.out.println(entry_list_id);
				controller.getOrderData(table_no,menu_list_id,menu_list_name,order_detail_count,entry_list_id);
				
			} else if (requestType.equals("entry")) {
				//sql날릴때 entry_list_id는 seq.nextval로..  
				int user_list_id = Integer.valueOf((String) obj.get("user_list_id"));
				int gender_type_id = Integer.valueOf((String) obj.get("gender_type_id"));
				int entry_list_total = Integer.valueOf((String) obj.get("entry_list_total"));
				//date는 sysdate삽입
				System.out.println("로그인 정보를 받았어요\n");
				System.out.println(user_list_id+ " " +gender_type_id+" "+entry_list_total+"\n로그인 정보 받은거 긑");
				
				controller.insertEntry(user_list_id, gender_type_id, entry_list_total);
				
			}  else if (requestType.equals("checkid")) {
				String table_ip = (String) obj.get("table_ip");
				String member_list_phone = (String) obj.get("member_list_phone");
				System.out.println(member_list_phone+"폰넘버 받은거\n");
				
				controller.checkId(table_ip, member_list_phone);
				
			} else if(requestType.equals("join")) {
				String member_list_phone = (String) obj.get("member_list_phone");
				String member_list_pw = (String) obj.get("member_list_pw");
				int user_type_id = Integer.valueOf((String) obj.get("user_type_id"));
				
				controller.join(member_list_phone, member_list_pw,user_type_id);
				
			} else if(requestType.equals("login")) {
				String table_ip = (String) obj.get("table_ip");
				String member_list_phone = (String) obj.get("member_list_phone");
				String member_list_pw = (String) obj.get("member_list_pw");
				
				controller.login(table_ip,member_list_phone, member_list_pw);
				
			} else if(requestType.equals("guest")){
				int user_type_id = Integer.valueOf((String)obj.get("user_type_id"));
				String guest_list_name = (String) obj.get("guest_list_name");
				controller.insertGuest(user_type_id,guest_list_name);
				
			} else if(requestType.equals("bill")) {
				String table_ip = (String) obj.get("table_ip");
				int entry_list_id = Integer.valueOf((String)obj.get("entry_list_id"));
				
				controller.billUpdate(table_ip,entry_list_id);
			} else if(requestType.equals("getmemberid")) {
				String phone = (String) obj.get("phone");
				
				controller.getMemberId(phone);
			} else if(requestType.equals("playgame")) {
				String table_ip = (String) obj.get("table_ip");
				
				//controller.checkGame(table_ip);
			}
		} catch (ParseException e){
			e.printStackTrace();
		}
	}
}
