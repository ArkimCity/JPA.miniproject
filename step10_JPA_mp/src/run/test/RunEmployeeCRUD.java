//alter table department add primary key(deptno);
//alter table employee add primary key(empno);
//alter table employee add constraints employee_deptno_fk foreign key (deptno) references dept(deptno);


package run.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import model.domain.SeoulPopulation;
import model.domain.SeoulCovid;
import util.PublicCommon;

@Slf4j

public class RunEmployeeCRUD {

	public static void main(String[] args) {
		createEmployee();
		updateEmployee();
		findElement();
		deleteElement();
	}
	
	public static void createEmployee() {
		EntityManager em = PublicCommon.getEntityManger();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
//			Department d50 = Department.builder().deptno(50L).dName("Technical Manager").loc("SEOUL").build();
//			
//			em.persist(d50);
			SeoulPopulation d50 = (SeoulPopulation) em.createNamedQuery("Department.findByDeptno").setParameter("deptno", 50L).getSingleResult();
//			Department d50 = em.find(Department.class, 50L);

			SeoulCovid employee = SeoulCovid.builder().empno(1201L).ename("Gopal").sal(40000L).deptno(d50).build();

			em.persist(employee);

			tx.commit();
			log.warn("생성 기록");
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void updateEmployee() {
		EntityManager em = PublicCommon.getEntityManger();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			SeoulCovid employee = em.find(SeoulCovid.class, 1201L);

			System.out.println("update 전 : " + employee);
			employee.setSal(46000L);
			tx.commit();
			log.warn("업데이트 기록");
			// after update
			System.out.println("update 후 : " + employee);
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void findElement() {
		EntityManager em = PublicCommon.getEntityManger();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			SeoulCovid employee = (SeoulCovid) em.createNamedQuery("Employee.findByEmpno").setParameter("empno", 1201L).getSingleResult();
//			Employee employee = em.find(Employee.class, 1201L);

			if (employee != null) {
				System.out.println(employee);
			} else {
				System.out.println("검색 요청한 직원은 미존재합니다");
			}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void deleteElement() {
		EntityManager em = PublicCommon.getEntityManger();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			SeoulCovid employee = em.find(SeoulCovid.class, 1201L);
			em.remove(employee);

			tx.commit();
			log.warn("삭제 기록");
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
}
