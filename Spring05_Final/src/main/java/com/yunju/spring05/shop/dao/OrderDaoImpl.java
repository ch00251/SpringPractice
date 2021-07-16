package com.yunju.spring05.shop.dao;

import java.util.Random;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunju.spring05.exception.NoDeliveryException;
import com.yunju.spring05.shop.dto.OrderDto;

@Repository
public class OrderDaoImpl implements OrderDao{
	@Autowired
	private SqlSession session;

	@Override
	public void addOrder(OrderDto dto) {
		//�׽�Ʈ�� ���� 50%�� Ȯ���� ���� �߻���Ű��
		Random ran=new Random();
		int ranNum=ran.nextInt(2);
		if(ranNum==0) {
			throw new NoDeliveryException("����� �Ұ��� �մϴ�.");
		}
		//�ֹ� ���̺� �ֹ� �߰�
		session.insert("shop.addOrder",dto);
	}
	
	
}
