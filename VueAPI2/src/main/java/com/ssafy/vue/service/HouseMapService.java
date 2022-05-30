package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> searchByAptname(String aptname) throws Exception;
	List<HouseInfoDto> searchByAptcode(int aptcode) throws Exception;
	String aptHighest(String aptname) throws Exception;
	String aptLowest(String aptname) throws Exception;
	boolean aptHit(int aptCode) throws Exception;
	
}
