package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.MemberDto;


public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public int deleteUser(String userid) ;
	public int updateUser(MemberDto userInfo);
	public List<MemberDto> getUserlist();
	public int checkId(String userid);
	public int registerUser(MemberDto userInfo);
	
}