package koroler.spring.JDBCTemplate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import koroler.spring.JDBCTemplate.DAOs.PeopleDAO;
import koroler.spring.JDBCTemplate.models.Person;

@Controller
@RequestMapping ("/people")
public class PeopleController {
	@Autowired
	PeopleDAO peopleDAO;
	
	// Main page
	@GetMapping ()
	public String getList(Model model)
	{
		model.addAttribute("people", peopleDAO.getList());
		return "people/list";
	}
	
	// Person page
	@GetMapping("/{id}")
	public String getPerson(Model model, @PathVariable ("id") Integer id)
	{
		model.addAttribute("person", peopleDAO.getPerson(id));
		return "people/person";
	}
	
	//Add person
	@GetMapping ("/add")
	public String addPerson(@ModelAttribute ("person") Person person)
	{
		return "people/add";
	}
	
	@PostMapping ()
	public String submitAddPerson(@Valid Person person, BindingResult bindingResult)
	{
		//@ModelAttribute ("person")
		if (bindingResult.hasErrors())
		{
			return "people/add";
		}
		peopleDAO.addPerson(person);
		return "redirect:/people";
	}
	
	//Update person
	@GetMapping ("/{id}/edit")
	public String UpdatePerson(Model model, @PathVariable ("id") Integer id)
	{
		model.addAttribute("person", peopleDAO.getPerson(id));
		return "people/edit";
	}
	
	@PatchMapping ("/{id}")
	public String submitUpdatePerson(@ModelAttribute("person") Person person, BindingResult bindingResult, @PathVariable("id") Integer id)
	{
		if (bindingResult.hasErrors())
		{
			return "people/edit";
		}
		//System.out.println(person + "id : " + id);
		peopleDAO.updatePerson(id, person);
		return "redirect:/people/{id}"; // "redirect:/people";
	}
	
	//Delete person
	@DeleteMapping ("/{id}")
	public String submitDeletePerson(@PathVariable("id") Integer id)
	{
		peopleDAO.murderPerson(id);
		return "redirect:/people";
	}
}