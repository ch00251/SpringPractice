package com.yunju.spring05.users.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

}
