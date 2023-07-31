package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        System.out.println("hello world");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

        EntityManager em = emf.createEntityManager(); //마치 자바 컬렉션 처럼 사용
        // 실제 db에 작성 불러오기 등의 코드 작성
        EntityTransaction tx = em.getTransaction();
        tx.begin();
//아래 try catch는 정석 코드이나 스프링이 다 해준다.
        try {
            //멤버 생성 후 삽입
            /*Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");*/
            System.out.println("여기 오냐?");
            //멤버 조회
            /*Member findMember = em.find(Member.class, 1L);
            System.out.println("여기 오냐?2");

            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());*/

            //삭제
            /*Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);*/

            //수정
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");
            //굳이 em.persist() 안해줘도 자동으로 업데이트 쿼리 나가고 커밋된다.
//            em.persist(member);

            tx.commit(); //자원을 다 쓰면 반환
        }catch (Exception e){
            tx.rollback();//예외 발생 시 롤백시켜주기
        }finally {
            em.close();
        }
        //jpa에서는 트랜잭션이 중요해서 모든 데이터 변경은 트랜잭션안에서 하도록

        emf.close();
    }
}
