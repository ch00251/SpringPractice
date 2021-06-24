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
import com.yunju.spring05.member.service.MemberService;

@Controller
public class MemberController {
	//���� ��ü ���� �ޱ�(DI)
	@Autowired
	private MemberService service;
	
	//ȸ�� ��� ���� ��û(/member/list.do)�� ó���� ��Ʈ�ѷ��� �޼ҵ�
	@RequestMapping("/member/list")
	public ModelAndView list(ModelAndView mView) {
		//MemberServiceImpl ��ü�� �̿��ؼ� ����Ͻ� ���� ó��
		service.getList(mView);
		//view page������ ���
		mView.setViewName("member/list");
		return mView;//Model�� view page������ ��� ��ü�� �������ش�
	}
	//ȸ������ ���� ��û ó��
	@RequestMapping("/member/delete")
	public String delete(@RequestParam int num) {
		service.deleteMember(num);
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
	public ModelAndView insert(@ModelAttribute("dto") MemberDto dto,
					ModelAndView mView) {
		//���񽺸� ���� ����Ͻ� ���� ó��
		service.addMember(dto);
		/*
		 *  @ModelAttribute("dto") MemberDto dto�� �ǹ̴�
		 *  1. ���۵Ǵ� �Ķ���͸� �ڵ����� �����ؼ� MemberDto�� ����ֱ⵵ �ϰ�
		 *  2. "dto"��� Ű������ MemberDto��ü�� request������ ����ִ� ���ҵ� ��
		 */
		mView.setViewName("member/insert");
		return mView;
	}
	@RequestMapping("/member/updateform")
	public ModelAndView updateform(@RequestParam int num,
				ModelAndView mView) {
		//ModelAndView ��ü�� ȸ�������� ��⵵�� ���� �޼ҵ� ȣ��
		service.getMember(mView, num);
		//view page�� forward �̵��ؼ� ������ �ܿ��� ������ ������ش�.
		mView.setViewName("member/updateform");
		return mView;
	}
	@RequestMapping("/member/update")
	public ModelAndView update(@ModelAttribute("dto") MemberDto dto,
			ModelAndView mView) {
		//ȸ�������� ���� �ǵ��� ������ �޼ҵ� ȣ��
		service.updateMember(dto);
		mView.setViewName("member/update");
		return mView;
	}
}
