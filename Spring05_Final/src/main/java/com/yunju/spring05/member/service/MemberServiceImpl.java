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
	@Override
	public void addMember(MemberDto dto) {
		dao.insert(dto);
	}
	//ȸ�� �Ѹ��� ������ ModelAndView ��ü�� ��� ����Ͻ� ����ó��
	@Override
	public void getMember(ModelAndView mView, int num) {
		MemberDto dto=dao.getData(num);
		mView.addObject("dto", dto);
	}
	//ȸ�� ���� �����ϴ� ����Ͻ� ���� ó��
	@Override
	public void updateMember(MemberDto dto) {
		dao.update(dto);
	}
	//ȸ�� ���� �����ϴ� ����Ͻ� ���� ó��
	@Override
	public void deleteMember(int num) {
		dao.delete(num);
	}
}
