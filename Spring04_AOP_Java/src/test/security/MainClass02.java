package test.security;

import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MainClass02 {
	public static void main(String[] args) {
		//������ �Է��� ��й�ȣ��� ����
		String lastPwd="1234";
		//�Է��� ��й�ȣ�� ��ȣȭ �ؼ� ����� ��й�ȣ��� ����
		String endcodedPwd=new BCryptPasswordEncoder().encode(lastPwd);
		
		Scanner scan=new Scanner(System.in);
		System.out.print("��й�ȣ �Է�:");
		String pwd=scan.nextLine();
		
		//��й�ȣ ��ġ ���θ� BCryptŬ������ static �޼ҵ带 �̿��ؼ� ����
		boolean isValid=BCrypt.checkpw(pwd, endcodedPwd);
		
		if(isValid) {
			System.out.println("��й�ȣ�� ��ġ�ؿ�");
		}else {
			System.out.println("��й�ȣ�� Ʋ����");
		}
	}
}
