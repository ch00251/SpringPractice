package test.example3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.mypac.Car;
import test.mypac.Weapon;

public class MainClass {
	public static void main(String[] args) {
		//test.example3 ��Ű���� �ִ� init.xml ������ �ε��ؼ�
		//�ش� ������ ��õ� bean ������ �°� ��ü�� �����ϰ�
		//������ ��ü�� spring bean container���� �����ϱ�
		ApplicationContext context=
				new ClassPathXmlApplicationContext("test/example3/init.xml");
		//useWeapon() �޼ҵ带 ȣ���Ϸ���?
		
		//spring bean container���� �����Ǵ� ��ü�߿��� Weapon type�� ������ ��������
		Weapon w1=context.getBean(Weapon.class);
		useWeapon(w1);
		//useCar() �޼ҵ带 ȣ���Ϸ���?
		Car c1=context.getBean(Car.class);
		useCar(c1);
	}
	public static void useWeapon(Weapon w) {
		w.attack();
	}
	public static void useCar(Car c) {
		c.drive();
	}
}
