package ca.sheridancollege.wang269.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.wang269.beans.Student;

@Repository
public class DatabaseAccess{
	
	@Autowired
	NamedParameterJdbcTemplate jdbc;
	
	public List<Student> findAll(){
		String query = "SELECT * FROM student";
		return jdbc.query(query, new BeanPropertyRowMapper<Student>(Student.class));
	}
	
	public void save(String name) {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		
		namedParameter.addValue("name",name);
		
		String query="INSERT INTO student(name) VALUES(:name)";
		
		int rowsAffected = jdbc.update(query, namedParameter);
		if(rowsAffected>0) {
			System.out.println("Inserted appointment into database");
		}
	}
	
	
}