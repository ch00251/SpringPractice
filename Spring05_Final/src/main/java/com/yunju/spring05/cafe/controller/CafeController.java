package com.yunju.spring05.cafe.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.cafe.dto.CafeCommentDto;
import com.yunju.spring05.cafe.dto.CafeDto;
import com.yunju.spring05.cafe.service.CafeService;

@Controller
public class CafeController {
	@Autowired
	private CafeService service;
	
	//�۸�� ��ûó��
	@RequestMapping("/cafe/list")
	public ModelAndView list(HttpServletRequest request) {
		//HttpServletRequest ��ü�� ���񽺿� �Ѱ��ָ鼭
		//����Ͻ� ������ �����ϰ�
		service.getList(request);
		
		//view page�� forward �̵��ؼ� �� ��� ����ϱ�
		return new ModelAndView("cafe/list");
	}
	//���� �߰� �� ��ûó��
	@RequestMapping("/cafe/insertform")
	public ModelAndView authInsertform(HttpServletRequest request) {
		return new ModelAndView("cafe/insertform");
	}
	
	//���� �߰� ��ûó��
	@RequestMapping(value="/cafe/insert", method=RequestMethod.POST)
	public ModelAndView authInsert(HttpServletRequest request,
			 		@ModelAttribute CafeDto dto) {
		//���ǿ� �ִ� ���ۼ����� ���̵�
		String writer=(String)request.getSession().getAttribute("id");
		//CafeDto ��ü�� ���
		dto.setWriter(writer);
		//���񽺸� �̿��ؼ� DB�� ����
		service.saveContent(dto);
		//�� ��Ϻ���� �����Ϸ�Ʈ �̵�
		return new ModelAndView("redirect:/cafe/list.do");
	}
	
	//�� �ڼ��� ���� ��ûó��
	@RequestMapping("/cafe/detail")
	public String detail(HttpServletRequest request) {
		service.getDetail(request);
		//view page�� forward �̵��ؼ� �� �ڼ��� ����
		return "cafe/detail";
	}
	
	//�� ������û ó��
	@RequestMapping("/cafe/delete")
	public ModelAndView 
		authDelete(HttpServletRequest request,
				@RequestParam int num){
		//���񽺸� �̿��ؼ� ���� �����ϱ� 
		service.deleteContent(num);
		//�� ��� ����� �����Ϸ�Ʈ �̵� 
		return new ModelAndView("redirect:/cafe/list.do");
	}
	
	@RequestMapping("/cafe/updateform")
	public ModelAndView authUpdateform(HttpServletRequest request,
					@RequestParam int num, ModelAndView mView) {
		//���񽺸� �̿��ؼ� ������ �������� ModelAndView ��ü�� ���
		service.getUpdateData(mView, num);
		//view page�� forward �̵��ؼ� ������ ���
		mView.setViewName("cafe/updateform");
		return mView;
	}
	
	//�� �����ݿ� ��ûó��
	@RequestMapping(value="/cafe/update", method=RequestMethod.POST)
	public ModelAndView authUpdate(HttpServletRequest request,
							@ModelAttribute CafeDto dto) {
		//���񽺸� �̿��ؼ� �����ݿ�
		service.updateContent(dto);		
		//�� �ڼ�������� �����Ϸ�Ʈ �̵�
		return new ModelAndView("redirect:/cafe/detail.do?num="+dto.getNum());
	}
	
	//��� ���� ��û ó��
	@RequestMapping(value="/cafe/comment_insert", method=RequestMethod.POST)
	public ModelAndView authCommentInsert(HttpServletRequest request, 
					@RequestParam int ref_group) {
		service.saveComment(request);
		return new ModelAndView("redirect:/cafe/detail.do?num="+ref_group);
	}
	
	//��� ���� ��û ó��
	@ResponseBody
	@RequestMapping(value="/cafe/comment_delete", method=RequestMethod.POST)
	public Map<String, Object>
			authCommentDelete(HttpServletRequest request, @RequestParam int num){
		service.deleteComment(num);
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isSuccess", true);
		return map;//{"isSuccess":true} ������ JSON ���ڿ��� ����ȴ�.
	}
	
	//��� ���� ��ûó��
	@ResponseBody
	@RequestMapping("/cafe/comment_update")
	public Map<String, Object>
			authCommentUpdate(HttpServletRequest request, 
					@ModelAttribute CafeCommentDto dto){
		service.updateComment(dto);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isSuccess", true);
		return map;
	}
}
