package com.yunju.spring02;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//1. Ŭ������ ����� Controller ������̼��� �ٿ��ش�
@Controller
public class FortuneController {
	//2. RequestMapping ������̼��� �޼ҵ忡 �ۼ��ϰ� � ��û�� ó������ ����Ѵ�
	@RequestMapping("/fortune.do")
	public String fortune(HttpServletRequest request) {//�޼ҵ���� ������� ���� �� �ִ�
		/*
		 *	view page�� ��(data)�� �����ؾ� �Ѵ�
		 *	���⼭ ���� ������ �(String)�̴�
		 *	HttpServletRequest�� ������ view page���� ����� �� �ִ� 
		 */
		request.setAttribute("fortuneToday", "�������� ���� ������ ������");
		
		//3. view page ������ �������ش�
		return "fortune";
	}
}
