package xyz.lunab.yywiki.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xyz.lunab.yywiki.domain.UserVO;
import xyz.lunab.yywiki.dto.RegisterDTO;
import xyz.lunab.yywiki.service.UserService;
import xyz.lunab.yywiki.util.Sha256;

@Controller
@RequestMapping("auth")
public class AuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Inject
	public UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(@RequestParam(value = "loginFailed", required = false) String loginFailed, Model model) {
		
		if(loginFailed != null) {
			model.addAttribute("loginFailed", true);
		}
		return "/auth/login";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(@RequestParam(value = "registerFailed", required = false) String registerFailed, Model model) {
		
		if(registerFailed != null) {
			model.addAttribute("registerFailed", true);
		}
		
		return "/auth/register";
	}
	
	@RequestMapping(value="/registerProcess", method=RequestMethod.POST)
    public String registerProcess(RegisterDTO dto) {
        logger.info("/auth/registerProcess");
        logger.info(dto.toString());
        if( !(dto.getUser_id().isEmpty() || dto.getUser_name().isEmpty() || dto.getUser_pw().isEmpty())) {
        	if(dto.getUser_pw().equals(dto.getUser_pw_conf())){
	            UserVO vo = new UserVO();
	            vo.setId(dto.getUser_id());
	            vo.setName(dto.getUser_name());
	            vo.setPass(Sha256.encrypt(dto.getUser_pw()));
	            vo.setRole("ROLE_USER");
	
	            userService.insertUser(vo);
	            
	            return "redirect:/auth/login?registerSucceed";
        	}
        }
        return "redirect:/auth/register?registerFailed";
    }
	
	@RequestMapping(value="/e", method=RequestMethod.GET)
    public String error() {
		return "/test/test";
	}
	
}
