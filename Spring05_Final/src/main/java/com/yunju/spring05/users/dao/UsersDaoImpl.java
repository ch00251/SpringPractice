package com.yunju.spring05.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
