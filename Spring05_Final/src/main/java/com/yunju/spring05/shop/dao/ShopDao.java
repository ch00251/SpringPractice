package com.yunju.spring05.shop.dao;

import java.util.List;

import com.yunju.spring05.shop.dto.ShopDto;

public interface ShopDao {
	//��ǰ�� ����� �������ִ� �޼ҵ�
	public List<ShopDto> getList();
	//��ǰ ��� ���� ��Ű�� �޼ҵ�
	public void minusCount(int num);
	//�ܰ� ���� ��Ű�� �޼ҵ�
	public void minusMoney(ShopDto dto);
	//����Ʈ�� ������Ű�� �޼ҵ�
	public void plusPoint(ShopDto dto);
	//Ư�� ��ǰ�� ������ �����ϴ� �޼ҵ�
	public void getPrice(int num);
}
