package com.yunju.spring05.shop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.shop.dao.OrderDao;
import com.yunju.spring05.shop.dao.ShopDao;
import com.yunju.spring05.shop.dto.OrderDto;
import com.yunju.spring05.shop.dto.ShopDto;

@Service
public class ShopServiceImpl implements ShopService{
	//���� ��ü
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private OrderDao orderDao;


	@Override
	public void getList(ModelAndView mView) {
		List<ShopDto> list=shopDao.getList();
		mView.addObject("list", list);
	}

	@Override
	public void buy(HttpServletRequest request, ModelAndView mView) {
		//�α��ε� ���̵�
		String id=(String)request.getSession().getAttribute("id");
		//������ ��ǰ ��ȣ
		int num=Integer.parseInt(request.getParameter("num"));
		//1. ��ǰ�� �������� ������
		int price=shopDao.getPrice(num);
		//2. ��ǰ�� ���ݸ�ŭ ���� �ܾ� ���̱�
		ShopDto dto=new ShopDto();
		dto.setId(id);
		dto.setPrice(price);
		dto.setNum(num);
		shopDao.minusMoney(dto);
		//3. ������ 10%�� ����Ʈ�� ����
		shopDao.plusPoint(dto);
		//4. ����� ������ 1 ���� ��Ų��.
		shopDao.minusCount(num);
		//5. ��� ��û ������ �߰��Ѵ�
		OrderDto dto2=new OrderDto();
		dto2.setId(id);
		dto2.setCode(num);
		dto2.setAddr("������ ��Ÿ����");
		orderDao.addOrder(dto2);
	}

}