package com.yunju.spring03.users.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {
	
	//�α��� �� ��û ó��
	@RequestMapping("/users/loginform")
	public String loginform() {
		//������ ����Ͻ� ������ ����
		//request ������ ���� �𵨵� ����
		
		//view page�� ������ �ܼ��� ������ �ִ� ��쵵 �ִ�.
		return "users/loginform";
	}
	//�α��� ��û ó��
	@RequestMapping("/users/login")
	public String login(HttpServletRequest request, HttpSession session) {
		//�� ���۵Ǵ� �Ķ���͸� �����Ѵ�
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		//��ȿ�� �������� ����
		boolean isSuccess=false;
		if(id.equals("gura")&&pwd.equals("1234")) {
			isSuccess=true;
			//�α��� ó���� �Ѵ�
			session.setAttribute("id", id);
		}
		//�α��� ���� ���θ� request�� ��´�
		request.setAttribute("isSuccess", isSuccess);
		//view page�� ������ �����Ѵ�
		return "users/login";
	}
	
}
