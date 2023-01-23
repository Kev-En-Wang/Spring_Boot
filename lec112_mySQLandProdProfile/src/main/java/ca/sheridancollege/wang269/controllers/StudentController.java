package ca.sheridancollege.wang269.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.wang269.beans.Student;
import ca.sheridancollege.wang269.database.DatabaseAccess;

@Controller
public class StudentController{
	
	@Autowired
	DatabaseAccess da;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("student", new Student());
		model.addAttribute("studentList", da.findAll());	
		return "index";
	}
	
	@PostMapping("/insertStudent")
	public String insertStudent(Model model, @ModelAttribute Student student) {
		
		da.save(student.getName());
		
		model.addAttribute("student", new Student());
		model.addAttribute("studentList", da.findAll());
		
		return "index";
	}
}