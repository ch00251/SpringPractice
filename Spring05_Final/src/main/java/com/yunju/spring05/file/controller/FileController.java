package com.yunju.spring05.file.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.file.dto.FileDto;
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
	
	//���� ���ε��� ��û ó��
	@RequestMapping("/file/upload_form")
	public ModelAndView authUploadForm(HttpServletRequest request) {
		return new ModelAndView("file/upload_form");
	}
	
	//���� ���ε� ��û ó��
	@RequestMapping(value="/file/upload", method=RequestMethod.POST)
	public ModelAndView authUpload(HttpServletRequest request,
					@ModelAttribute FileDto dto) {
		service.saveFile(request, dto);
		return new ModelAndView("redirect:/file/list.do");
	}
}
