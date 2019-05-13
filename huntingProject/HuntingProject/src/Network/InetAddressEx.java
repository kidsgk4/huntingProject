package Network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressEx {

 public static void main(String[] args) throws UnknownHostException{
  
  //���� ȣ��Ʈ�� �̿��� InetAddress ��ü�� �����Ѵ�.
  InetAddress iaddr = InetAddress.getLocalHost();
  //ȣ��Ʈ �̸��� ���ڿ��� ��ȯ�Ѵ�.
  System.out.printf("ȣ��Ʈ �̸� : %s %n", iaddr.getHostName());
  //ȣ��Ʈ�� ���� IP�ּҸ� ��ȯ�Ѵ�.
  System.out.printf("ȣ��Ʈ IP �ּ� : %s %n", iaddr.getHostAddress());
  //"java.sun.com"�� �����ϴ�  InetAddress ��ü�� ��ȯ�Ѵ�.
  
  iaddr = InetAddress.getByName("java.sun.com");
  System.out.printf("ȣ��Ʈ �̸� : %s %n", iaddr.getHostName());
  System.out.printf("ȣ��Ʈ IP �ּ� : %s %n", iaddr.getHostAddress());
  
  //�Ű����� host�� �����ϴ� InetAddress �迭�� ��ȯ�Ѵ�.
  InetAddress sw[] = InetAddress.getAllByName("www.naver.com");
  for(InetAddress temp_sw : sw)
  {
   System.out.printf("ȣ��Ʈ �̸� : %s,", temp_sw.getHostName());
   System.out.printf("ȣ��Ʈ IP �ּ� : %s %n", temp_sw.getHostAddress());
   
  }
  
 }
 
}