package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.service.HouseMapService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/map")
@CrossOrigin("*")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private HouseMapService houseMapService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
//		logger.debug("sido : {}", haHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
//		logger.debug("gugun : {}", haHouseMapService.getGugunInSido(sido));
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getAptInDong(dong), HttpStatus.OK);
	}
	@GetMapping("/searchbyaptname")
	public ResponseEntity<List<HouseInfoDto>> searchByAptname(@RequestParam("aptname") String aptname) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.searchByAptname(aptname), HttpStatus.OK);
	}
	
	@GetMapping("/searchbyaptcode")
	public ResponseEntity<List<HouseInfoDto>> searchByAptcode(@RequestParam("aptcode") int aptcode) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.searchByAptcode(aptcode), HttpStatus.OK);
	}
	
	@ApiOperation(value = "아파트 최고 거래가 검색", notes = "아파트 이름을 검색하여 해당 아파트의 최고 거래가를 반환한다.", response = String.class)
	@GetMapping("/highest")
	public ResponseEntity<String> aptHighest(@RequestParam("aptname") String aptname) throws Exception {
		logger.debug("aptHighest - 호출");
		return new ResponseEntity<String>(houseMapService.aptHighest(aptname), HttpStatus.OK);
	}
	
	@ApiOperation(value = "아파트 최저 거래가 검색", notes = "아파트 이름을 검색하여 해당 아파트의 최고 거래가를 반환한다.", response = String.class)
	@GetMapping("/lowest")
	public ResponseEntity<String> aptLowest(@RequestParam("aptname") String aptname) throws Exception {
		logger.debug("aptLowest - 호출");
		return new ResponseEntity<String>(houseMapService.aptLowest(aptname), HttpStatus.OK);
	}
	
	 @ApiOperation(value = "아파트 조회수 증가", notes = "해당하는 아파트의 조회수를 증가시킨다.", response = String.class)
		@PutMapping("/hit/{aptCode}")
		public ResponseEntity<String> aptHit(@PathVariable int aptCode) throws Exception {
			logger.debug("aptHit - 호출");
			if (houseMapService.aptHit(aptCode)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
}
