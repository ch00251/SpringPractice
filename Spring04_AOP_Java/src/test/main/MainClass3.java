package test.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.mypac.Messenger;

public class MainClass3 {
	public static void main(String[] args) {
		// init.xml ������ �ε��ؼ� bean ���� ����͵��� ���� ���� �Ѵ�.
		ApplicationContext context=
			new ClassPathXmlApplicationContext("test/main/init.xml");
		Messenger m=context.getBean(Messenger.class);

		// getMessage() �� ȣ���ؼ� ���ϵǴ� �� ������ 
		String result=m.getMessage();

		System.out.println("result:"+result);
	}
}
