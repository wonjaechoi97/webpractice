package com.cos.jwt.controller;

import com.cos.jwt.model.User;
import com.cos.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("api/v1")
@RequiredArgsConstructor
public class RestApiController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder ;
    private final UserRepository userRepository;

    @GetMapping("home")
    public String home(){
        return "<h1>Home</h1>";
    }

    @PostMapping("token")
    public String token(){
        return "<h1>token</h1>";
    }

    @PostMapping("join")
    public String join(@RequestBody User user){
        System.out.println(user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()) );
        System.out.println(bCryptPasswordEncoder);
        user.setRoles("ROLE_USER");
        userRepository.save(user);
        return "회원가입 완료되었습니다.";
    }
}
