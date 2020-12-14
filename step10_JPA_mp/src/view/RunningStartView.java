/*
		System.out.println("***** 모든 진행되는 실제 Project 검색 *****");
		ProbonoProjectController.getAllProbonoProjects();
		System.out.println();
		// 모든 재능 기부자들 검색
		System.out.println("\n***** 모든 재능 기부자 검색 *****");
		ProbonoProjectController.getAllActivists();

		// 특정 프로보노 정보 검색
		System.out.println("\n***** 특정 프로보노 정보 검색 *****");
		ProbonoProjectController.getProbono("schaaaaaaaaaaaaaweitzer");
		ProbonoProjectController.getProbono("schweitzer");
		// 프로보노 id로 프로보노 목적 수정 - test data - id : schweitzer, 목적 : 애완동물사랑
		System.out.println("\n***** 특정 프로보노 정보 수정후 재 검색 *****");
		ProbonoProjectController.updateProbono("schweitzer", "애완동물사랑");
		ProbonoProjectController.getProbono("schweitzer");
 */
package view;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import api.JsonSimpleAPI;
import controller.SeoulCovidController;
import service.DateLocationService;
import util.PublicCommon;

public class RunningStartView {

	public static void main(String[] args) {
		//기본 CRUD
//		SeoulCovidController.createSeoulCovid(10000, "재웅 통해서 감염", "20/12/11", "강서구");
//		SeoulCovidController.findElement(10000);
//		SeoulCovidController.updateSeoulCovid(10000, "온라인 상에서 감염");
//		SeoulCovidController.findElement(10000);
//		SeoulCovidController.deleteElement(10000);
		
//		SeoulCovidController.getDateLocationSum();
//		SeoulCovidController.findDateLocation("20/11/01");
		
		//인접한 지역 테이블 만들기
		SeoulCovidController.setLocRelations();
		
		SeoulCovidController.getChartGraph("마포구");
		
	}
}

