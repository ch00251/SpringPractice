package com.yunju.spring05.cafe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.cafe.service.CafeService;

@Controller
public class CafeController {
	@Autowired
	private CafeService service;
	
	//�۸�� ��ûó��
	@RequestMapping("/cafe/list")
	public ModelAndView list(HttpServletRequest request) {
		//HttpServletRequest ��ü�� ���񽺿� �Ѱ��ָ鼭
		//����Ͻ� ������ �����ϰ�
		service.getList(request);
		
		//view page�� forward �̵��ؼ� �� ��� ����ϱ�
		return new ModelAndView("cafe/list");
	}
}
