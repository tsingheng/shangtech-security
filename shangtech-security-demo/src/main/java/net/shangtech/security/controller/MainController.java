package net.shangtech.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/")
	public String main(){
		return "redirect:/security/user/list";
	}
	
}
