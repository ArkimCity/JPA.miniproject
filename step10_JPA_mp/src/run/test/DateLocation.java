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
			for(String date : datelist) {
				HashMap smallmap = new HashMap();
				for(String location : locationlist) {
					smallmap.put(location, em.createNamedQuery("SeoulCovid.getDateLocationCount").setParameter("caughtdate", date).setParameter("location", em.find(SeoulPopulation.class, location)).getSingleResult());
				}
				bigmap.put(date, smallmap);
			}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return bigmap;
	}
}
