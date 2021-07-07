package com.yunju.spring05.view;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.yunju.spring05.file.dto.FileDto;

/*
 * 	[ View �� ����� ��� ]
 * 
 * 	- AbstractView �߻� Ŭ������ ��� �޴´�.
 *  - @Component("bean�� �̸� ����")
 *  - ModelAndView ��ü�� ���� ���� Map ��ü�� ���޵ȴ�.
 *  - servlet-context.xml�� BeanNameViewResolver ����
 */
@Component("fileDownView") //bean�� �̸� �����ϱ�
public class FileDownView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//ModelAndView ��ü�� ��Ҵ� ���� Map ��ü�� ��ܼ� ���޵ȴ�.
		FileDto dto=(FileDto)model.get("dto");
		//���� data�� �����Ѵ�.
		String orgFileName=dto.getOrgFileName();//���� ���ϸ�
		String saveFileName=dto.getSaveFileName();//����� ���ϸ�
		//ServletContext��ü�� ������ (jsp������������ �⺻��ü)
		ServletContext application=request.getServletContext();
		//�ٿ�ε� ������ ������ ���� ��� �����ϱ�
		String path=application.getRealPath("/upload")
				+File.separator+saveFileName;

		//�ٿ�ε� �� ������ �о�� ��Ʈ�� ��ü �����ϱ�
		FileInputStream fis=new FileInputStream(path);

		//�ٿ�ε� �����ִ� �۾��� �Ѵ�. (���� ���� �����Ϳ� �������ϸ��� ��������Ѵ�.)
		String encodedName=null;
		System.out.println(request.getHeader("User-Agent"));
		//�ѱ� ���ϸ� ����ó�� 
		if(request.getHeader("User-Agent").contains("Firefox")){
			//�����簡 ���̾� �����ΰ�� 
			encodedName=new String
				(orgFileName.getBytes("utf-8"),"ISO-8859-1");
		}else{ //�׿� �ٸ� ������ 
			encodedName=URLEncoder.encode(orgFileName, "utf-8");
			//���ϸ� �������ִ� ��� ó�� 
			encodedName=encodedName.replaceAll("\\+"," ");
		}

		//���� ��� ���� ����
		response.setHeader("Content-Disposition","attachment;filename="+encodedName);
		response.setHeader("Content-Transfer-Encoding", "binary");

		//�ٿ�ε��� ������ ũ�� �о�ͼ� �ٿ�ε��� ������ ũ�� ����
		response.setContentLengthLong(dto.getFileSize());

		//Ŭ���̾�Ʈ���� ����Ҽ� �ִ� ��Ʈ�� ��ü ������
		BufferedOutputStream bos=
			new BufferedOutputStream(response.getOutputStream());
		//�ѹ��� �ִ� 1M byte �� �о�ü� �ִ� ����
		byte[] buffer=new byte[1024*1000];
		int readedByte=0;
		//�ݺ��� ���鼭 ������ֱ�
		while(true){
			//byte[] ��ü�� �̿��ؼ� ���Ͽ��� byte �˰��� �о����
			readedByte = fis.read(buffer);
			if(readedByte == -1)break; //���̻� ���� �����Ͱ� ���ٸ� �ݺ��� ���� ������
			//���� ��ŭ ����ϱ�
			bos.write(buffer, 0, readedByte);
			bos.flush(); //���
		}
		fis.close(); 
		
	}

}
