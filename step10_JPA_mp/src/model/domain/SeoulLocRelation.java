package model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

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

@NamedQuery(query="select p.alocation from SeoulLocRelation p where p.plocation=:location" , name="SeoulLocRelations.locations")

@Entity
@SequenceGenerator(name = "LR_seq_gen", sequenceName = "LR_seq_id", initialValue = 1, allocationSize=1)
public class SeoulLocRelation {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LR_seq_gen")
	@Column(name = "연결번호")
	private Long index;
	
	@Column(name = "지역")
	private String plocation;
	
	@Column(name = "인접지역")
	private String alocation;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[지역=");
		builder.append(plocation);
		builder.append(", 인접지역=");
		builder.append(alocation);
		builder.append("]");
		return builder.toString();
	}
}

/*
DROP TABLE seoullocrelation;

CREATE TABLE seoullocrelation(
	연결번호 NUMBER primary key,
	지역 VARCHAR2 (50),
	인접지역 VARCHAR2 (50)
);

COMMIT;
*/