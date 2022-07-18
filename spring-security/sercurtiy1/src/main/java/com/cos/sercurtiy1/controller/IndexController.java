package com.cos.sercurtiy1.controller;

import com.cos.sercurtiy1.model.User;
import com.cos.sercurtiy1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //view 를 리턴하겟다.
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"","/"})
    public String index(){
        //템플릿 엔진 mustache
        //기본 폴더 src/main/resources
        //뷰 리졸버: templates(prefix), mustache(suffix) --> yml에 설정 but 생략 가능
        return "index";//src/main/resources/templates/index.mustache
    }


    @GetMapping("/user")
    public @ResponseBody String user() {


        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "admin";
    }

    //@PostAuthorize("hasRole('ROLE_MANAGER')")
    //@PreAuthorize("hasRole('ROLE_MANAGER')")

    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "manager";
    }

    @GetMapping("/loginForm") //securitConfig만들기 전까지는 낚아 챔
    public  String loginForm() {

        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String  joinForm() {

        return "joinForm";
    }

    @PostMapping("/join")
    public  String  join(User user) {
        System.out.println("join");
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        //회원 가입 잘 됨 비밀번호 : 1234 => 시큐리티로 로그인을 할 수가 없음. 이유는 패스워드가 암호화가 안되어서
        return "redirect:/loginForm";
    }

    @GetMapping("/joinProc")
    public @ResponseBody String joinProc() {

        return "joinProc";
    }
}
