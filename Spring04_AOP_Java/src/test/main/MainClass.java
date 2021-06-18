package test.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.mypac.WritingUtil;

public class MainClass {
	public static void main(String[] args) {
		// init.xml ������ �ε��ؼ� bean ���� ����͵��� ���� ���� �Ѵ�.
		ApplicationContext context=
			new ClassPathXmlApplicationContext("test/main/init.xml");
		// �����ϰ� �ִ� ��ü�߿��� WritingUtil type �� ������ ������ 
		WritingUtil util=context.getBean(WritingUtil.class);

		util.write1();
		util.write2();
		util.write3();
	}
}
