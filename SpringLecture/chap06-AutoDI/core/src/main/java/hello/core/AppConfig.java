package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration //Applicaion 구성 설정 정보 , @Configuration 뺀다면? CGLIB 안돼서 싱글톤 보장 못하고 계속 호출
public class AppConfig {
    //생성자 주입


    //@Bean memberService ->  new MemoryMemberRepository()
    //@Bean orderService -> new OrderServiceImpl(memberRepository(), discountPolicy()
    //이렇게 new를 여러번 하는데 싱글톤 가능한가?

    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository
    //
    //call AppConfig.memberRepository 3번 출력되어야 함
    //이것들이 몇번 호출 될까? 하지만 3개가 출력된다.

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){

        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    @Bean //스프링 컨테이너에 등록
    public DiscountPolicy discountPolicy(){
//        return  new FixDiscountPolicy(); //<===여기 하나만 바꾸면 할인 정책 변경 가능
        return new RateDiscountPolicy();
    }


}
