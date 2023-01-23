package ca.sheridancollege.wang269.database;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.wang269.beans.Airplane;

@Repository
public class DatabaseAccess{
	@Autowired
	NamedParameterJdbcTemplate jdbc;
	
	public void insertAirplane(String airplane, String manufacturer, String propulsion, LocalDate deliveryDate, LocalTime deliveryTime ) {
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		//This adds all the parameters to the thing that's going to be added
		namedParameters.addValue("airplane",airplane);
		namedParameters.addValue("manufacturer",manufacturer);
		namedParameters.addValue("propulsion",propulsion);
		namedParameters.addValue("deliveryDate",deliveryDate);
		namedParameters.addValue("deliveryTime",deliveryTime);
		
		//This is the query that will run
		String query = "INSERT INTO airplane(airplane, manufacturer, propulsion, deliveryDate, deliveryTime) "
				+ "VALUES(:airplane, :manufacturer, :propulsion, :deliveryDate, :deliveryTime)";
		
		System.out.println("");
		//This runs the query
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0) 
			System.out.println("Inserted airplane into database.");
	}
	
	//Selects all the airplanes. Doesn't need to map since it's a no args
	public List<Airplane> getAllAirplanes(){
		String query = "SELECT * FROM airplane";
		return jdbc.query(query, new BeanPropertyRowMapper<Airplane>(Airplane.class));
	}
	
	//Delete method
	public void deleteAirplane(int id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM airplane WHERE id = :id";
		namedParameters.addValue("id", id);
		
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0) 
			System.out.println("Airplane "+id+" Deleted");
	}
	
	//Get the airplane by ID
	public List<Airplane> getAirplaneById(int id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		String query = "SELECT * FROM airplane WHERE id = :id";
		return jdbc.query(query,namedParameters, new BeanPropertyRowMapper<Airplane>(Airplane.class));
	}
	
}