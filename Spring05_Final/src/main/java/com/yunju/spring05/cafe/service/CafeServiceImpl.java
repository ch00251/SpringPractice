package com.yunju.spring05.cafe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunju.spring05.cafe.dao.CafeDao;

@Service
public class CafeServiceImpl implements CafeService{
	@Autowired
	private CafeDao dao;
}
