package ca.sheridancollege.wang269.beans;


import java.time.LocalDate;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Contact{
	private long id;
	private String firstName;
	private String lastName;
	private int phoneNumber;
	private LocalDate birthday;
}