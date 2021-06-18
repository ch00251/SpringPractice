package test.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect // aspect ������ �Ҽ� �ֵ��� 
@Component //bean �� �ɼ� �ֵ���
public class PenAspect {
	/*
	 *  spring �� ���� �ϴ� ��ü�� �޼ҵ� �߿���
	 *  ���� type  �� void �޼ҵ���� write�� ����
	 *  �޼ҿ� ���޵Ǵ� ���ڴ� ���� �޼ҵ���
	 *  ���� ������ �� �۾� 
	 */
	@Before("execution(void write*())")
	public void prepare() {
		System.out.println("Pen �� �غ��ؿ�!");
	}
	@After("execution(void write*())")
	public void end() {
		System.out.println("Pen�� ������ �ؿ�!");
	}
}
