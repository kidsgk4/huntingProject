package com.hunter.user;



import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Dispatcher {
	JSONParser jsonParser;
	ClientMain clientMain;
	Controller controller;
	UserMain main;
	public Dispatcher(UserMain main,ClientMain clientMain) {
		this.main = main;
		this.clientMain = clientMain;
		controller =new Controller(main);
	}
	
	public void dispatch(String msg) {
		jsonParser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) jsonParser.parse(msg);
			String requestType = (String) obj.get("requestType");
			if(requestType.equals("entryinfo")) {
				int table_info_id = Integer.valueOf((String)obj.get("table_info_id"));
				String table_ip = (String) obj.get("table_ip");
				int entry_list_id = Integer.valueOf((String)obj.get("entry_list_id"));

				controller.setUserInfo(table_info_id,table_ip, entry_list_id);
				
			} else if(requestType.equals("checkidback")) {
				String idCheck = (String)obj.get("idCheck");
				
				controller.setCheckIdBack(idCheck);
			} else if(requestType.equals("checkloginback")) {
				String loginCheck = (String)obj.get("check");
				
				controller.setCheckLoginBack(loginCheck);
			}  else if(requestType.equals("sendip")) {
				String table_ip = (String)obj.get("table_ip");
				
				controller.setTableIp(table_ip);
			} else if(requestType.equals("sendordersummaryback")) {
				int order_summary_id = Integer.valueOf((String)obj.get("order_summary_id"));
				System.out.println("샌드오더서머리 백 왔다!\n");
				controller.setOrderSummaryId(order_summary_id);
			}
			else if(requestType.equals("billback")) {
				String menu_list_name = (String)obj.get("menu_list_name");
				String menu_list_price = (String)obj.get("menu_list_price");
				int order_detail_count = Integer.valueOf((String)obj.get("order_detail_count"));
				int row = Integer.valueOf((String)obj.get("row"));
				System.out.println("샌드오더서머리 백 왔다!\n");
				
				controller.setBillBack(menu_list_name,menu_list_price,order_detail_count,row);
			}
			else if(requestType.equals("memberidback")) {
				int memberId = Integer.valueOf((String)obj.get("memberId"));
				
				controller.setMemberId(memberId);
				System.out.println("처음 받아올때"+memberId);
			}
			else if(requestType.equals("guestlistidback")) {
				int guest_list_id = Integer.valueOf((String)obj.get("guest_list_id"));
				
				controller.setGuestListId(guest_list_id);
				System.out.println("처음 받아온 gueset_list_id는"+guest_list_id);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
