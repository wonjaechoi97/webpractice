package helloo.springhello.controller;

import helloo.springhello.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 스프링 컨테이너가 객체 생성해서 컨테이너에 넣어두어 관리
public class MemberController {

    private final MemberService memberService;

    //alt+insert
    @Autowired
    public MemberController(MemberService memberService) { //컨테이너의 memberService가져와서 넣어줌 di
        this.memberService = memberService;
    }
}
