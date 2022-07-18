# 공부한 것 정리
auth 패키지 생성 

public class PrincipalDetails implements UserDetails

UserDetails implements한 PrincipalDetails생성 

왜냐 ? 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인 진행
로그인 진행 완료가 되면 시큐리티 자신만의 session 만들어 줌(security ContextHolder에 세션 키 값 정보 저장)
 시큐리티 session에 들어갈 수 있는 객체는 Authentication 객체이어야 함
Authentication안에 유저 정보가 있음
user 정보를 답은 오브젝트 타입은 => UserDetails 타입 객체이어야 함

즉, Security Session 안에 Authentication 안에 UserDetails(PrincipalDetails) 안에서 User객체 생성해서 사용
