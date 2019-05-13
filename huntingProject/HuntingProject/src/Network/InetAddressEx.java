package Network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressEx {

 public static void main(String[] args) throws UnknownHostException{
  
  //로컬 호스트를 이용한 InetAddress 객체를 생성한다.
  InetAddress iaddr = InetAddress.getLocalHost();
  //호스트 이름을 문자열로 반환한다.
  System.out.printf("호스트 이름 : %s %n", iaddr.getHostName());
  //호스트에 대한 IP주소를 반환한다.
  System.out.printf("호스트 IP 주소 : %s %n", iaddr.getHostAddress());
  //"java.sun.com"에 대응하는  InetAddress 객체를 반환한다.
  
  iaddr = InetAddress.getByName("java.sun.com");
  System.out.printf("호스트 이름 : %s %n", iaddr.getHostName());
  System.out.printf("호스트 IP 주소 : %s %n", iaddr.getHostAddress());
  
  //매개변수 host데 대응하는 InetAddress 배열을 반환한다.
  InetAddress sw[] = InetAddress.getAllByName("www.naver.com");
  for(InetAddress temp_sw : sw)
  {
   System.out.printf("호스트 이름 : %s,", temp_sw.getHostName());
   System.out.printf("호스트 IP 주소 : %s %n", temp_sw.getHostAddress());
   
  }
  
 }
 
}