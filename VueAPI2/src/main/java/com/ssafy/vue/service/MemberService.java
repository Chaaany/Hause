package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;

	public MemberDto userInfo(String userid) throws Exception;

	public boolean deleteUser(String userid) throws Exception;

	public boolean updateUser(MemberDto userInfo) throws Exception;

	public List<MemberDto> getUserlist() throws Exception;

	public int checkId(String userid) throws Exception;

	public boolean registerUser(MemberDto userInfo) throws Exception;

}
