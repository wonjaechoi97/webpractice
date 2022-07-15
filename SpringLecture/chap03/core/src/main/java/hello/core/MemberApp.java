package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //컨테이너 의미
        /*
        스프링이 이렇게 하면 AppConfig 설정 정보를 읽고 안의 Bean객체 생성해서 컨테이너에 넣어서 관리 시작
         */

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);//name은 메서드 명과 같다.

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}
