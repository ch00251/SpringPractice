package com.yunju.spring03.todo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping("/todo/list2") //.do�� ��������
	public ModelAndView list2() {
		//Model
		List<String> todoList=new ArrayList<String>();
		todoList.add("html �����ϱ�");
		todoList.add("css  �����ϱ�");
		todoList.add("javascript �����ϱ�");
		//Model�� view page ������ ���� �� �ִ� ��ü ����
		ModelAndView mView=new ModelAndView();
		//ModelAndView��ü�� .addObject(key, value)�� ���� ��ü��
		//�ڵ����� request ��ü�� ����.
		mView.addObject("todoList", todoList);
		//view page ������ .setViewName() �޼ҵ带 �̿��ؼ� ��´�.
		mView.setViewName("todo/list");
		//Model�� view page������ ��� ��ü�� �������ش�.
		return mView;
	}
	
	//�޼ҵ��� ���ڷ� ModelAndView ��ü�� ���� �� �ִ� ������ �����ϸ�
	//������ �����ӿ�ũ�� �ش簴ü�� �����ؼ� ���ڷ� �������ش�.
	@RequestMapping("/todo/list3")
	public ModelAndView list3(ModelAndView mView) {
		//Model
		List<String> todoList=new ArrayList<String>();
		todoList.add("html �����ϱ�");
		todoList.add("css  �����ϱ�");
		todoList.add("javascript �����ϱ�");
		//���ڷ� ���� ���� ��ü�� Model�� ���
		mView.addObject("todoList", todoList);
		//���ڷ� ���� ���� ��ü�� view page�� ������ ���
		mView.setViewName("/todo/list");
		//���ڷ� ���� ���� ��ü�� �������� �������ش�.
		return mView;
	}
}
