package helloo.springhello;

import helloo.springhello.repository.MemberRepository;
import helloo.springhello.repository.MemoryMemberRepository;
import helloo.springhello.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 상황에 따라 구현 클래스가 변할 때 주로 사용한다.
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService( memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return  new MemoryMemberRepository(); //=>DbMemberRepository()
    }
}
