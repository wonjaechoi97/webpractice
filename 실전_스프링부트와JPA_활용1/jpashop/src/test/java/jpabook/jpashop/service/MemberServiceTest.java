package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest //스프링부트 띄운 상태로 테스트
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
//    @Rollback(value = false)
    public void 회원가입() throws Exception {

        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        em.flush();
        Assertions.assertThat(memberRepository.findOne(savedId)).isEqualTo(member);

    }

    @Test()
    public void 중복_회원_예외() throws Exception {

        //given
        Member member1 = new Member();
        member1.setName("choi");

        Member member2 = new Member();
        member2.setName("choi");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*
        try{
            memberService.join(member2);
        }catch (IllegalStateException e){
            return;
        }*/
        //then
//        fail("예외가 발생해야 한다.");
    }
}

//인텔리제이 라이브 템플릿 사용하면 편하다
