package xyz.lunab.yywiki.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
import xyz.lunab.yywiki.domain.WikiVO;
import xyz.lunab.yywiki.dto.WikiHeadDTO;
import xyz.lunab.yywiki.dto.WikiWrapperDTO;
import xyz.lunab.yywiki.reCaptcha.VerifyRecaptcha;
import xyz.lunab.yywiki.service.WikiService;
import xyz.lunab.yywiki.util.PermissionUtil;
import xyz.lunab.yywiki.util.SqlInjectionUtil;
import xyz.lunab.yywiki.util.UrlUtil;
import xyz.lunab.yywiki.util.XssUtil;

@Controller
@RequestMapping("/wiki")
public class WikiController {
	
	private static final Logger logger = LoggerFactory.getLogger(WikiController.class);
	
	@Inject
	public WikiService wikiService;
	
	@RequestMapping(value="/{value:.+}", method=RequestMethod.GET)
	public String wikiPage(@PathVariable String value, Model model, Authentication authentication) throws UnsupportedEncodingException {
		
		// 불순 문자셋을 사용했을경우
		value = value.trim();
		
		if(!XssUtil.xssEncode(value).equals(value)) {
			return "redirect:/auth/e";
		}else {
			value = XssUtil.xssEncode(value);
		}
		
		// 권한을 가지고있는 이용자일경우
		int permissionId = 1; 
		if(authentication != null) {
			UserDetailsVO userVO = (UserDetailsVO) authentication.getPrincipal();
			permissionId = PermissionUtil.check(userVO.getAuthorities().toArray()[0].toString());
		}
		
		// 위키 존재여부 확인
		WikiWrapperDTO dto = wikiService.selectWiki(value);
		if(dto != null) {
			dto.setTitle(XssUtil.xssEncode(dto.getTitle()));
			if(dto.getContent() != null) {
				dto.setContent(dto.getContent());
			}
		}
		model.addAttribute("value", XssUtil.xssEncode(value));
		model.addAttribute("wiki", dto);
		model.addAttribute("permission_id", permissionId);
		
		// 메인페이지 확인
		if("양디위키:대문".equals(value)) {
			model.addAttribute("isMainPage", true);
			
			List<WikiHeadDTO> updateList = new ArrayList<WikiHeadDTO>();
			for(WikiHeadDTO updateDTO : wikiService.selectUpdateWikiList()) {
				updateDTO.setTitle_entity(XssUtil.xssEncode(updateDTO.getTitle()));
				updateDTO.setTitle_url(UrlUtil.urlEncode(updateDTO.getTitle()));
				updateList.add(updateDTO);
			}
			model.addAttribute("updatePage", updateList);
			
			List<WikiHeadDTO> recommendList = new ArrayList<WikiHeadDTO>();
			for(WikiVO wTmp : wikiService.selectRandomWiki()) {
				WikiHeadDTO recommendDTO = new WikiHeadDTO();
				recommendDTO.setTitle_entity(XssUtil.xssEncode(wTmp.getTitle()));
				recommendDTO.setTitle_url(UrlUtil.urlEncode(wTmp.getTitle()));
				recommendList.add(recommendDTO);
			}
			model.addAttribute("recommendPage", recommendList);
		}
		
		logger.info("검색 문자열 : " + value);
//		logger.info("wiki : " + dto);
		
		return "/wiki/wiki";
	}
	
	@RequestMapping(value="/{value}/append", method=RequestMethod.POST)
	public String wikiAppend(HttpServletRequest request, @PathVariable String value, Model model) throws UnsupportedEncodingException {
		
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        
        try {
            if(!VerifyRecaptcha.verify(gRecaptchaResponse))
            	return "redirect:/wiki/%EC%96%91%EB%94%94%EA%B3%A0%EC%B9%9C%EA%B5%AC%EB%93%A4%E3%85%A0%20%ED%85%8C%EB%9F%AC%EC%A2%80%ED%95%98%EC%A7%80%EB%A7%88";
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/auth/e";
        }
		
		value = value.trim();
		
		// 위키이름길이
		if(value.length() >= 30) {
			logger.warn("위키이름길이 초과");
			return "redirect:/wiki/" + UrlUtil.urlEncode(value);
		}
		
		// 불순문자열 처리
		if(SqlInjectionUtil.isDenger(value)) {
			logger.warn("불순 문자열 처리");
			return "redirect:/auth/e"; 
		}
		
		WikiVO vo = new WikiVO();
		vo.setTitle(value);
		vo.setPermission_id(1);
		
		wikiService.insertWiki(vo);
		
		return "redirect:/wiki/" + UrlUtil.urlEncode(value);
	}
	
}
