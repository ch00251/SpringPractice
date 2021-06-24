package com.yunju.spring05.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {
	//회원가입 폼 요청처리
	@RequestMapping("/users/signupform")
	public String signupform() {
		return "users/signupform";
	}
}
