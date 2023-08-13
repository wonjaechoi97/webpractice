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
            /*Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");*/
            //굳이 em.persist() 안해줘도 자동으로 업데이트 쿼리 나가고 커밋된다.
//            em.persist(member);

//            tx.commit(); //자원을 다 쓰면 반환
            //비영속
            /*Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            //영속 -> 엔티티 매니저 안 영속성 컨텍스 틍해서 관리가 된다.
            em.persist(member); //쿼리 날아가지 않음

            em.detach(member); //회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태

            em.remove(member); //객체 삭제한 상태(삭제 상태)

            tx.commit(); //트랜잭션을 커밋하는 시점에 쿼리 날아감*/


            //영속
           /* Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");
*/
            // 영속성 컨텍스트 1차 캐시에 저장 쿼리도 아직 날리지 않고 저장
        /*    em.persist(member1);
            em.persist(member2);*/
            System.out.println("====================="); // <== 이 이후 커밋할때 쿼리 날린다.
            //버퍼링 쿼리 한번에 모았다가 날림

            tx.commit();

        }catch (Exception e){
            tx.rollback();//예외 발생 시 롤백시켜주기
        }finally {
            em.close();
        }
        //jpa에서는 트랜잭션이 중요해서 모든 데이터 변경은 트랜잭션안에서 하도록

        emf.close();
    }
}
