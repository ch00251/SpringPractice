package com.yunju.spring05.users.dao;

import com.yunju.spring05.users.dto.UsersDto;

public interface UsersDao {
	public boolean isExist(String inputId);
	public void insert(UsersDto dto);
}
