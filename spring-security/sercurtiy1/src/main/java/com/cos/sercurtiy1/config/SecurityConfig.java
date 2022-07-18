package com.cos.sercurtiy1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터 체인에 등록 됨
public class SecurityConfig extends WebSecurityConfigurerAdapter { //<--이게 필터

    @Bean //빈 객체로 등록
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //csrf비활성화
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() ///user/**로 들어오면 인증이 필요
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll() //다른 요청은 다 허가
                .and()                       //권한 없을 때 로그인 창이동
                .formLogin()
                .loginPage("/loginForm") //로그인 페이지로 가라
                .loginProcessingUrl("/login") //login 주소가 호출되면 시큐리티가 낚아채서 대신 로그인 진행
                .defaultSuccessUrl("/"); //로그인 성공하면 /== 메인페이지로
    }



}
