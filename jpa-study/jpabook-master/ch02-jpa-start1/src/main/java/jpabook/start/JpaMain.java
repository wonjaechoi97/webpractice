package jpabook.start;


import javax.persistence.*;
import java.util.List;

/**
 * @author holyeye
 */

public class JpaMain {

    public static void main(String[] args) {

        //[엔티티 매니저 팩토리] - 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

/*
        설정 정보 읽어서 JPA 동작 위한 기반 객체 생성, JPA 구현체에 따라 "커넥션 풀도 생성하므로 생성하는 비용이 아주 크다"

        따라서 엔티티 매니저 팩토리는 애프리케이션 전체에 딱 한 번만 생성하고 공유해서 사용해야 함.
         */


        //[엔티티 매니저] - 생성
        EntityManager em = emf.createEntityManager();

/*
        JPA의 대부분의 기능을 얘가 제공
        1. em으로 엔티티 데이터베이스에 CRUD할 수 있다.
        2. em은 내부에서 데이터소스(데이터베이스 커넥션)을 유지하면서 데 베 와 통신
        3. em을 가상의 데 베로 생각할 수도 있다
        4. em은 커넥션과 밀접한 관계가 있으므로 스레드간 공유 혹은 재사용 x
         */


        //[트랜잭션] - 획득
        EntityTransaction tx = em.getTransaction();

        try {

/*
            JPA는 트랜잭션 안에서 데이터 변경해야 함 아니면 에러 발생
             */

            tx.begin();
            logic(em);
            tx.commit(); //예외 x commit
        } catch (Exception e) { //예외 발생 rollback
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

        //매니저, 매니저 팩토리 순차적 종료
    }

    //비즈니스 로직
    private static void logic(EntityManager em) {
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("원재");
        member.setAge(26);

        //등록
        em.persist(member);

        //수정
        member.setAge(28);

        //한 건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember = " + findMember.getUsername()+ ", age="+findMember.getAge());

        //목록조회
        List<Member> members =
                em.createQuery("select m from Member m", Member.class)
                        .getResultList();
        System.out.println("members.size = " + members.size());

        //삭제
        em.remove(member);



    }

}

