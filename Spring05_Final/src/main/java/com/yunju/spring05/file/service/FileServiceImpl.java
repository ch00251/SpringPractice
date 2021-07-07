package com.yunju.spring05.file.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.exception.CanNotDeleteException;
import com.yunju.spring05.file.dao.FileDao;
import com.yunju.spring05.file.dto.FileDto;

@Service
public class FileServiceImpl implements FileService{
	@Autowired
	private FileDao dao;
	
	@Override
	public void list(HttpServletRequest request) {
		/*
		 * 	request�� �˻� keyword�� ���޵ɼ��� �ְ� �ȵɼ��� �ִ�.
		 *  - ���� �ȵǴ� ��� : navbar���� ���� ��Ϻ��⸦ �������
		 *  - ���� �Ǵ� ��� : �ϴܿ� �˻�� �Է��ϰ� �˻� ��ư�� �������
		 *  - ���� �Ǵ� ���2 : �̹� �˻��� �� ���¿��� �ϴ� ������ ��ȣ�� ���� ���
		 */
		//�˻��� ���õ� �Ķ���͸� �о�� ����.
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		
		//�˻� Ű���尡 �����Ѵٸ� Ű���� ���� FileDto ��ü ����
		FileDto dto=new FileDto();
		if(keyword!=null) {//�˻� Ű���尡 ���޵� ���
			if(condition.equals("titlename")) {//����+���ϸ� �˻�
				dto.setTitle(keyword);
				dto.setOrgFileName(keyword);
			}else if(condition.equals("title")) {//���� �˻�
				dto.setTitle(keyword);
			}else if(condition.equals("writer")) {//�ۼ��� �˻�
				dto.setWriter(keyword);
			}
			/*
			 * 	�˻� Ű���忡�� �ѱ��� ���Ե� ���ɼ��� �ֱ� ������
			 * 	��ũ�� �״�� ��°����ϵ��� �ϱ� ���� �̸� ���ڵ��� �ؼ�
			 *  request�� ����ش�
			 */
			String encodedKeyword=null;
			try {
				encodedKeyword=URLEncoder.encode(keyword, "utf-8");
			}catch(UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//Ű����� �˻������� request�� ��´�.
			request.setAttribute("keyword", keyword);
			request.setAttribute("encodedKeyword", encodedKeyword);
			request.setAttribute("condition", condition);
		}
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
		int totalRow=dao.getCount(dto);
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
		//��ü ���� ������ ���
		request.setAttribute("totalRow", totalRow);
	}
	
	@Override
	public void saveFile(HttpServletRequest request, FileDto dto) {
		//������ ������ ������ ���� ��θ� ���´�
		String realPath=request.getServletContext().getRealPath("/upload");
		//�ܼ�â�� �׽�Ʈ ���
		System.out.println(realPath);
		
		//MultipartFile ��ü�� ������ ������
		//FileDto�� ��� MultipartFile ��ü�� �������� ���´�
		MultipartFile mFile=dto.getMyFile();
		//���� ���ϸ�
		String orgFileName=mFile.getOriginalFilename();
		//���� ������
		long fileSize=mFile.getSize();
		//������ ������ �� ���
		String filePath=realPath+File.separator;
		//�����丮�� ���� ���� ��ü ����
		File file=new File(filePath);
		if(!file.exists()) {//�����丮�� �������� �ʴ´ٸ�
			file.mkdir();//�����丮�� �����
		}
		//���� �ý��ۿ� ������ ���ϸ��� �����(��ġ�� �ʰ�)
		String saveFileName=
				System.currentTimeMillis()+orgFileName;
		try {
			//upload ������ ������ �����Ѵ�
			mFile.transferTo(new File(filePath+saveFileName));
		}catch(Exception e){
			e.printStackTrace();
		}
		//FileDto ��ü�� �߰� ������ ��´�
		String id=(String)request.getSession().getAttribute("id");
		dto.setWriter(id);//�ۼ���
		dto.setOrgFileName(orgFileName);
		dto.setSaveFileName(saveFileName);
		dto.setFileSize(fileSize);
		//FileDao ��ü�� �̿��ؼ� DB�� �����ϱ�
		dao.insert(dto);
	}
	
	@Override
	public void getFileData(ModelAndView mView, int num) {
		//�ٿ�ε� ������ ������ ������ ���ͼ�
		FileDto dto=dao.getData(num);
		//ModelAndView ��ü�� ��Ƶα�
		mView.addObject("dto", dto);
	}
	
	@Override
	public void addDownCount(int num) {
		//�ٿ�ε� Ƚ�� ���� ��Ű��
		dao.addDownCount(num);
	}
	
	@Override
	public void removeFile(HttpServletRequest request) {
		//1. ������ ������ ��ȣ�� �о�´�
		int num=Integer.parseInt(request.getParameter("num"));
		//2. ������ ������ ������ �о�ͼ� ������ ������ ����� ���ϸ��� ����
		FileDto dto=dao.getData(num);
		//���� �ۼ��ڿ� �α��ε� ���̵� �ٸ��� ���ܸ� �߻���Ų��.
		String id=(String)request.getSession().getAttribute("id");
		if(!id.equals(dto.getWriter())) {
			//���ܸ� �߻� ���Ѽ� �޼ҵ尡 ���� ������� �ʵ��� ���´�.
			throw new CanNotDeleteException();
		}
		String saveFileName=dto.getSaveFileName();
		//3. DB���� ���� ���� ����
		dao.delete(num);
		//4. ���� �ý��ۿ��� ���� ����
		String path=request.getServletContext().getRealPath("/upload")+
				File.separator+saveFileName;
		File f=new File(path);
		f.delete();
	}
	
}
