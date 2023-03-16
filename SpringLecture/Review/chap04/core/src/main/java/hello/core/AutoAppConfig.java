package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration //얘도 @Component가짐
@ComponentScan( //예제 코드를 살리기 위한 것 실무에서는 필요 x
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) //설정 충돌 방지하기 위해 기존 설정 파일(Test 디렉토리에 있는 것 포함) 제외
)
public class AutoAppConfig {
}
