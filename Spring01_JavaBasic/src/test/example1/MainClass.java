package test.example1;

import test.mypac.Weapon;
import test.mypac.WeaponImpl;

public class MainClass {
	public static void main(String[] args) {
		//useWeapon()�޼ҵ带 ȣ���ϴ°� �����̶��?
		
		//�ʿ��� type��ü�� ���� �����ؼ�
		WeaponImpl w1=new WeaponImpl();
		//�޼ҵ带 ȣ�������ν� ������ �޼��Ѵ�
		useWeapon(w1);
	}
	
	//Weapon(�������̽�)type�� �����ؾ� ȣ���Ҽ� �ִ� �޼ҵ�
	public static void useWeapon(Weapon w) {
		w.attack();
	}
}
