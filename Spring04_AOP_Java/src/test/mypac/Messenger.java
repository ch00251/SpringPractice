package test.mypac;

import org.springframework.stereotype.Component;

@Component
public class Messenger {
	public void sendGreeting(String msg) {
		System.out.println("������ �λ�:"+msg);
	}
	public String getMessage() {
		return "������ ��������!";
	}
}
