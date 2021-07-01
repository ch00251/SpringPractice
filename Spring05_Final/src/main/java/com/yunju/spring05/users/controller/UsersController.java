package com.yunju.spring05.users.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.users.dto.UsersDto;
import com.yunju.spring05.users.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService service;
	//ȸ������ �� ��ûó��
	@RequestMapping("/users/signupform")
	public String signupform() {
		return "users/signupform";
	}
	/*
	 *  [ JSON ���ڿ� �����ϴ� ��� ]
	 *  1. pom.xml �� jackson-databind dependency ���
	 *  2. controller �� �޼ҵ忡 @ResponseBody ������̼� ���̱�
	 *  3. List, Map, Dto �߿� �ϳ��� �����Ѵ�.
	 */
	@ResponseBody
	@RequestMapping("/users/checkid")
	public Map<String, Object> checkid(@RequestParam String inputId){
		Map<String, Object> map=service.isExistId(inputId);
		return map;
	}
	//POST��� /users/signup.do ��ûó��
	@RequestMapping(value="/users/signup", method=RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute("dto") UsersDto dto,
				ModelAndView mView) {
		service.addUser(dto);
		mView.setViewName("users/insert");
		return mView;
	}
	
	//�α��� �� ��û ó��
	@RequestMapping("/users/loginform")
	public String loginform(HttpServletRequest request) {
		//"url"�̶�� �Ķ���Ͱ� �Ѿ������ �о�� ����
		String url=request.getParameter("url");
		if(url==null) {//���� ������
			//�α��� �����Ŀ� index�������� ���� �� �ֵ��� ����
			url=request.getContextPath()+"/home.do";
		}
		//���̵�, ��й�ȣ�� ��Ű�� ����Ǿ����� Ȯ���ؼ� ���� �Ǿ����� ���� ���
		Cookie[] cookies=request.getCookies();
		//����� ���̵�� ��й�ȣ�� ���� ������ �����ϰ� �ʱⰪ���� �� ���ڿ� ����
		String savedId="";
		String savedPwd="";
		if(cookies!=null) {
			for(Cookie tmp:cookies) {
				if(tmp.getName().equals("savedId")) {
					savedId=tmp.getValue();
				}else if(tmp.getName().equals("savedPwd")){
					savedPwd=tmp.getValue();
				}
			}
		}
		//view page���� �ʿ��� ���� �Ѱ��ֱ�
		request.setAttribute("url", url);
		request.setAttribute("savedId", savedId);
		request.setAttribute("savedPwd", savedPwd);
		return "users/loginform";
	}
	
	//�α��� ��û ó�� 
	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute UsersDto dto,
			ModelAndView mView, 
			HttpServletRequest request, HttpServletResponse response) {
		//������ ����
		String url=request.getParameter("url");
		if(url==null){
			url=request.getContextPath()+"/home.do";
		}
		//������ ������ �̸� ���ڵ� �� ���´�.
		String encodedUrl=URLEncoder.encode(url);
		// view page �� �����ϱ� 
		mView.addObject("url", url);
		mView.addObject("encodedUrl", encodedUrl);

		//���̵� ��й�ȣ ���� üũ�ڽ��� üũ �ߴ��� �о�� ����.
		String isSave=request.getParameter("isSave");	
		//���̵�, ��й�ȣ�� ��Ű�� ����
		Cookie idCook=new Cookie("savedId", dto.getId());
		Cookie pwdCook=new Cookie("savedPwd", dto.getPwd());
		if(isSave != null){ // null �� �ƴϸ� üũ �� ���̴�.
			//�Ѵ� ���� �����ϱ�
			idCook.setMaxAge(60*60*24*30);
			pwdCook.setMaxAge(60*60*24*30);
		}else{
			//��Ű ����� 
			idCook.setMaxAge(0);
			pwdCook.setMaxAge(0);
		}
		//�����Ҷ� ��Ű�� �ɾ� ������ 
		response.addCookie(idCook);
		response.addCookie(pwdCook);

		service.validUser(dto, request.getSession(), mView);

		mView.setViewName("users/login");
		return mView;
	}
	
	//�α׾ƿ� ó��
	@RequestMapping("/users/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home.do";
	}
	
	//�������� ���� ��ûó��
	@RequestMapping("/users/info")
	public ModelAndView authInfo(HttpServletRequest request,
							ModelAndView mView) {
		//�α��ε� ���̵� �о����
		String id=(String)request.getSession().getAttribute("id");
		//UsersService��ü�� �̿��ؼ� ���������� ModelAndView ��ü�� ���
		service.showInfo(id, mView);
		//view page������ ���
		mView.setViewName("users/info");
		return mView;//ModelAndView��ü�� �������ֱ�
	
	}
	
	/*
	 * 	[ ���� ���ε� ���� ]
	 * 
	 * 	1. pom.xml�� commons-fileupload, commons-io dependency ����ϱ�
	 * 	2. servlet-context.xml�� CommonsMultipartResolver bean ����
	 * 	3. MultipartFile ��ü Ȱ��
	 * 	4. upload ���� �����
	 */
	
	//ajax ���� ���ε� ó��, JSON ���ڿ��� ������ �־�� �Ѵ�.
	@ResponseBody
	@RequestMapping(value="/users/profile_upload",
					method=RequestMethod.POST)
	public Map<String, Object> profileUpload(HttpServletRequest request,
					@RequestParam MultipartFile profileImage){
		String path=service.saveProfileImage(request, profileImage);
		/*
		 *  {"savedPath":"/upload/xxxx.jpg"} ������ JSON ���ڿ��� �������ֵ���
		 *  Map ��ü�� �����ؼ� �������ش�. 
		 */
		Map<String, Object> map=new HashMap<>();
		map.put("savedPath", path);
		return map;
	}
}
