package com.udemy.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.udemy.constant.ViewCostant;
import com.udemy.model.ContactModel;
import com.udemy.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	
	private static final Log LOG = LogFactory.getLog(ContactController.class);
	
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;
	
	//PreAuthorize("hasRole('ADMIN')")//Con este rol, al no estar dado de alta se va a la pagina 403(no autorizado)
	//PreAuthorize("hasRole('ADMIN' or hasRole('ROLE_USER'))")//Si se quiere dejar paso a ambos roles(dados de alta)
	@GetMapping("/contactform")
	@PreAuthorize("hasRole('ROLE_USER')")//Para solo dejar paso a este rol
	private String redirectContactForm(@RequestParam(name="id", required=false)int id, Model model){
		ContactModel contactmodel = contactService.findContactByIdModel(id);
		if(id!=0){//Venimos del modificar
			model.addAttribute("contactModel", contactmodel);
		}else{//Venimos del alta
			model.addAttribute("contactModel", new ContactModel());
		}
		
		return ViewCostant.CONTACT_FORM;
	}
	
	@GetMapping("/cancel")
	private String cancel(){
		return "redirect:/contacts/showcontacts";
	}
	
	@PostMapping("/addcontact")
	private String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel,
			Model model){
		LOG.info("Método addContact con parametro: contactModel -- "+contactModel.toString());
		
		//Si ha ido bien devolvemos 1, hemos dado de alta ok
		if(null != contactService.addContact(contactModel) && contactModel.getId()!=0){
			model.addAttribute("result", 1);
		}else{
			model.addAttribute("result", 0);
		}
		
		return "redirect:/contacts/showcontacts";
		
		
	}
	
	@GetMapping("/showcontacts")
	public ModelAndView showContacts(){
		ModelAndView mav = new ModelAndView(ViewCostant.CONTACTS);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("username", user.getUsername());
		mav.addObject("contacts", contactService.listAllContacts());
		return mav;
	}
	
	@GetMapping("/removecontact")
	private ModelAndView removeContact(@RequestParam(name="id", required=true)int id){
		contactService.removeContact(id);
		return showContacts();
	}
}
