package test.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.mypac.Messenger;

public class MainClass2 {
	public static void main(String[] args) {
		// init.xml ������ �ε��ؼ� bean ���� ����͵��� ���� ���� �Ѵ�.
		ApplicationContext context=
			new ClassPathXmlApplicationContext("test/main/init.xml");
		Messenger m=context.getBean(Messenger.class);
		m.sendGreeting("���� ��ħ~");
		m.sendGreeting("�ٺ���! ���� ��ħ~");
	}
}
