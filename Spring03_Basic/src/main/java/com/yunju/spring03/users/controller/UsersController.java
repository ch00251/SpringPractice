package com.yunju.spring03.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {
	@RequestMapping("/users/loginform")
	public String loginform() {
		//������ ����Ͻ� ������ ����
		//request ������ ���� �𵨵� ����
		
		//view page�� ������ �ܼ��� ������ �ִ� ��쵵 �ִ�.
		return "users/loginform";
	}
}
