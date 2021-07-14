package com.yunju.spring05.cafe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.cafe.dto.CafeDto;
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
	//���� �߰� �� ��ûó��
	@RequestMapping("/cafe/insertform")
	public ModelAndView authInsertform(HttpServletRequest request) {
		return new ModelAndView("cafe/insertform");
	}
	
	//���� �߰� ��ûó��
	@RequestMapping(value="/cafe/insert", method=RequestMethod.POST)
	public ModelAndView authInsert(HttpServletRequest request,
			 		@ModelAttribute CafeDto dto) {
		//���ǿ� �ִ� ���ۼ����� ���̵�
		String writer=(String)request.getSession().getAttribute("id");
		//CafeDto ��ü�� ���
		dto.setWriter(writer);
		//���񽺸� �̿��ؼ� DB�� ����
		service.saveContent(dto);
		//�� ��Ϻ���� �����Ϸ�Ʈ �̵�
		return new ModelAndView("redirect:/cafe/list.do");
	}
}
