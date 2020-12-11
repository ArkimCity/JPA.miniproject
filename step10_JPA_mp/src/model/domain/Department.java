package model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

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

@NamedQuery(query="select d from Department d where d.deptno=:deptno" , name="Department.findByDeptno")

@Entity
public class Department {
	@Id
	@Column
	private Long deptno;
	
	@Column
	private String dName;
	
	@Column
	private String loc;
	
	@OneToMany(mappedBy="deptno")
	private List<Employee> employees;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Department [deptno=");
		builder.append(deptno);
		builder.append(", dName=");
		builder.append(dName);
		builder.append(", loc=");
		builder.append(loc);
		builder.append(", employees=");
		builder.append(employees);
		builder.append("]");
		return builder.toString();
	}
}
