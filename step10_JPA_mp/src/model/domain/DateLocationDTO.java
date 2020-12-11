package model.domain;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DateLocationDTO {
	
	private String Date;
	
	private HashMap<String, Long> locations;
}
