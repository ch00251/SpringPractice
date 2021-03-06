package com.yunju.spring05.cafe.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.cafe.dto.CafeCommentDto;
import com.yunju.spring05.cafe.dto.CafeDto;

public interface CafeService {
	public void getList(HttpServletRequest request);
	//새글 작성
	public void saveContent(CafeDto dto);
	//글 자세히 보기
	public void getDetail(HttpServletRequest request);
	//글 삭제
	public void deleteContent(int num);
	//글 수정폼 출력에 필요한 메소드
	public void getUpdateData(ModelAndView mView,int num);
	//글 수정
	public void updateContent(CafeDto dto);
	//댓글 저장하는 메소드
	public void saveComment(HttpServletRequest request);
	//댓글 삭제하는 메소드
	public void deleteComment(int num);
	//댓글 수정하는 메소드
	public void updateComment(CafeCommentDto dto);
}
