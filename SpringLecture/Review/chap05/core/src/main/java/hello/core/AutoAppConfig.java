package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration //얘도 @Component가짐
@ComponentScan(
        //basePackages = "hello.core.member", //컴포넌트 스캔할 패키지 지정 가능하다. 여기서는 hello.core.member 하위 패키지 안의 component만 컨테이너에 등록가능
        // 모든 패키지 다 찾으면 너무 오래 걸림, 원하는 패키지만 탐색하고 싶을 때
        // 지정하지 않으면 @ComponentScan 달린 클래스의 패키지부터 시작해서 다 뒤짐
        // 여기는 hello.core 부터 다 뒤짐
        // root(hello.core)에 CoreApplication에 @SpringBootApplication이 ComponentScan 가지고 있음
        //예제 코드를 살리기 위한 것 실무에서는 필요 x
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) //설정 충돌 방지하기 위해 기존 설정 파일(Test 디렉토리에 있는 것 포함) 제외
)
public class AutoAppConfig {
}
