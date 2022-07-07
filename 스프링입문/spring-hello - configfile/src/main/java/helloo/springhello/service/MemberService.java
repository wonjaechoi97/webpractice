package helloo.springhello.service;

import helloo.springhello.domain.Member;
import helloo.springhello.repository.MemberRepository;
import helloo.springhello.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//서비스는 비즈니스 용어 사용 해서 이름 짓기

public class MemberService { // ctrl + shift + t ==> 테스트 생성
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) { //외부에서 넣어주도록 바꿔줌
        this.memberRepository = memberRepository; //이렇게 외부에서 넣어주는 것을 dependecy injection DI
    }

    /*
        회원가입
         */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원은 안됨
        /*Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m->{ //있다면?
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });*/
        //중복회원 검증
        validateDuplicateMember(member);


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m->{
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    public List<Member> findMembers(){
        /*전체 회원 조회*/
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
