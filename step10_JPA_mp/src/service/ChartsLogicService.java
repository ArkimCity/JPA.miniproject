package service;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import api.TimeSeriesAPI;
import util.PublicCommon;

public class ChartsLogicService {
	
	private ChartsLogicService() {
	}
	
	public static void getChartsGraph(String location) {
		TimeSeriesAPI.getChartGraph(location);
	}
	
	public static ArrayList<String> getLocRelations(String location) {
		EntityManager em = PublicCommon.getEntityManger();
		EntityTransaction tx = em.getTransaction();
		ArrayList<String> locations = new ArrayList<String>();
		tx.begin();
		try {
			locations = (ArrayList<String>) em.createNamedQuery("SeoulLocRelations.locations").setParameter("location", location).getResultList();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return locations;
	}
	
	public static ArrayList getNumbers(String location) {
		EntityManager em = PublicCommon.getEntityManger();
		EntityTransaction tx = em.getTransaction();
		ArrayList numbers = new ArrayList();
		tx.begin();
		try {
			numbers = (ArrayList) em.createNativeQuery("select " + location + " from loctime").getResultList();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return numbers;
	}
}
