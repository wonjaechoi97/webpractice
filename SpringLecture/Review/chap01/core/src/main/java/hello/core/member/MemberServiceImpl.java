package hello.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository(); //구현부까지 의존해서 문제다.
    //다형성에 의해서 MemberRepository 타입 변수가 MemoryMemberRepository 객체를 참조할 수 있다.

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
