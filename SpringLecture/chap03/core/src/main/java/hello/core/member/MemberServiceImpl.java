package hello.core.member;

public class MemberServiceImpl implements MemberService { //구현체가 하나만 있을 경우 관례상 impl 붙여서 사용

    //추상화와 구체화 모두 의존한다 dip 위반
    private final MemberRepository memberRepository;

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
}
