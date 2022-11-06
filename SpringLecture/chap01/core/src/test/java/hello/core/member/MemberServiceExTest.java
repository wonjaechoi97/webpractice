package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceExTest {
    MemberService memberService = new MemberServiceImpl();

    @Test
    void 회원가입(){
        Member member = new Member(2L, "memberA", Grade.BASIC);

        memberService.join(member);
        Member findMember =  memberService.findMember(2L);

        assertThat(member).isEqualTo(findMember);

    }

}
