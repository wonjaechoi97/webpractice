package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext //엔티티 매니저 스프링 컨테이너가 자동으로 주입
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId(); // 커맨드와 쿼리랑 분리하라 커맨드성 없애고 아이디 정도만
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
