package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc//1.mvc를 사용하는데 필요한 다양한 설정을 생성
public class MvcConfig implements WebMvcConfigurer{ //WebMvcConfigurer 은 webmvc내부 설정 조정할 때 사용 
	
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) { //2.dispatcherservlet의 매핑 경로를 '/'로 주었을 때, jsp/html/css 등을 올바르게 처리하기 위한 설정 추가
		// TODO Auto-generated method stub
		configurer.enable();
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) { //3.jsp이용해서 controller 실행 결과를 보여주기 위한 설정 
		registry.jsp("/WEB-INF/view/",".jsp");
	}
	
	//1,2,3 webmvc를 위한 
}
