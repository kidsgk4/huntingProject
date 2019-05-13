/*
 * 스트링 처리 관련하여 기능을 모아놓은 클래스
 * */
package com.hunter.util;

public class StringUtil {
//확장자 추출하기!!
   public static String getExt(String path) {
      //가장 마지막 점의 index를 구한다
      int last=path.lastIndexOf(".");
      String ext=path.substring(last+1,path.length());
      return ext;
   }
   public static String getId(String member_phone) {
      //가장 마지막 점의 index를 구한다
      int last=member_phone.lastIndexOf("-");
      String member_id=member_phone.substring(last+1,member_phone.length());
      return member_id;
   }
   ///////////////////////////////수정//////////////////////////////////////////
   public static String getDate(String origin_date) {
	   String date=origin_date.substring(0,11);
	   return date;
   }
   //public static void main(String[] args) {
      //System.out.println(getExt("eeeeeeeeeee.jpg"));
   //}
}