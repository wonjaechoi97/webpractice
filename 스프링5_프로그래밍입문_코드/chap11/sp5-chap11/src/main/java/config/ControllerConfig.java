package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.RegisterController;
import controller.SurveyController;
import spring.MemberRegisterService;

@Configuration
public class ControllerConfig {
	
	//registerController에 있음 
	@Autowired
	private MemberRegisterService memberRegSvc;
	
	
	@Bean
	public RegisterController registerController() {
		RegisterController controller = new RegisterController();
		controller.setMemberRegisterService(memberRegSvc);
		
		return controller;
	}
	
	@Bean
	public SurveyController surveyController() {
		return new SurveyController();
	}
}
