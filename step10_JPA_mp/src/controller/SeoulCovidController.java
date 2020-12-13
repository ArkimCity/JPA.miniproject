package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import api.JsonSimpleAPI;
import service.SeoulCovidCRUDService;
import service.ChartsLogicService;
import service.DateLocationService;
import exception.NotExistException;
import view.RunningEndView;

//현 로직 : view.RunningStrartView에서 호출 
public class SeoulCovidController {
	// ----------------------------차트 관련 로직----------------------------
	public static void getChartGraph(String location) {
		ChartsLogicService.getChartsGraph(location);
	}
	
	
	// ----------------------------Date/Location 관련 로직----------------------------
	static HashMap<String, HashMap<String, Object>> map = DateLocationService.getDLSum();
	public static void getDateLocationSum() {
		try {
			RunningEndView.projectMapView(map);
		} catch (Exception e) {
			RunningEndView.showError("코로나 기록 검색시 에러 발생");
			e.printStackTrace();
		}
	}
	
	public static void findDateLocation(String Date) {
		try {
			RunningEndView.allView(map.get(Date));
		} catch (Exception e) {
			RunningEndView.showError("코로나 기록 검색시 에러 발생");
			e.printStackTrace();
		}
	}
	
	public static void setLocRelations() {
		String content = DateLocationService.getFileContent("skorea_municipalities_geo_simple.json");
		DateLocationService.detect(DateLocationService.mapping(JsonSimpleAPI.toJSONArray(content)));
	}
	
	// ----------------------------Covid table  CRUD 관련 로직----------------------------
	public static void findElement(int index) {
		try {
			RunningEndView.allView(SeoulCovidCRUDService.findElement(index));
		} catch (Exception e) {
			RunningEndView.showError("코로나 기록 검색시 에러 발생");
			e.printStackTrace();
		}
	}

	public static void updateSeoulCovid(int index, String content) {
		try {
			SeoulCovidCRUDService.updateSeoulCovid(index, content);;
		} catch (Exception e) {
			RunningEndView.showError("연번으로 코로나 기록 내용 변경중 에러 발생");
			e.printStackTrace();
		}
	}
	
	public static void createSeoulCovid(int index, String content, String date, String location) {
		try {
			SeoulCovidCRUDService.createSeoulCovid(index, content, date, location);
		} catch (Exception e) {
			RunningEndView.showError("코로나 기록 추가시 에러 발생");
			e.printStackTrace();
		}
	}

	public static void deleteElement(int index) {
		try {
			SeoulCovidCRUDService.deleteElement(index);
		} catch (Exception e) {
			RunningEndView.showError("코로나 기록 삭제시 에러 발생");
			e.printStackTrace();
		}
	}
}