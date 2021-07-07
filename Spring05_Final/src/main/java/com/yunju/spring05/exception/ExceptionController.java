package com.yunju.spring05.exception;

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
}
