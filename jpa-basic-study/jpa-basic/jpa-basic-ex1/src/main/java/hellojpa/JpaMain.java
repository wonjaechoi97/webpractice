package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        System.out.println("hello world");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

        EntityManager em = emf.createEntityManager();
        // 실제 db에 작성 불러오기 등의 코드 작성
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = new Member();

        member.setId(2L);
        member.setName("HelloB");
        em.persist(member);

        //jpa에서는 트랜잭션이 중요해서 모든 데이터 변경은 트랜잭션안에서 하도록
        tx.commit();

        em.close();

        emf.close();
    }
}
