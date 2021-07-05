package com.yunju.spring05.file.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunju.spring05.file.dto.FileDto;

@Repository
public class FileDaoImpl implements FileDao{
	@Autowired
	private SqlSession session;
	
	@Override
	public int getCount() {
		//���ε�� ������ ��ü ������ select�ؼ�
		int count=session.selectOne("file.getCount");
		return count;//�����Ѵ�
	}
	
	@Override
	public List<FileDto> getList(FileDto dto){
		List<FileDto> list=session.selectList("file.getList", dto);
		return list;
	}
}
