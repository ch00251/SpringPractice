package com.yunju.spring05.users.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.users.dto.UsersDto;
import com.yunju.spring05.users.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService service;
	//ȸ������ �� ��ûó��
	@RequestMapping("/users/signupform")
	public String signupform() {
		return "users/signupform";
	}
	/*
	 *  [ JSON ���ڿ� �����ϴ� ��� ]
	 *  1. pom.xml �� jackson-databind dependency ���
	 *  2. controller �� �޼ҵ忡 @ResponseBody ������̼� ���̱�
	 *  3. List, Map, Dto �߿� �ϳ��� �����Ѵ�.
	 */
	@ResponseBody
	@RequestMapping("/users/checkid")
	public Map<String, Object> checkid(@RequestParam String inputId){
		Map<String, Object> map=service.isExistId(inputId);
		return map;
	}
	//POST��� /users/signup.do ��ûó��
	@RequestMapping(value="/users/signup", method=RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute("dto") UsersDto dto,
				ModelAndView mView) {
		service.addUser(dto);
		mView.setViewName("users/insert");
		return mView;
	}
}
