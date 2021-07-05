package com.yunju.spring05.file.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunju.spring05.file.dao.FileDao;
import com.yunju.spring05.file.dto.FileDto;

@Service
public class FileServiceImpl implements FileService{
	@Autowired
	private FileDao dao;
	
	@Override
	public void list(HttpServletRequest request) {
		//�� �������� ��Ÿ�� row �� ����
		final int PAGE_ROW_COUNT=5;
		//�ϴ� ���÷��� ������ ����
		final int PAGE_DISPLAY_COUNT=5;

		//������ �������� ��ȣ
		int pageNum=1;
		//������ �������� ��ȣ�� �Ķ���ͷ� ���޵Ǵ��� �о�� ����.	
		String strPageNum=request.getParameter("pageNum");
		if(strPageNum != null){//������ ��ȣ�� �Ķ���ͷ� �Ѿ�´ٸ�
			//������ ��ȣ�� �����Ѵ�.
			pageNum=Integer.parseInt(strPageNum);
		}
		//������ ������ �������� ���� ResultSet row ��ȣ
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//������ ������ �������� �� ResultSet row ��ȣ
		int endRowNum=pageNum*PAGE_ROW_COUNT;

		//��ü row �� ������ �о�´�.
		int totalRow=dao.getCount();
		//��ü �������� ���� ���ϱ�
		int totalPageCount=
				(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//���� ������ ��ȣ
		int startPageNum=
			1+((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//�� ������ ��ȣ
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//�� ������ ��ȣ�� �߸��� ���̶�� 
		if(totalPageCount < endPageNum){
			endPageNum=totalPageCount; //�������ش�. 
		}		
		// FileDto ��ü�� ������ ���� startRowNum �� endRowNum �� ��´�.
		FileDto dto=new FileDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);

		//1. DB ���� ���� ����� ���´�.
		List<FileDto> list=dao.getList(dto);
		//2. view page �� �ʿ��� ���� request �� ��Ƶд�.
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("list", list);
	}
}
