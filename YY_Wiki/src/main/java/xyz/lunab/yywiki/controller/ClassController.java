package xyz.lunab.yywiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/class")
public class ClassController {
	
	@RequestMapping(value="/{value}", method=RequestMethod.GET)
	public String classRoot(@PathVariable String value) {
		return "";
	}
	
}
