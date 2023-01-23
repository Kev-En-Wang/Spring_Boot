package ca.sheridancollege.wang269.database;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.wang269.beans.Contact;

@Repository
public class DatabaseAccess{
	
	@Autowired
	NamedParameterJdbcTemplate jdbc;
	
	public List<Contact> findAll(){
		String query = "SELECT * FROM contact";
		return jdbc.query(query, new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	
	public void save(String firstName, String lastName, int phoneNumber, LocalDate birthday) {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		
		namedParameter.addValue("firstName",firstName);
		namedParameter.addValue("lastName",lastName);
		namedParameter.addValue("phoneNumber",phoneNumber);
		namedParameter.addValue("birthday",birthday);
		
		String query="INSERT INTO contact(firstName, lastName, phoneNumber, birthday)"
				+ " VALUES(:firstName, :lastName, :phoneNumber, :birthday)";
		
		int rowsAffected = jdbc.update(query, namedParameter);
		if(rowsAffected>0) {
			System.out.println("Inserted appointment into database");
		}
	}
	
	
}