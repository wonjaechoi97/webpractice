package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc//mvc를 사용하는데 필요한 다양한 설정을 생성
public class MvcConfig implements WebMvcConfigurer{
	
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) { //매핑 경로를 '/'로 주었을 때, jsp/html/css 등을 올바르게 처리하기 위한 설정 추가
		// TODO Auto-generated method stub
		configurer.enable();
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/",".jsp");
	}
}
