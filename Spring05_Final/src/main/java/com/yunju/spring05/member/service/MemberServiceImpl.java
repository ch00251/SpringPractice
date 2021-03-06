package com.yunju.spring05.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.member.dao.MemberDao;
import com.yunju.spring05.member.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService{
	//서비스는 Dao에 의존한다
	@Autowired
	private MemberDao dao;
	
	//회원 목록을 인자로 전달 받은 ModelAndView객체에 담는 로직을 처리하는 서비스 메소드
	@Override
	public void getList(ModelAndView mView) {
		List<MemberDto> list=dao.getList();
		mView.addObject("list", list);
	}
	@Override
	public void addMember(MemberDto dto) {
		dao.insert(dto);
	}
	//회원 한명의 정보를 ModelAndView 객체에 담는 비즈니스 로직처리
	@Override
	public void getMember(ModelAndView mView, int num) {
		MemberDto dto=dao.getData(num);
		mView.addObject("dto", dto);
	}
	//회원 정보 수정하는 비즈니스 로직 처리
	@Override
	public void updateMember(MemberDto dto) {
		dao.update(dto);
	}
	//회원 정보 삭제하는 비즈니스 로직 처리
	@Override
	public void deleteMember(int num) {
		dao.delete(num);
	}
}
