package com.udemy.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.udemy.constant.ViewCostant;

@Controller
public class LoginController {
	
	private static final Log LOG = LogFactory.getLog(LoginController.class);
	
	//http://localhost:8080
//	@GetMapping("/")
//	public String redirectToLogin(){
//		LOG.info("Método redirectToLogin");
//		return "redirect:/login";
//	}
	
	@GetMapping("/login")
	public String showLoginForm(Model model, 
			@RequestParam(name="error", required=false) String error,
			@RequestParam(name="logout", required=false) String logout){
		LOG.info("Método showLoginForm con parametros: error -- "+error+" y logout -- "+logout);
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		//model.addAttribute("userCredentials", new UserCredential());
		LOG.info("Retornamos a la vista login");
		return ViewCostant.LOGIN;
	}

//	@PostMapping("/logincheck")
//	public String loginCheck(@ModelAttribute(name="userCredentials") UserCredential userCredential){
//		LOG.info("Método loginCheck con parametro: userCredential -- "+userCredential.toString());
//		
//		if(userCredential.getUserName().equals("user")
//				&& userCredential.getPassword().equals("user")){
//			LOG.info("Retornamos a la vista contacts");
//			return "redirect:/contacts/showcontacts";
//		}
//		
//		LOG.info("Retornamos a la vista login con error");
//		return "redirect:/login?error";
//	}
	
	@GetMapping({"/loginsuccess","/"})
	public String loginCheck(){
		LOG.info("Retornamos a la vista contacts");
		return "redirect:/contacts/showcontacts";
	}
}
