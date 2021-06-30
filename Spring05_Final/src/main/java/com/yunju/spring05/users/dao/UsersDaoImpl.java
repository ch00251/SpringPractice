package com.yunju.spring05.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunju.spring05.users.dto.UsersDto;

@Repository
public class UsersDaoImpl  implements UsersDao{
	
	@Autowired
	private SqlSession session;
	
	@Override
	public boolean isExist(String inputId) {
		//���ڷ� ���޵Ǵ� ���̵� �̿��ؼ� select�� �Ѵ�
		String id=session.selectOne("users.isExist", inputId);
		//���� select�� ����� null�̸� �������� �ʴ� ���̵��̴�
		if(id==null) {
			return false;
		}else {
			return true;
		}
	}
	
	@Override
	public void insert(UsersDto dto) {
		session.insert("users.insert", dto);
	}
	
	@Override
	public String getPwdHash(String inputId) {
		//�Է��� ���̵� �̿��ؼ� ����� ��й�ȣ�� select�Ѵ�
		//���� �������� �ʴ� ���̵�� null�̴�
		String savedPwd=session.selectOne("users.getPwdHash", inputId);
		//select�� ��й�ȣ�� �������ش�
		return savedPwd;
	}
}
