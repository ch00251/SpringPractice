package com.yunju.spring03;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
/*
 * 	@Controller ������̼�
 *  - �ش� Ŭ������ Spring MVC ������Ʈ���� Controller�� �� �� �ֵ��� �Ѵ�
 *  - component scan�� ���ؼ� spring bean container���� ������ �Ǵ� bean�� �Ǿ�� ������ �Ѵ�
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class HomeController{
	// /home.do ��û�� ���� �� ��û�� ó���ϰ� �ϴ� @RequestMapping ������̼�
	@RequestMapping("/home.do")
	public String home(HttpServletRequest request) {
		//��
		List<String> notice=new ArrayList<>();
		notice.add("��������");
		notice.add("�ڷγ� ����");
		notice.add("�ٵ� ��� ���ƿ�~");
		notice.add("��¼��...");
		notice.add("��¼��...");
		
		//���� request�� ��´�.
		request.setAttribute("notice", notice);
		
		/*
		 *  "home"�� �������ָ�
		 *  "/WEB-INF/views/"+"home"+".jsp"�� ���� ���ڿ��� ����� ����
		 *  /WEB-INF/views/home.jsp �������� forward �̵� �Ǿ
		 *  ����ȴ�.
		 */
		return "home";
	}
	
	@RequestMapping("/play")
	public ModelAndView play(HttpSession session,
				ModelAndView mView) {
		//���ǿ� �α��� ������ �ִ��� Ȯ���Ѵ�.
		String id=(String)session.getAttribute("id");
		if(id==null) {
			//�α��ε��� ���� �����̸� �α��� ������ �����Ϸ�Ʈ
			mView.setViewName("redirect:/users/loginform.do");
		}
		return mView;
	}
}