package com.yunju.spring05.exception;

import org.springframework.dao.DataAccessException;

public class NoDeliveryException extends DataAccessException{
	//�������� ���ڷ� ���� �޼����� ���� �޾Ƽ�
	public NoDeliveryException(String msg) {
		//�θ� �����ڿ� �����ϸ�
		super(msg);
		//message �ʵ忡 ����ȴ�.
	}
}
