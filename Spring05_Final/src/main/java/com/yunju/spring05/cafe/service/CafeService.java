package com.yunju.spring05.cafe.service;

import javax.servlet.http.HttpServletRequest;

import com.yunju.spring05.cafe.dto.CafeDto;

public interface CafeService {
	public void getList(HttpServletRequest request);
	//새글 작성
	public void saveContent(CafeDto dto);
	//글 자세히 보기
	public void getDetail(HttpServletRequest request);
}
