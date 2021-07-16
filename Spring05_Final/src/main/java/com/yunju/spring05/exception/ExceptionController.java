package com.yunju.spring05.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/*
 * 	Exception�� �߻����� �� ó���ϴ� ��Ʈ�ѷ� �����
 * 
 * 	- @ControllerAdvice ������̼��� Ŭ������ ���δ�.
 * 
 * 	- �޼ҵ忡 @ExceptionHandler(���� class type)�� �ٿ��� ���ܸ� ó���Ѵ�.
 */
@ControllerAdvice
public class ExceptionController {
	//CanNotDeleteException type�� ���ܰ� �߻��ϸ� ȣ��Ǵ� �޼ҵ�
	@ResponseStatus(HttpStatus.FORBIDDEN)//���� ���¸� ǥ���ϰ� ������ ǥ���Ѵ�
	@ExceptionHandler(CanNotDeleteException.class)
	public ModelAndView forbidden() {
		ModelAndView mView=new ModelAndView();
		mView.addObject("msg", "���� ���� ������ ������!");
		mView.setViewName("error/forbidden");
		return mView;
	}
	
	/*
	 * 	@Repository ������̼��� �ۼ��� Dao ���� DB ���� Exception�� �߻��ϸ�
	 *  Spring �����ӿ�ũ�� DataAccessException type�� ���ܸ� �߻���Ų��.
	 *  ���� ��ü�� �޼ҵ��� ���ڷ� ���޵ǰ� �ش� ���� ��ü�� getMessage()���
	 *  getter �޼ҵ尡 �����Ѵ�.
	 *  �ش� �޼ҵ带 ȣ���ϸ� ���� �޼����� �������ش�.
	 */
	@ExceptionHandler(DataAccessException.class)
	public ModelAndView dataAccess(DataAccessException dae) {
		ModelAndView mView=new ModelAndView();
		//"exception" �̶�� Ű������ ���� ��ü�� ��´�.
		mView.addObject("exception", dae);
		//view page ����
		mView.setViewName("error/data_access");
		return mView;
	}
	//custom ���� ó��
	@ExceptionHandler(NoDeliveryException.class)
	public ModelAndView noDelivery(NoDeliveryException nde) {
		ModelAndView mView=new ModelAndView();
		mView.addObject("exception", nde);
		mView.setViewName("error/data_access");
		return mView;
	}
}
