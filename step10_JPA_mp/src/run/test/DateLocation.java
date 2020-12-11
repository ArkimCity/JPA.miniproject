package run.test;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.domain.SeoulCovid;
import model.domain.SeoulPopulation;
import util.PublicCommon;

public class DateLocation {

	public static void main(String[] args) {
		System.out.println(returnMap().get("20/11/01"));
	}
	
	public static HashMap returnMap() {
		EntityManager em = PublicCommon.getEntityManger();
		EntityTransaction tx = em.getTransaction();
		ArrayList<String> locationlist = RunEmployeeCRUD.getAllLocations();
		ArrayList<String> datelist = RunEmployeeCRUD.getAllDates();
		HashMap bigmap = new HashMap();
		tx.begin();
		try {
			long beforeTime = System.currentTimeMillis();
			for(String date : datelist) {
				HashMap smallmap = new HashMap();
				for(String location : locationlist) {
					smallmap.put(location, em.createNativeQuery("select " + location + " from loctime where 확진일=?").setParameter(1, date).getSingleResult()); //29823ms
//					smallmap.put(location, em.createNamedQuery("SeoulCovid.getDateLocationCount").setParameter("caughtdate", date).setParameter("location", em.find(SeoulPopulation.class, location)).getSingleResult()); //134759ms
				}
				bigmap.put(date, smallmap);
			}
			long afterTime = System.currentTimeMillis(); 
			System.out.println(afterTime-beforeTime);
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return bigmap;
	}
}

/*
create table loctime as select 확진일, 
sum(decode(지역, '종로구',1,0)) 종로구,
sum(decode(지역, '중구',1,0)) 중구,
sum(decode(지역, '용산구',1,0)) 용산구,
sum(decode(지역, '성동구',1,0)) 성동구,
sum(decode(지역, '광진구',1,0)) 광진구,
sum(decode(지역, '동대문구',1,0)) 동대문구,
sum(decode(지역, '중랑구',1,0)) 중랑구,
sum(decode(지역, '성북구',1,0)) 성북구,
sum(decode(지역, '강북구',1,0)) 강북구,
sum(decode(지역, '도봉구',1,0)) 도봉구,
sum(decode(지역, '노원구',1,0)) 노원구,
sum(decode(지역, '은평구',1,0)) 은평구,
sum(decode(지역, '서대문구',1,0)) 서대문구,
sum(decode(지역, '마포구',1,0)) 마포구,
sum(decode(지역, '강서구',1,0)) 강서구,
sum(decode(지역, '구로구',1,0)) 구로구,
sum(decode(지역, '양천구',1,0)) 양천구,
sum(decode(지역, '금천구',1,0)) 금천구,
sum(decode(지역, '영등포구',1,0)) 영등포구,
sum(decode(지역, '동작구',1,0)) 동작구,
sum(decode(지역, '관악구',1,0)) 관악구,
sum(decode(지역, '서초구',1,0)) 서초구,
sum(decode(지역, '강남구',1,0)) 강남구,
sum(decode(지역, '송파구',1,0)) 송파구,
sum(decode(지역, '강동구',1,0)) 강동구
from seoulcovid
group by 확진일
order by 확진일 asc;
*/
