package com.yunju.spring05.users.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.users.dao.UsersDao;
import com.yunju.spring05.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersDao dao;
	//���ڷ� ���޵� ���̵� �����ϴ��� ���θ� Map�� ��Ƽ� �����ϴ� �޼ҵ�
	@Override
	public Map<String, Object> isExistId(String inputId) {
		boolean isExist=dao.isExist(inputId);
		Map<String, Object> map=new HashMap<>();
		map.put("isExist", isExist);
		return map;
	}
	@Override
	public void addUser(UsersDto dto) {
		//��й�ȣ�� ��ȣȭ �Ѵ�
		String encodedPwd=new BCryptPasswordEncoder().encode(dto.getPwd());
		//��ȣȭ�� ��й�ȣ�� UsersDto �� �ٽ� �־��ش�
		dto.setPwd(encodedPwd);
		//UsersDao ��ü�� �̿��ؼ� DB�� �����ϱ�
		dao.insert(dto);
	}
	@Override
	public void validUser(UsersDto dto, HttpSession session, ModelAndView mView) {
		//���̵� ��й�ȣ�� ��ȿ���� ����
		boolean isValid=false;
		//���̵� �̿��ؼ� ����� ��� ��ȣ�� �о�´�
		String pwdHash=dao.getPwdHash(dto.getId());
		if(pwdHash!=null) {//��й�ȣ�� �����ϰ�
			//�Է��� ��й�ȣ�� ��ġ �ϴٸ� �α��� ����
			isValid=BCrypt.checkpw(dto.getPwd(), pwdHash);			
		}
		if(isValid) {
			//�α��� ó���� �Ѵ�
			session.setAttribute("id", dto.getId());
		}
	}
	
	@Override
	public void showInfo(String id, ModelAndView mView) {
		UsersDto dto=dao.getData(id);
		mView.addObject("dto",dto);
	}
}
