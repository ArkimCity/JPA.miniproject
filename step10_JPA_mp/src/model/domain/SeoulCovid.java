package model.domain;



import java.sql.Date;

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

@NamedQuery(query="select c from SeoulCovid c where c.patientnumber=:patientnumber" , name="SeoulCovid.findByPbumber")

@Entity
public class SeoulCovid {

	@Id
	@Column(name="연번")
	private Long patientnumber;
	
	@Column(name="접촉력")
	private String history;
	
	@Column(name="확진일")
	private String caughtdate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "지역") // (이 테이블에선 이게 지역 인데 이 부분을 어떻게 해야 할까나)
	private SeoulPopulation location;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SeoulCovid [patientnumber=");
		builder.append(patientnumber);
		builder.append(", history=");
		builder.append(history);
		builder.append(", caughtdate=");
		builder.append(caughtdate);
		builder.append(", location=");
		builder.append(location.getLocation());
		builder.append("]");
		return builder.toString();
	}
}