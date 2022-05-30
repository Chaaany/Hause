package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;

public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	List<HouseInfoDto> searchByAptname(String aptname) throws Exception;
	List<HouseInfoDto> searchByAptcode(int aptcode) throws Exception;
	String aptHighest(String aptname) throws Exception;
	String aptLowest(String aptname) throws Exception;
	int aptHit(int aptCode) throws Exception;
}
