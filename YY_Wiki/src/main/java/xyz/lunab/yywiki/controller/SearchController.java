package xyz.lunab.yywiki.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import xyz.lunab.yywiki.reCaptcha.VerifyRecaptchaInvisible;
import xyz.lunab.yywiki.service.WikiService;
import xyz.lunab.yywiki.util.UrlUtil;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@Inject
	public WikiService wikiService;
	
	@RequestMapping(value="/{value:.+}", method=RequestMethod.GET)
	public String wikiSearch(@PathVariable String value, HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		
//		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
//        
//        try {
//            if(!VerifyRecaptchaInvisible.verify(gRecaptchaResponse))
//            	return "redirect:/wiki/%EC%96%91%EB%94%94%EA%B3%A0%EC%B9%9C%EA%B5%AC%EB%93%A4%E3%85%A0%20%ED%85%8C%EB%9F%AC%EC%A2%80%ED%95%98%EC%A7%80%EB%A7%88";
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "redirect:/auth/e";
//        }
		
		logger.info(request.getHeader("X-FORWARDED-FOR")+" "+value);
		return "redirect:/wiki/" + UrlUtil.urlEncode(value);
	}
}
