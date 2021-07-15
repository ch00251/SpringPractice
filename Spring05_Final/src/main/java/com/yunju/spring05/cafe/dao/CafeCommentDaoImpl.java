package com.yunju.spring05.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunju.spring05.cafe.dto.CafeCommentDto;

@Repository
public class CafeCommentDaoImpl implements CafeCommentDao {
	@Autowired
	private SqlSession session;
	
	//���ڷ� ���޵� �׷��ȣ(������ �۹�ȣ)�� �ش�Ǵ� ��� ��� ������
	@Override
	public List<CafeCommentDto> getList(int ref_group) {
		return session.selectOne("cafeComment.getList", ref_group);
	}

	@Override
	public void delete(int num) {
		session.update("cafeComment.delete", num);
	}

	@Override
	public void insert(CafeCommentDto dto) {
		session.insert("cafeComment.insert", dto);
	}
	//������ ����� �۹�ȣ�� �����ϴ� �޼ҵ�
	@Override
	public int getSequence() {
		//������ ���� ����
		int seq=session.selectOne("cafeComment.getSequence");
		//�������ش�
		return seq;
	}
	
	@Override
	public void update(CafeCommentDto dto) {
		session.update("cafeComment.update", dto);
	}
}
