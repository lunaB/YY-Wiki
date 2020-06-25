package xyz.lunab.yywiki.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("list")
public class WikiListController {
	
	private static final Logger logger = LoggerFactory.getLogger(WikiListController.class);
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String home(@RequestParam(value = "loginFailed", required = false) String loginFailed, Model model) {
		
		if(loginFailed != null) {
			model.addAttribute("loginFailed", true);
		}
		return "/list/home";
	}
}
