package ca.sheridancollege.wang269.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ca.sheridancollege.wang269.beans.Airplane;
import ca.sheridancollege.wang269.database.DatabaseAccess;

@Controller
public class AirplaneController{
	
	@Autowired
	private DatabaseAccess da;
	
	@GetMapping("/")
	public String index(Model model) {
		
		//Make the model and upload the model
		model.addAttribute("airplane",new Airplane());
		model.addAttribute("airplaneList", da.getAllAirplanes());
		Airplane airplane = da.getAllAirplanes().get(0);
		System.out.println(airplane);
		
		return "index";
	}
	
	@PostMapping("/insertAirplane")
	public String insertAirplane(Model model, @ModelAttribute Airplane airplane) {
		
		//Inserting into the database
		da.insertAirplane(airplane.getAirplane(), airplane.getManufacturer(),airplane.getPropulsion(), 
				airplane.getDeliveryDate(), airplane.getDeliveryTime());
		
		//Inserting into the model
		model.addAttribute("airplane", new Airplane());
		model.addAttribute("airplaneList", da.getAllAirplanes());
		
		return "index";
	}
	
	@GetMapping("/editAirplane/{id}")
	public String editAirplane(Model model, @PathVariable int id) {
		Airplane airplane = da.getAirplaneById(id).get(0);
		System.out.println(airplane);
		//This updates the form for editing BEFORE we delete the entry
		model.addAttribute("airplane",airplane);
		
		da.deleteAirplane(id);
		model.addAttribute("airplaneList",da.getAllAirplanes());
		return "index";
	}
	
	@GetMapping("/deleteAirplane/{id}")
	public String deleteAirplane(Model model, @PathVariable int id) {
		da.deleteAirplane(id);
		model.addAttribute("airplane",new Airplane());
		model.addAttribute("airplaneList",da.getAllAirplanes());
		return "index";
	}
}