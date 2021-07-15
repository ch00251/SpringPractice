package com.yunju.spring05.cafe.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.cafe.dao.CafeDao;
import com.yunju.spring05.cafe.dto.CafeDto;
import com.yunju.spring05.exception.CanNotDeleteException;

@Service
public class CafeServiceImpl implements CafeService{
	@Autowired
	private CafeDao cafeDao;
	
	//�� �������� ��Ÿ�� row�� ����
	static final int PAGE_ROW_COUNT=5;
	//�ϴ� ���÷��� ������ ����
	static final int PAGE_DISPLAY_COUNT=5;

	@Override
	public void getList(HttpServletRequest request) {
		/*
		 * request�� �˻� keyword�� ���� �ɼ��� �ְ� �ȵɼ��� �ִ�.
		 * 1. ���� �ȵǴ� ��� : home���� ��Ϻ��⸦ �������
		 * 2. ���� �Ǵ� ��� : list.do���� �˻�� �Է� �� form ������ ���
		 * 3. ���� �Ǵ� ��� : �̹� �˻��� �� ���¿��� ����¡ó�� ��ũ�� �������
		 */
		//�˻��� ���õ� �Ķ���͸� �о�� ����.
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		
		//CafeDto ��ü ����(select �� �� �ʿ��� ������ ��� ����)
		CafeDto dto=new CafeDto();
		
		if(keyword!=null) {//�˻� Ű���尡 ���޵� ���
			if(condition.equals("titlecontent")) {//����+���� �˻�
				dto.setTitle(keyword);
				dto.setContent(keyword);
			}else if(condition.equals("title")) {//���� �˻�
				dto.setTitle(keyword);
			}else if(condition.equals("writer")) {//�ۼ��� �˻�
				dto.setWriter(keyword);
			}
			//request �� �˻� ���ǰ� Ű���� ���
			request.setAttribute("condition", condition);
			/*
			 *  �˻� Ű���忡�� �ѱ��� ���Ե� ���ɼ��� �ֱ� ������
			 *  ��ũ�� �״�� ��°����ϵ��� �ϱ� ���� �̸� ���ڵ��� �ؼ�
			 *  request �� ����ش�.
			 */
			String encodedKeyword=null;
			try {
				encodedKeyword=URLEncoder.encode(keyword, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//���ڵ��� Ű����� ���ڵ� �ȵ� Ű���带 ��� ��´�.
			request.setAttribute("encodedKeyword", encodedKeyword);
			request.setAttribute("keyword", keyword);
		}
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
		int totalRow=cafeDao.getCount(dto);
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
		// startRowNum �� endRowNum �� CafeDto ��ü�� ��� 
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);

		// startRowNum �� endRowNum �� �ش��ϴ� ī��� ����� select �� �´�.
		List<CafeDto> list=cafeDao.getList(dto);

		//view ���������� �ʿ��� ���� request �� ��� 
		request.setAttribute("list", list);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPageCount", totalPageCount);	
		//��ü ���� ������ request �� ��´�.
		request.setAttribute("totalRow", totalRow);
	}
	
	@Override
	public void saveContent(CafeDto dto) {
		cafeDao.insert(dto);
	}
	
	@Override
	public void getDetail(HttpServletRequest request) {
		//�Ķ���ͷ� ���޵Ǵ� �۹�ȣ
		int num=Integer.parseInt(request.getParameter("num"));
		
		//�˻��� ���õ� �Ķ���͸� �о�´�
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		
		//CafeDto ��ü ����(select�Ҷ� �ʿ��� ������ ��� ����)
		CafeDto dto=new CafeDto();
		
		if(keyword!=null) {//�˻� Ű���尡 ���޵� ���
			if(condition.equals("titlecontent")) {//����+���� �˻�
				dto.setTitle(keyword);
				dto.setContent(keyword);
			}else if(condition.equals("title")) {//���� �˻�
				dto.setTitle(keyword);
			}else if(condition.equals("writer")) {//�ۼ��� �˻�
				dto.setWriter(keyword);
			}
			//request �� �˻� ���ǰ� Ű���� ���
			request.setAttribute("condition", condition);
			/*
			 *  �˻� Ű���忡�� �ѱ��� ���Ե� ���ɼ��� �ֱ� ������
			 *  ��ũ�� �״�� ��°����ϵ��� �ϱ� ���� �̸� ���ڵ��� �ؼ�
			 *  request �� ����ش�.
			 */
			String encodedKeyword=null;
			try {
				encodedKeyword=URLEncoder.encode(keyword, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//���ڵ��� Ű����� ���ڵ� �ȵ� Ű���带 ��� ��´�.
			request.setAttribute("encodedKeyword", encodedKeyword);
			request.setAttribute("keyword", keyword);
		}		
		//CafeDto �� �۹�ȣ�� ���
		dto.setNum(num);
		//��ȸ�� 1 ���� ��Ű��
		cafeDao.addViewCount(num);
		//�������� ���ͼ�
		CafeDto dto2=cafeDao.getData(dto);
		//request �� �������� ��� 
	request.setAttribute("dto", dto2);		
	}
	
	@Override
	public void deleteContent(int num) {
		cafeDao.delete(num);
	}
	
	@Override
	public void getUpdateData(ModelAndView mView, int num) {
		//������ �۹�ȣ�� �̿��ؼ� ������ �������� ���ͼ�
		CafeDto dto=cafeDao.getData2(num);
		//ModelAndView ��ü�� ��´�.
		mView.addObject("dto", dto);
	}

	@Override
	public void updateContent(CafeDto dto) {
		//CafeDao ��ü�� �̿��ؼ� ������ ���� �ݿ��Ѵ�. 
		cafeDao.update(dto);
	}
}
