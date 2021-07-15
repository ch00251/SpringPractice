package com.yunju.spring05.aspect;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Aspect
@Component
public class LoginAspect {
	@Around("execution(org.springframework.web.servlet.ModelAndView auth*(..))")
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		//aop �� ����� �޼ҵ忡 ���޵� ���� Object[] �� ������
		Object[] args=joinPoint.getArgs();
		//�α��� ����
		boolean isLogin=false;
		HttpServletRequest request=null;
		for(Object tmp:args) {
			//���ڷ� ���޵� ���߿� HttpServletRequest type �� ã�Ƽ�
			if(tmp instanceof HttpServletRequest) {
				//���� type ���� casting
				request=(HttpServletRequest)tmp;
				//HttpSession ��ü ���� 
				HttpSession session=request.getSession();
				//���ǿ� "id" ��� Ű������ ����Ȱ� �ִ��� Ȯ��(�α��� ����)
				if(session.getAttribute("id") != null) {
					isLogin=true;
				}
			}
		}
		//�α��� �ߴ��� ����
		if(isLogin){
			// aop �� ����� �޼ҵ带 �����ϰ� 
			Object obj=joinPoint.proceed();
			// ���ϵǴ� ���� ������ �ֱ� 
			return obj;
		}
		//���� ������ url ���� �о���� 
		String url=request.getRequestURI();
		//GET ��� ���� �Ķ���͸� query string ���� ������
		String query=request.getQueryString();

		String encodedUrl=null;
		if(query==null) {//���޵� �Ķ���Ͱ� ���ٸ� 
			encodedUrl=URLEncoder.encode(url);
		}else {
			encodedUrl=URLEncoder.encode(url+"?"+query);
		}
		//ModelAndView ��ü�� �����ؼ� 	
		ModelAndView mView=new ModelAndView();
		//�α��� ������ �����Ϸ�Ʈ ��Ű���� view page ����
		mView.setViewName
		("redirect:/users/loginform.do?url="+encodedUrl);

		//���⼭ ������ ��ü�� ������ �ش�. 
		return mView;		
	}
	
	@Around("execution(java.util.Map auth*(..))")
	public Object loginCheckAjax(ProceedingJoinPoint joinPoint) throws Throwable{
		//aop�� ����� �޼ҵ忡 ���޵� ���� Obejct[]�� ������
		Object[] args=joinPoint.getArgs();
		//�α��� ����
		boolean isLogin=false;
		HttpServletRequest request=null;
		for(Object tmp:args) {
			//���ڷ� ���޵� ���߿� HttpServletRequest type�� ã�Ƽ�
			if(tmp instanceof HttpServletRequest) {
				//���� type���� casting
				request=(HttpServletRequest)tmp;
				//HttpSession��ü ����
				HttpSession session=request.getSession();
				//���ǿ� "id"��� Ű������ ����Ȱ� �ִ��� Ȯ��(�α��� ����)
				if(session.getAttribute("id")!=null) {
					isLogin=true;
				}
			}
		}
		//�α��� �ߴ��� ����
		if(isLogin) {
			// aop�� ����� �޼ҵ带 �����ϰ�
			Object obj=joinPoint.proceed();
			//���ϵǴ� ���� �������ֱ�
			return obj;
		}
		//�α����� ���� �ʾ�����
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isSuccess", false);
		return map;
	}
}
