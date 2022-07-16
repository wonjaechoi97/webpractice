package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 Di 컨테이너")
    void pureContainer(){
        
        //초당 50000개의 요청이 오면 계속 객체를 생성할 것인가? 메모리 낭비
        
        // --> 하나의 객체를 생성해서 공유하도록 하자 --> 싱글톤
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회:
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);

        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);




    }

    @Test
    @DisplayName("싱글톤 패턴을 사용한 객체 사용")
    void singletonService(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2); //두 객체의 참조 값이 같다 ㅎㅎ 싱글톤 !!!!
        //객체 생성 비용이 참조 비용보다 훨씬크다.

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
        //same ==> 객체 인스턴스를 비교
        //equal 문자열 비교하듯 내용 비교하느 건듯? 오버라이드 사용해서 정의 가능하므로

    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);

        //2. 조회:
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);

        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isSameAs(memberService2);


    }
}

