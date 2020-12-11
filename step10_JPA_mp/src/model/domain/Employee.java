package model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@NamedQuery(query="select e from Employee e where e.empno=:empno" , name="Employee.findByEmpno")

@Entity
public class Employee {

	@Id
	@Column
	private Long empno;
	
	@Column
	private String ename;
	
	@Column
	private String job;
	
	@Column
	private Long mgr;
	
	@Column
	private Date hiredate;
	
	@Column
	private Long sal;
	
	@Column
	private Long comm;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "deptno")
	private Department deptno;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [empno=");
		builder.append(empno);
		builder.append(", ename=");
		builder.append(ename);
		builder.append(", job=");
		builder.append(job);
		builder.append(", mgr=");
		builder.append(mgr);
		builder.append(", hiredate=");
		builder.append(hiredate);
		builder.append(", sal=");
		builder.append(sal);
		builder.append(", comm=");
		builder.append(comm);
		builder.append(", deptno=");
		builder.append(deptno.getDeptno());
		builder.append("]");
		return builder.toString();
	}
	
	
}