package com.yunju.spring05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.member.dao.MemberDao;
import com.yunju.spring05.member.dto.MemberDto;

@Controller
public class MemberController {
	//���� ��ü ���� �ޱ�(DI)
	@Autowired
	private MemberDao dao;
	
	//ȸ�� ��� ���� ��û(/member/list.do)�� ó���� ��Ʈ�ѷ��� �޼ҵ�
	@RequestMapping("/member/list")
	public ModelAndView list(ModelAndView mView) {
		//ȸ�� ����� ��������?
		List<MemberDto> list=dao.getList();
		
		mView.addObject("list",list);
		mView.setViewName("member/list");
		return mView;
	}
	//ȸ������ ���� ��û ó��
	@RequestMapping("/member/delete")
	public String delete(@RequestParam int num) {
		//MemberDao ��ü�� �̿��ؼ� ȸ������ ����
		dao.delete(num);
		//�����Ϸ�Ʈ ����
		return "redirect:/member/list.do";
	}
	//ȸ������ �߰�
	@RequestMapping("/member/insertform")
	public String insertform() {
		//������ ����Ͻ� ������ ����
		return "member/insertform";
	}
	/*
	 * 	@ModelAttribute MemberDto dto�� �޼ҵ��� ���ڷ� �����ϸ�
	 * 	�� ���۵Ǵ� �Ķ���Ͱ� �ڵ����� MemberDto ��ü�� setter�޼ҵ带 ���ؼ�
	 *  ���� �� ��ü�� �޼ҵ��� ���ڷ� ���޵ȴ�.
	 *  ��, �Ķ���͸�� Dto�� �ʵ���� ��ġ�ؾ� �ȴ�.
	 */
	@RequestMapping("/member/insert")
	public ModelAndView insert(@ModelAttribute MemberDto dto,
					ModelAndView mView) {
		dao.insert(dto);
		mView.addObject("dto",dto);
		mView.setViewName("member/insert");
		return mView;
	}
}
