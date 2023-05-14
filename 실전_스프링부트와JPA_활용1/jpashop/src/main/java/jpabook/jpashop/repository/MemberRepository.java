package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //Component scan해서 자동으로 빈으로 관리
@RequiredArgsConstructor
public class MemberRepository {

 /*   @PersistenceContext //jpa가 제공하는 표준 annotation
    private EntityManager em; //스프링이 엔티티 매니저를 만들어서 주입*/

//    @Autowired @PersistenceContext
    private final EntityManager em; //data jpa가 위에 껄 자동으로 해줌 , autowired -> PersistenceContext

    /*@Autowired
    public MemberRepository(EntityManager em) {
        this.em = em;
    }*/

    public void save(Member member){
        em.persist(member); //영속성 엔티티에 member객체 넣고 트랜잭션 커밋되는 시점에 db에 insert쿼리 날라감
    }

    public Member findOne(Long id){
        return em.find(Member.class, id); //멤버 반환
    }

    public List<Member> findAll(){

        // ctrl + alt + n => 합치기

        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    /*
    jpql : sql과 다름, 테이블이 아닌 엔티티 객체에 대해서 쿼리한다는 점
     */

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}

