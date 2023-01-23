package ca.sheridancollege.wang269.beans;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

//POJO for airplane
public class Airplane {
	private int id;
	private String airplane;
	private String propulsion;
	private String manufacturer;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate deliveryDate;
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime deliveryTime;
}