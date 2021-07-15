package com.yunju.spring05.cafe.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.cafe.dto.CafeDto;

public interface CafeService {
	public void getList(HttpServletRequest request);
	//���� �ۼ�
	public void saveContent(CafeDto dto);
	//�� �ڼ��� ����
	public void getDetail(HttpServletRequest request);
	//�� ����
	public void deleteContent(int num);
	//�� ������ ��¿� �ʿ��� �޼ҵ�
	public void getUpdateData(ModelAndView mView,int num);
	//�� ����
	public void updateContent(CafeDto dto);
}
