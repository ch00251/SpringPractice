package com.yunju.spring05.member.dao;

import java.util.List;

import com.yunju.spring05.member.dto.MemberDto;

public interface MemberDao {
	public List<MemberDto> getList();
}
