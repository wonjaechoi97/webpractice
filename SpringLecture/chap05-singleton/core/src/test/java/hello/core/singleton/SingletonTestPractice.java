package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTestPractice {

    @Test
    @DisplayName("스프링 없는 순수한 Di")
    void pureContainer(){

        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);
        //여러개 생성해서 낭비임
    }

    @Test
    @DisplayName("싱글톤 적용")
    void singleton(){
        SingletonServicePractice singletonServicePractice1 = SingletonServicePractice.getInstance();
        SingletonServicePractice singletonServicePractice2 = SingletonServicePractice.getInstance();

        singletonServicePractice1.logic();
        assertThat(singletonServicePractice1).isSameAs(singletonServicePractice2);
        //호출 비용이 생성비용보다는 적게 든다.
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean(MemberService.class);
        MemberService memberService2 = ac.getBean(MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
