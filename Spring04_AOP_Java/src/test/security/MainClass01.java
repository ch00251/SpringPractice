package test.security;

import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MainClass01 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.print("���ڿ� �Է�:");
		String str=scan.nextLine();
		//���ڿ��� ��ȣȭ ���ִ� ��ü ����
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		//�Է��� ���ڿ��� ��ȣȭ �Ѵ�
		String result=encoder.encode(str);
		System.out.println("�Է��� ����:"+str);
		System.out.println("��ȣȭ�� ����:"+result);
	}
}
