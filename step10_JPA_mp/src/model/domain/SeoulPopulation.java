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

@NamedQuery(query="select p from SeoulPopulation p where p.location=:location" , name="SeoulPopulation.location")
@NamedQuery(query="select p.location from SeoulPopulation p" , name="SeoulPopulation.locations")

@Entity
public class SeoulPopulation {
	@Id
	@Column(name="구분")
	private String location;

	@Column(name="인구")
	private Long population;
	
	@OneToMany(mappedBy="location")
	private List<SeoulCovid> seoulcovids;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SeoulPopulation [location=");
		builder.append(location);
		builder.append(", population=");
		builder.append(population);
		builder.append("]");
		return builder.toString();
	}
}
