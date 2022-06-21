package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;

import aspect.CacheAspect;
import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.RecCalculator;

@Configuration
//@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAspectJAutoProxy
public class AppCtxWithCache {
	// aspect2
	@Bean
	@Order(1) //순서 관리
	public CacheAspect cacheAspect() {
		return new CacheAspect();
	}

	// aspect1
	@Bean
	@Order(2)
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}

	// chap07 패키지 이하의 빈 객체이므로 공통 기능(measure) 적용 대상
	@Bean
	public Calculator calculator() {
		return new RecCalculator();
	}
}
