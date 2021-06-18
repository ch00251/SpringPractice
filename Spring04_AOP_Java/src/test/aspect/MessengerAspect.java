package test.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MessengerAspect {
	/*
	 * 	1. ���� type�� �������
	 *  2. �޼ҵ���� send�� �����ϴ� �޼ҵ�
	 *  3. �޼ҵ忡 ���޵Ǵ� ���ڵ� ��� ����
	 *  
	 *  ���� 3���� ������ ��� ������Ű�� �޼ҵ忡 �Ʒ��� aop�� ����ȴ�.
	 */
	@Around("execution(* send*(..))")
	public void around(ProceedingJoinPoint joinPoint) 
					throws Throwable {
		//aop�� ����� �޼ҵ� ���� ����
		System.out.println("-- ���� ���� --");
		
		//aop�� ����� �޼ҵ忡 ���޵� ���ڸ� Object[]�� �� �� �ִ�.
		Object[] args=joinPoint.getArgs();
		//�ݺ��� ���鼭 ã�� ���� type�� ã�´�
		for(Object tmp:args) {
			if(tmp instanceof String) {//���� String type�̸�
				//���� type���� casting
				String msg=(String)tmp;
				System.out.println("aop���� �о ����:"+msg);
				if(msg.contains("�ٺ�")) {
					System.out.println("�ٺ���� �ϱ� ����!");
					return;//�޼ҵ带 ���⼭ ���� ��Ų��
				}
			}
		}
		
		//aop�� ����� �޼ҵ� �����ϰ� ���ϵǴ°� �޾ƿ���(void�� null�̴�)
		Object obj=joinPoint.proceed();
		
		//aop�� ����� �޼ҵ� ���� ����
		System.out.println("-- ���� ���� --");
	}
	
	@Around("execution(String getMessage())")
	public Object around2(ProceedingJoinPoint joinPoint) 
			throws Throwable {
		// aop �� ����� �޼ҵ带 �����ϰ� ���ϵǴ� ���� ����.
		Object obj=joinPoint.proceed();
		// ������ ���� �����ϱ� 
		obj = "�� ���ξ� �׳� ����~~";
		// ���۵� ���� �ٽ� �������ֱ� 
		return obj;
	}
}
