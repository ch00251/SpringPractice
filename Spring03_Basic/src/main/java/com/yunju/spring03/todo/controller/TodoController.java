package com.yunju.spring03.todo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//1. Controller ������̼�
@Controller
public class TodoController {
	//2. RequestMapping ������̼����� ��û �����ϱ�
	@RequestMapping("/todo/list.do")
	public String list(HttpServletRequest request) {
		//3. ���� ����� ������ ����Ͻ� ���� ó���ϱ�
		List<String> todoList=new ArrayList<String>();
		todoList.add("html �����ϱ�");
		todoList.add("css �����ϱ�");
		todoList.add("javascript �����ϱ�");
		//4. ����Ͻ� ���� ó�� ��� ���� request�� ���
		request.setAttribute("todoList", todoList);
		//5. view page�� ������ �����ϸ� �ش��������� forward�̵��ؼ� ������ �ȴ�
		return "todo/list";
	}
}
