package xyz.lunab.yywiki.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.lunab.yywiki.domain.UserDetailsVO;
import xyz.lunab.yywiki.domain.WikiContentVO;
import xyz.lunab.yywiki.dto.EditDTO;
import xyz.lunab.yywiki.dto.WikiWrapperDTO;
import xyz.lunab.yywiki.reCaptcha.VerifyRecaptcha;
import xyz.lunab.yywiki.service.WikiService;
import xyz.lunab.yywiki.util.PermissionUtil;
import xyz.lunab.yywiki.util.UrlUtil;
import xyz.lunab.yywiki.util.XssUtil;


@Controller
@RequestMapping("/edit")
public class EditController {
	
	private static final Logger logger = LoggerFactory.getLogger(WikiController.class);
	
	@Inject
	public WikiService wikiService;
	
	@RequestMapping(value="/{value}", method=RequestMethod.GET)
	public String editPath(@PathVariable String value, Model model, Authentication authentication) throws UnsupportedEncodingException {
		// 불순 문자셋을 사용
		if(!XssUtil.xssEncode(value).equals(value)) {
			return "redirect:/auth/e";
		}
		
		WikiWrapperDTO dto = wikiService.selectWiki(value);
		
		// 문서 유효성 검사
		if(dto == null) {
			return "redirect:/wiki/" + UrlUtil.urlEncode(value);
		}
		
		// 권한이 있는가
		int permissionId = 1;
		UserDetailsVO userVO = null;
		if(authentication != null) {
			userVO = (UserDetailsVO) authentication.getPrincipal();
			permissionId = PermissionUtil.check(userVO.getAuthorities().toArray()[0].toString());	
		}
		if(permissionId < dto.getPermission_id()) {
			logger.warn("편집 권한 없음");
			return "redirect:/auth/e";
		}
		
		model.addAttribute("wiki", dto);
		
		logger.info(value);
		
		return "/wiki/edit";
	}
	
	@RequestMapping(value="/{value}", method=RequestMethod.POST)
	public String editProcess(HttpServletRequest request, @PathVariable String value, EditDTO dto, Model model, Authentication authentication, HttpServletRequest req) throws UnsupportedEncodingException {
		
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        
        try {
            if(!VerifyRecaptcha.verify(gRecaptchaResponse))
            	return "redirect:/wiki/%EC%96%91%EB%94%94%EA%B3%A0%EC%B9%9C%EA%B5%AC%EB%93%A4%E3%85%A0%20%ED%85%8C%EB%9F%AC%EC%A2%80%ED%95%98%EC%A7%80%EB%A7%88";
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/auth/e";
        }
		
		// 불순 문자셋을 사용했을경우
		if(!XssUtil.xssEncode(value).equals(value)) {
			logger.warn("불순 문자셋 사용됨");
			return "redirect:/auth/e";
		}
		
		// summary가 없을경우 (비정상 요청)
		if(dto.getSummary() == null) {
			logger.warn("Summary 누락");
			return "redirect:/edit/" + UrlUtil.urlEncode(value);
		}
		
		dto.setContent(dto.getContent());
		dto.setSummary(dto.getSummary());
		
		logger.info(dto.toString());
		
		WikiContentVO contentVO = new WikiContentVO();
		
		WikiWrapperDTO wikidto = wikiService.selectWiki(value);
		
		/*
		 * TODO 나중에 flashAttr 추가
		 */
		if(dto.getContent().length() >= 50000) {
			// 정해진 길이를 초과한 경우
			logger.warn("Content 길이 초과");
			return "redirect:/edit/" + UrlUtil.urlEncode(value);
		}
		else if(dto.getSummary().length() >= 20) {
			// 정해진 길이를 초과한 경우
			logger.warn("Summary 길이 초과");
			return "redirect:/edit/" + UrlUtil.urlEncode(value);
		}
		else if(dto.getContent().equals(wikidto.getContent())) {
			// 변경사항이 없는경우
			logger.warn("변경 사항 없음");
			return "redirect:/edit/" + UrlUtil.urlEncode(value);
		}
		contentVO.setWiki_id(dto.getWiki_id());
		contentVO.setContent(XssUtil.xssEncode(dto.getContent()));
		
		// 권한 체크
		int permissionId = 1;
		UserDetailsVO userVO = null;
		if(authentication != null) {
			userVO = (UserDetailsVO) authentication.getPrincipal();
			permissionId = PermissionUtil.check(userVO.getAuthorities().toArray()[0].toString());
			contentVO.setUser_id(userVO.getUsername());	
		}
		
		if(permissionId < wikidto.getPermission_id()) {
			logger.warn("편집 권한 없음");
			return "redirect:/auth/e";
		}
	
		
		// ip 추가
		String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
        	ip = req.getRemoteAddr();
        }
        contentVO.setUser_ip(ip);
		contentVO.setSummary(dto.getSummary());
		
		wikiService.insertContent(contentVO);
		
		return "redirect:/wiki/" + UrlUtil.urlEncode(value);
	}
	
}
