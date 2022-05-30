package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.MemberDto;
import com.ssafy.vue.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if (memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return memberMapper.login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return memberMapper.userInfo(userid);
	}

	@Override
	public boolean deleteUser(String userid) {
		return memberMapper.deleteUser(userid) == 1;
	}

	@Override
	public boolean updateUser(MemberDto userInfo) throws Exception {
		return memberMapper.updateUser(userInfo) == 1;
	}

	@Override
	public List<MemberDto> getUserlist() throws Exception {	
		return memberMapper.getUserlist();
	}

	@Override
	public int checkId(String userid) throws Exception {
		return memberMapper.checkId(userid);
	}

	@Override
	public boolean registerUser(MemberDto userInfo) throws Exception {
		return memberMapper.registerUser(userInfo) == 1;
	}

}
