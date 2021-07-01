package com.yunju.spring05.users.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yunju.spring05.users.dao.UsersDao;
import com.yunju.spring05.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersDao dao;
	//���ڷ� ���޵� ���̵� �����ϴ��� ���θ� Map�� ��Ƽ� �����ϴ� �޼ҵ�
	@Override
	public Map<String, Object> isExistId(String inputId) {
		boolean isExist=dao.isExist(inputId);
		Map<String, Object> map=new HashMap<>();
		map.put("isExist", isExist);
		return map;
	}
	@Override
	public void addUser(UsersDto dto) {
		//��й�ȣ�� ��ȣȭ �Ѵ�
		String encodedPwd=new BCryptPasswordEncoder().encode(dto.getPwd());
		//��ȣȭ�� ��й�ȣ�� UsersDto �� �ٽ� �־��ش�
		dto.setPwd(encodedPwd);
		//UsersDao ��ü�� �̿��ؼ� DB�� �����ϱ�
		dao.insert(dto);
	}
	@Override
	public void validUser(UsersDto dto, HttpSession session, ModelAndView mView) {
		//���̵� ��й�ȣ�� ��ȿ���� ����
		boolean isValid=false;
		//���̵� �̿��ؼ� ����� ��� ��ȣ�� �о�´�
		String pwdHash=dao.getPwdHash(dto.getId());
		if(pwdHash!=null) {//��й�ȣ�� �����ϰ�
			//�Է��� ��й�ȣ�� ��ġ �ϴٸ� �α��� ����
			isValid=BCrypt.checkpw(dto.getPwd(), pwdHash);			
		}
		if(isValid) {
			//�α��� ó���� �Ѵ�
			session.setAttribute("id", dto.getId());
		}
	}
	
	@Override
	public void showInfo(String id, ModelAndView mView) {
		UsersDto dto=dao.getData(id);
		mView.addObject("dto",dto);
	}
	
	@Override
	public String saveProfileImage(HttpServletRequest request,
					MultipartFile mFile) {
		//������ ������ ������ ���� ��θ� ���´�
		String realPath=request.getServletContext().getRealPath("/upload");
		//���� ���ϸ�
		String orgFileName=mFile.getOriginalFilename();
		//������ ������ �� ���
		String filePath=realPath+File.separator;
		//���丮�� ���� ���� ��ü ����
		File file=new File(filePath);
		if(!file.exists()) {//���丮�� �������� �ʴ´ٸ�
			file.mkdir();//���丮�� �����
		}
		//���� �ý��ۿ� ������ ���ϸ��� �����(��ġ�� �ʰ�)
		String saveFileName=
				System.currentTimeMillis()+orgFileName;
		try {
			//upload������ ������ �����Ѵ�
			mFile.transferTo(new File(filePath+saveFileName));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//UsersDao��ü�� �̿��ؼ� �������� �̹��� ��θ� DB�� �����ϱ�
		String path="/upload/"+saveFileName;
		//�α��ε� ���̵�
		String id=(String)
					request.getSession().getAttribute("id");
		//���̵�� �������� �̹��� ��θ� dto�� ���
		UsersDto dto=new UsersDto();
		dto.setId(id);
		dto.setProfile(path);
		//UsersDao�� �̿��ؼ� DB�� �ݿ��ϱ�
		dao.updateProfile(dto);
		
		//�̹��� ��� �������ֱ�
		return path;
	}
	@Override
	public void updatePassword(UsersDto dto, ModelAndView mView) {
		//1. ���� ��й�ȣ�� �´� �������� Ȯ��
		String pwdHash=dao.getData(dto.getId()).getPwd();
		boolean isValid=BCrypt.checkpw(dto.getPwd(), pwdHash);
		//2. ���� �´ٸ� ���� ��й�ȣ�� ��ȣȭ �ؼ� �����ϱ�
		if(isValid) {
			//�� ��й�ȣ�� ��ȣȭ �ؼ� dto �� ��� 
			String encodedPwd=new BCryptPasswordEncoder()
					.encode(dto.getNewPwd());
			dto.setPwd(encodedPwd);
			//DB �� ���� �ݿ��ϱ�
			dao.updatePwd(dto);
			mView.addObject("isSuccess", true);
		}else {
			mView.addObject("isSuccess", false);
		}
	}
}
