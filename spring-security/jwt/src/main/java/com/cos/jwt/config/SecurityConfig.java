package com.cos.jwt.config;

import com.cos.jwt.filter.MyFilter1;
import com.cos.jwt.filter.MyFilter3;
import com.cos.jwt.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity //security 활성화
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean //빈 객체로 등록
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    private final CorsFilter corsFilter; //인증이 필요한 요청은 이렇게 필터 설정

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class); //BasicAuthenticationFilter 필터 시작 이전에 걸어야함
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//세션 쓰지 않겟다
        .and()
                .addFilter(corsFilter) //모든 요청은 이 필터를 통과한다. @CrossOrigin (인증x) 필터에 등록(인증 ㅇ)
                .formLogin().disable()//jwt로그인 하므로 폼 로그인 하지 않음
                .httpBasic().disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager())) //필터 달아줌 Authentication 매니저 필요
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")//이쪽으로 들어오면
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")//이쪽으로 들어오면
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")//이쪽으로 들어오면
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/loginProc"); form 로그인 안하기로 해서 동작 x


    }
}
