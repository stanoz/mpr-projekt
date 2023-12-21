package com.example.mpr_frontend.controllers;

import com.example.mpr_frontend.dtos.Person;
import com.example.mpr_frontend.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.Objects;

@Controller
public class WebController {
    private final PersonService personService;
    @Autowired
    public WebController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping(value = "/welcome")
    public String getWelcomeView(){
        return "welcome";
    }
    @GetMapping(value = "/showAll")
    public String getIndexView(Model model){
        model.addAttribute("persons", personService.getAll());
        return "index";
    }
//    @GetMapping(value = "/deletePerson/{id}")
//    public String getDeletePersonManager(Model model, @PathVariable("id") Long id){
//        model.addAttribute("person", personService.getPersonById(id));
//        return "deletePerson";
//    }
    @GetMapping(value = "/editPerson/{id}")
    public String getEditPersonManager(Model model, @PathVariable("id") Long id){
        model.addAttribute("person", personService.getPersonById(id));
        return "editPerson";
    }
//    @RequestMapping(value = "/deletePerson/{id}", method = RequestMethod.POST)
//    public String deletePerson(@ModelAttribute Person person, Model model, @PathVariable("id") Long id){
//        personService.deletePersonById(person.getId());
//        return "redirect:/showAll";
//    }
    @RequestMapping(value = "/editPerson/{id}", method = RequestMethod.POST)
    public String editPerson(@ModelAttribute Person person, Model model, @PathVariable("id") Long id){
        personService.editPerson(person);
        return "redirect:/showAll";
    }
    @GetMapping(value = "/addPerson")
    public String getAddPersonManager(Model model){
        model.addAttribute("person", new Person());
        return "addPerson";
    }
    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute Person person, Model model){
        personService.addPerson(person);
        return "redirect:/showAll";
    }
}
