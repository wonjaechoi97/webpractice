package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

@Controller
//@RequestMapping("/register")
public class RegisterController {
	
	private MemberRegisterService memberRegisterService;
	
	
	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}
	
	@RequestMapping("/register/step1") 
	public String handleStep1() {
		return "register/step1";
	}
	
//	@PostMapping("/register/step2")
//	public String handleStep2(HttpServletRequest request) {
//		String agreeParam = request.getParameter("agree");
//		if(agreeParam == null || !agreeParam.equals("true")) {
//			return "register/step1";
//		}
//		return "register/step2";
//	}
	
	@PostMapping("/register/step2")
	public String handlerStep2(@RequestParam(value="agree", defaultValue = "false") boolean agree, Model model) {
		if(!agree) {
			return "register/step1";
		}
		model.addAttribute("registerRequest", new RegisterRequest()); // <form:form> 사용 하기 위해서 커맨드 객체 매핑 위해서 
		return "register/step2";
	}
	
	@GetMapping("/register/step2")
	public String handleStep2Get() {
		return "redirect:/register/step1";
	}
	
	
	@PostMapping("register/step3") //model 대신 reqReq가 넘어감 getName 등등 사용해서 활용 
	public String handleStep3(RegisterRequest reqReq) {
		try {
			memberRegisterService.regist(reqReq);
			return "register/step3";
			} catch (DuplicateMemberException ex) {
			return "register/step2";
		}
		
	}


}
