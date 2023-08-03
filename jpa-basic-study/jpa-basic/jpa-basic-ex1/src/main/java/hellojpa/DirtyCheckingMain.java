package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DirtyCheckingMain {
    public static void main(String[] args) {
        System.out.println("hello world");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

        EntityManager em = emf.createEntityManager(); //마치 자바 컬렉션 처럼 사용
        // 실제 db에 작성 불러오기 등의 코드 작성
        EntityTransaction tx = em.getTransaction();
        tx.begin();
//아래 try catch는 정석 코드이나 스프링이 다 해준다.
        try {
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ");
            //em.persist하지 않아도 스냅샷과의 비교를 통해 변경을 감지해서 알아서 커밋에서 쿼리를 날려준다
            //자바 컬렉션을 생각해보자, 변경했다고 해서 컬렉션에 다시 넣어주는 과정이 필요한가?


            tx.commit();
            //커밋 -> flush -> 스냅샷과 현재 엔티티 비교 -> 바뀌었다면 쿼리 쓰기 지연 sql 저장소에 저장 후
            //데이터 베이스에 모아둔 쿼리 다 날림


        }catch (Exception e){
            tx.rollback();//예외 발생 시 롤백시켜주기
        }finally {
            em.close();
        }
        //jpa에서는 트랜잭션이 중요해서 모든 데이터 변경은 트랜잭션안에서 하도록

        emf.close();
    }
}
