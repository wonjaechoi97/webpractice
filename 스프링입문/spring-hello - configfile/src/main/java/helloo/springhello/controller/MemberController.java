package helloo.springhello.controller;

import helloo.springhello.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 스프링 컨테이너가 객체 생성해서 컨테이너에 넣어두어 관리 예는 스캔해야 함
public class MemberController {

    //개발자가 직접 생성하는 객체는 의존 주입이 되지 않는다다

   //@Autowired 필드 주입
    private  MemberService memberService;

//    @Autowired //setter 방식 public하므로 다른 곳(패키지, 클래스)에서 접근 가능
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    //alt+insert
    @Autowired //생성자 주입 가장 권장 !!!!!!!!!!!!!!, 런타임 중에 바뀔일은 거의 없다. 바뀌더라도 수정 후 다시 실행시켜서
    public MemberController(MemberService memberService) { //컨테이너의 memberService가져와서 넣어줌 di
        this.memberService = memberService;
    }



}
