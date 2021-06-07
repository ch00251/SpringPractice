package test.example2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.mypac.Weapon;

public class MainClass {
	public static void main(String[] args) {
		//init.xml ������ �ؼ��ؼ� bean�� �����Ѵ�
		ApplicationContext context=
				new ClassPathXmlApplicationContext("test/example2/init.xml");
		//�������� �����ϰ� �ִ� ��ü�߿��� "myWeapon"�̶�� �̸��� ��ü��
		//�������� ������ �ͼ� Weapon type���� casting �ؼ� ������ ��´�.
		Weapon w1=(Weapon)context.getBean("myWeapon");
		//Weapon type ��ü�� �̿��ؼ� ���ϴ� ������ �Ѵ�
		useWeapon(w1);
	}
	public static void useWeapon(Weapon w) {
		w.attack();
	}
}
