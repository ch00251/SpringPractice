package com.yunju.spring05.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunju.spring05.file.dao.FileDao;

@Service
public class FileServiceImpl implements FileService{
	@Autowired
	private FileDao dao;
	
}
