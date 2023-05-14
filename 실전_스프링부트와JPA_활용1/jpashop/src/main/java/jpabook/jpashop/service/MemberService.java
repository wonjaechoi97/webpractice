package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //jpa의 데이터 변경이나 로직들은 가급적 트랜잭션 안에서 실행되어여 함
//클래스 내에 읽기가 많다면 true
@RequiredArgsConstructor //final인 생성자만 가지고 생성자를 만들어줌
public class MemberService {

    /*
    테스트 시에 가짜 주입이 까다로움

     @Autowired
    private MemberRepository memberRepository;
     */

    private final MemberRepository memberRepository; //변경할 일이 없으므로 final

    // 생성자 주입
    /*
    직접 주입할 수 있고
    어떤 것을 의존하고 있는지 한눈에 확인 가능
     */
    /*@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/


    // @RequiredArgsConstructor로 생성자 대체


    //setter  injection : 애플리케이션 동작 중에 변경 가능 그러나 실제로 시작 시점에 주입이 끝나므로 동작 중
    //변경할 필요 없음
    /*
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /*
    회원 가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); //중복회원 검증 로직
        memberRepository.save(member);
        return member.getId(); //db에 들어가지 않고 영속성 컨텍스트에 들어가도 pk를 채워넣어줌
    }

    private void validateDuplicateMember(Member member) {
        //중복회원이면 예외 Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        /*
        WAS 멀티 스레드로 인해서 같은 이름의 회원이 동시에 등록하는 경우도 고려해야하므로
        db 에서 유니크 제약조건을 통해 최후의 안전장치 마련 필요
         */
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
//    @Transactional(readOnly = true) // jpa 조회 하는 경우 성능 최적화
    private List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //한 건만 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}

