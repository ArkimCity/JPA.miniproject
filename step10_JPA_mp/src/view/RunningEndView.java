package view;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;
import service.SeoulCovidCRUDService;

@Slf4j

public class RunningEndView {

	// 모든 프로젝트 출력
	public static void projectListView(ArrayList allAppUsers) {
		int length = allAppUsers.size();
		if (length != 0) {
			for (int index = 0; index < length; index++) {
				System.out.println("검색정보 " + (index + 1) + " - " + allAppUsers.get(index));
			}
			log.trace("유저 전체 출력 기록");
		}
	}

	public static void projectMapView(HashMap<String, HashMap<String, Object>> hashMap) {
		int length = hashMap.size();
		if (length != 0) {
			for (String date : SeoulCovidCRUDService.getAllDates()) {
				System.out.println(date + "=" + hashMap.get(date));
			}
			log.trace("유저 전체 출력 기록");
		}
	}
	// ?? 모든 DTO 정보 출력하는 메소드
	public static void allView(Object object) {
		System.out.println(object);
		log.trace("DTO 정보 검색 출력 기록");
	}

	// 예외 상황 출력
	public static void showError(String message) {
		System.out.println(message);
		log.warn("발생된 에러 : " + message);
	}
}
