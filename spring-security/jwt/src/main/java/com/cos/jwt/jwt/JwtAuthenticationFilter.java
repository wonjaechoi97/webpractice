package com.cos.jwt.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.jwt.auth.PrincipalDetails;
import com.cos.jwt.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

//스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 가 있음
// /login 요청해서 아이디 패스워드 전송하면(post)
//UsernamePasswordAuthenticationFilter 동작작
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
            //Authentication 매니저 통해서 로그인 진행
    private final AuthenticationManager authenticationManager; // <--얘로 로그인 시도

    //login요청이 오면 UsernamePasswordAuthenticationFilterdl 낚아채서 attemptAuthentication함수 실행
    //login 요청을 하면 로그인 시도를 위해 실행되는 함수
    @Override  //<----------------------------------------------------------시도
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter 로그인 시도중" );


        //1. username와 패스워드를 받아서
        try {
//            BufferedReader br = request.getReader();
////            System.out.println(request.getInputStream()); //username 패스워드 담김
//
//            String input = null;
//            while ((input=br.readLine())!=null){
//                System.out.println(input);
//            }
            ObjectMapper om = new ObjectMapper(); //json 데이터 파싱
            User user = om.readValue(request.getInputStream(), User.class); //User 객체에 담김
            System.out.println("user = " + user);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken); //인증 되면 authentication받음
            //이때 PrincipalDetailsService의 loadUserByUsername() 함수 실행
            //토큰을 통해 로그인 시도를 하고 로그인 시도가 잘 되면 authentication 리턴 됨.
            //db의 username과 password가 일치
            // authentication에 내 로그인 정보가 담김

            //=> 로그인이 됨
            PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
            System.out.println("로그인이 완료됨 : "+principalDetails.getUser().getUsername());//잘 출력됨 => 로그인이 됨
            return authentication; //auauthentication객체가  session 영역에 저장됨.
            //return의 이유는 security가 대신 해주기 때문에 편하기 때문에 해줌 단순히 권한 처리 때문에
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=================================================");
        //2. 정상인지 로그인 시도를 해보자 authenticationManager로그인 시도를 하면
        // PrincipalDetailsService 호출 loadUserByUsername 자동 실행

        //3.PrincipalDetails를 세션에 담고(권한 관리를 위해)

        //4. jwt토큰을 만들어서 응답해주면 된다.
        return null;// 오류 발생시 null 리턴

    }


    /*
    attemptAuthentication 실행 -> 인증 정상 완료 되면 아래 함수 에서 jwt토큰 만들어서 request 요청한 사용자에게 전달


     */
    //위의 로그인 인증 완료 후 실행되는 메서드
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal();

        //RSA X Hash 암호 방식
        String jwtToken = JWT.create()
                        .withSubject("cos")
                                .withExpiresAt(new Date(System.currentTimeMillis()+60000*10)) //10분
                        .withClaim("id",principalDetails.getUser().getId())
                                .withClaim("username", principalDetails.getUser().getUsername()) //withclaim 넣고 싶은 key value 막 넣어도 됨
                                        .sign(Algorithm.HMAC512("cos"));//사인 cos=서버만 사용하는 비밀키

        response.addHeader("Authorization","Bearer "+jwtToken);

        System.out.println("successfulAuthentication 실행 : 인증 완료되엇다는 뜻");

        //토큰을 가져오면 인증해서 요청한 페이지로 보내줌 jwt 토큰 유효한지 판단하는 필터 만들기 필요.
    }
}
