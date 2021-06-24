package com.yunju.spring05.member.service;

import org.springframework.web.servlet.ModelAndView;

public interface MemberService {
	//회원목록 얻어오기 처리 메소드, mView는 dao를 이용해서 회원 목록을 담는 역할을 하면 됨
	public void getList(ModelAndView mView);
}
