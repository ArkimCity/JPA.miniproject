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

//@NamedQuery(query="select e from Employee e where e.empno=:empno" , name="Employee.findByEmpno")

@Entity
public class SeoulCovid {

	@Id
	@Column(name="연번")
	private Long patientnumber;
	
	@Column(name="접촉력")
	private String history;
	
	@Column(name="확진일")
	private Date caughtdate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "지역") // (이 테이블에선 이게 지역 인데 이 부분을 어떻게 해야 할까나)
	private SeoulPopulation location;
	
}