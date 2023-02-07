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

@Configuration //@Configuration 없으면 CGlib 즉 바이트 코드 조작을 통한 AppConfig@CGLib 생성 안한고 순수한 객체 생성
public class AppConfig { //Application 전체를 설정하고 구성한다는 의미
    //사용 영역의 코드의 변경 없이
    // 구서 영역의 코드만 변경하여 확장가능하다.
    //역할이 드러나도록 수정
    /*
    메서드 명을 통해 역할이 드러남
     */
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository(); //<--db로 바꾸더라도 이부분만 바꾸면 되므로 간편함
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy(); //<---여기만 고치면 할인 정책 변경가능
    }


}
