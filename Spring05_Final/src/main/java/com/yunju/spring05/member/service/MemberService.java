package com.yunju.spring05.member.service;

import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.member.dto.MemberDto;

public interface MemberService {
	//회원목록 얻어오기 처리 메소드, mView는 dao를 이용해서 회원 목록을 담는 역할을 하면 됨
	public void getList(ModelAndView mView);
	//회원 추가
	public void addMember(MemberDto dto);
	//회원정보 가져오기
	public void getMember(ModelAndView mView, int num);
	//회원정보 수정
	public void updateMember(MemberDto dto);
	//회원정보 삭제
	public void deleteMember(int num);
}
