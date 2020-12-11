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

//@NamedQuery(query="select d from Department d where d.deptno=:deptno" , name="Department.findByDeptno")

@Entity
public class SeoulPopulation {
	@Id
	@Column(name="구분")
	private String location;
	
	@Column(name="인구")
	private Long population;
	
	@OneToMany(mappedBy="location")
	private List<SeoulCovid> seoulcovids;


}
