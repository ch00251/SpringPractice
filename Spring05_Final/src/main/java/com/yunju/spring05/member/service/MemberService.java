package com.yunju.spring05.member.service;

import org.springframework.web.servlet.ModelAndView;

public interface MemberService {
	//ȸ����� ������ ó�� �޼ҵ�, mView�� dao�� �̿��ؼ� ȸ�� ����� ��� ������ �ϸ� ��
	public void getList(ModelAndView mView);
}
