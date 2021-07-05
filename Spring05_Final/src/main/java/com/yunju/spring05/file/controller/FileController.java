package com.yunju.spring05.file.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.file.service.FileService;

@Controller
public class FileController {
	@Autowired
	private FileService service;
	
	//���� ��� ���� ��ûó��
	@RequestMapping("/file/list")
	public ModelAndView list(ModelAndView mView, HttpServletRequest request) {
		//���� ��ϰ� ����¡ ó���� �ʿ��� ������ request�� ����ִ� ���� �޼ҵ� ȣ���ϱ�
		service.list(request);
		
		mView.setViewName("file/list");
		return mView;
	}
}
