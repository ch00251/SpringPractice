package com.yunju.spring05.member.service;

import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.member.dto.MemberDto;

public interface MemberService {
	//ȸ����� ������ ó�� �޼ҵ�, mView�� dao�� �̿��ؼ� ȸ�� ����� ��� ������ �ϸ� ��
	public void getList(ModelAndView mView);
	//ȸ�� �߰�
	public void addMember(MemberDto dto);
	//ȸ������ ��������
	public void getMember(ModelAndView mView, int num);
	//ȸ������ ����
	public void updateMember(MemberDto dto);
	//ȸ������ ����
	public void deleteMember(int num);
}
