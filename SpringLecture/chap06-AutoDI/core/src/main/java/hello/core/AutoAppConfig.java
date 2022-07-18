package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core.member", // scan할 시작 위치 패키지 지정 가능, 모두 탐색하려면 시간이 오래 걸린다.
        //default 는 componentscan붙은 설정 정보 클래스의 패키지가 시작위치가 됨
        //보통 설정 정보 패키즈의 최상단의 두는 방법을 사용한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) //configuration 제외
)//자동으로 읽어서 bean 생성 @Component 붙은 클래스 자동으로 bean 등록
public class AutoAppConfig {
/*    @Bean(name="memoryMemberRepository")
    MemberRepository memberRepository(){
        System.out.println("수동 설정");
        return new MemoryMemberRepository();
    }*/
}
