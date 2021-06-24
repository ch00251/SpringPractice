package com.yunju.spring05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.member.dao.MemberDao;
import com.yunju.spring05.member.dto.MemberDto;
import com.yunju.spring05.member.service.MemberService;

@Controller
public class MemberController {
	//의존 객체 주입 받기(DI)
	@Autowired
	private MemberService service;
	
	//회원 목록 보기 요청(/member/list.do)을 처리할 컨트롤러의 메소드
	@RequestMapping("/member/list")
	public ModelAndView list(ModelAndView mView) {
		//MemberServiceImpl 객체를 이용해서 비즈니스 로직 처리
		service.getList(mView);
		//view page정보를 담고
		mView.setViewName("member/list");
		return mView;//Model과 view page정보가 담긴 객체를 리턴해준다
	}
	//회원정보 삭제 요청 처리
	@RequestMapping("/member/delete")
	public String delete(@RequestParam int num) {
		service.deleteMember(num);
		//리다일렉트 응답
		return "redirect:/member/list.do";
	}
	//회원정보 추가
	@RequestMapping("/member/insertform")
	public String insertform() {
		//수행할 비즈니스 로직은 없다
		return "member/insertform";
	}
	/*
	 * 	@ModelAttribute MemberDto dto를 메소드의 인자로 선언하면
	 * 	폼 전송되는 파라미터가 자동으로 MemberDto 객체에 setter메소드를 통해서
	 *  들어가고 그 객체가 메소드의 인자로 전달된다.
	 *  단, 파라미터명과 Dto의 필드명이 일치해야 된다.
	 */
	@RequestMapping("/member/insert")
	public ModelAndView insert(@ModelAttribute("dto") MemberDto dto,
					ModelAndView mView) {
		//서비스를 통해 비즈니스 로직 처리
		service.addMember(dto);
		/*
		 *  @ModelAttribute("dto") MemberDto dto의 의미는
		 *  1. 전송되는 파라미터를 자동으로 추출해서 MemberDto에 담아주기도 하고
		 *  2. "dto"라는 키값으로 MemberDto객체를 request영역에 담아주는 역할도 함
		 */
		mView.setViewName("member/insert");
		return mView;
	}
	@RequestMapping("/member/updateform")
	public ModelAndView updateform(@RequestParam int num,
				ModelAndView mView) {
		//ModelAndView 객체에 회원정보가 담기도록 서비스 메소드 호출
		service.getMember(mView, num);
		//view page로 forward 이동해서 수정할 외원의 정보를 출력해준다.
		mView.setViewName("member/updateform");
		return mView;
	}
	@RequestMapping("/member/update")
	public ModelAndView update(@ModelAttribute("dto") MemberDto dto,
			ModelAndView mView) {
		//회원정보가 수정 되도록 서비스의 메소드 호출
		service.updateMember(dto);
		mView.setViewName("member/update");
		return mView;
	}
}
