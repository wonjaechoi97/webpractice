package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component//("memberService2") : 이름 지정 가능
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository; //구현부까지 의존해서 문제다. ->이제 추상화에만 의존
    //다형성에 의해서 MemberRepository 타입 변수가 MemoryMemberRepository 객체를 참조할 수 있다.


    //생성자 주입
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도 : 인터페이스에는 안 만들기
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
