package com.yunju.spring05.file.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
