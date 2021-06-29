package com.yunju.spring05.users.service;

import java.util.Map;

import com.yunju.spring05.users.dto.UsersDto;

public interface UsersService {
	public Map<String, Object> isExistId(String inputId);
	public void addUser(UsersDto dto);
}
