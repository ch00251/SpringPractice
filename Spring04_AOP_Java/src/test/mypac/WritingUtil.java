package test.mypac;

import org.springframework.stereotype.Component;
/*
 *  [ bean �� �ȴ� ]
 *  
 *  - �ش� Ŭ������ ��ü�� �����̵ǰ� ������ bean �����̳ʿ��� ������ �ȴ�. 
 */

// @Component ������̼��� �ٿ� ������ ������Ʈ ������ ������ bean �� �ȴ�. 
@Component
public class WritingUtil {
	public void write1() {
		System.out.println("������ ���");
	}
	public void write2() {
		System.out.println("�ϱ⸦ ���");
	}
	public void write3() {
		System.out.println("�Ҽ��� ���");
	}
}