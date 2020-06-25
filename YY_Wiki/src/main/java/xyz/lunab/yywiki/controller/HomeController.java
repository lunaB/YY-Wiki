package xyz.lunab.yywiki.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xyz.lunab.yywiki.util.UrlUtil;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@RequestParam(value = "loginSucceed", required = false) String loginSucceed, 
			@RequestParam(value = "loginSucceed", required = false) String logoutSucceed, RedirectAttributes rttr) throws UnsupportedEncodingException {
		
		logger.info("/");
		
		if(loginSucceed != null) {
			rttr.addFlashAttribute("loginSucceed", true);
		}
		else if(logoutSucceed != null) {
			rttr.addFlashAttribute("logoutSucceed", true);
		}
		
		return "redirect:/wiki/" + UrlUtil.urlEncode("양디위키:대문");
	}
	
}
