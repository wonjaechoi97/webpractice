package com.cos.sercurtiy1.config.auth;

//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인 진행
//로그인 진행 완료가 되면 시큐리티 자신만의 session 만들어 줌(security ContextHolder에 세션 키 값 정보 저장)
// 시큐리티 session에 들어갈 수 있는 객체는 Authentication 객체이어야 함
//Authentication안에 유저 정보가 있음
//user 오브젝트 타입은 => UserDetails 타입 객체이어야 함

import com.cos.sercurtiy1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//Security Session => Authentication => UserDetails(PrincipalDetails)
public class PrincipalDetails implements UserDetails {

    private User user; //컴포지션

    @Autowired
    public PrincipalDetails(User user) {
        this.user = user;
    }


    //해당 User의 권한을 return 하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override //만료 안됨?
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override //안 잠김?
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override //비밀번호를 너무 오래 사용했니?
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override //계정이 활성화 되어잇니? no
    public boolean isEnabled() {

        //1년 동안 회원이 로그인을 안하면 휴면 계정으로 하기로 함
        //현재 시간 - 로그인 시간 => 1년 초과하면 --> false
        return true;
    }
}
