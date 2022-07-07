package helloo.springhello.service;

import helloo.springhello.domain.Member;
import static  org.assertj.core.api.Assertions.*;

import helloo.springhello.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//shift + f10 -> 이전 실행
class MemberServiceTest {

    MemberService memberService ;
    MemoryMemberRepository memberRepository ;



    @BeforeEach //동작하기 전에
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        /*
        *테스트 할 때마다 실행 전 각각 생성
        * */
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given  뭔가 주어졌을 때 - 데이터
        Member member = new Member();
        member.setName("spring");

        //when   이거를 수행했을 때 - 검증할 것
        Long saveId = memberService.join(member);

        //then   이런 결과가 나와야 돼 - 검증 부
        Member findMember =  memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// member2를join하면 예외가 터져야 함

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*try {
            memberService.join(member2);
            fail(); //여기 까지 오면 실패
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}