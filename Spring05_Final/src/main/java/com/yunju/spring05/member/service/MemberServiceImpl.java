package com.yunju.spring05.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.member.dao.MemberDao;
import com.yunju.spring05.member.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService{
	//���񽺴� Dao�� �����Ѵ�
	@Autowired
	private MemberDao dao;
	
	//ȸ�� ����� ���ڷ� ���� ���� ModelAndView��ü�� ��� ������ ó���ϴ� ���� �޼ҵ�
	@Override
	public void getList(ModelAndView mView) {
		List<MemberDto> list=dao.getList();
		mView.addObject("list", list);
	}
	
}
