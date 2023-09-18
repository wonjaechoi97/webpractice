package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class KeyTableMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Test member = new Test();
            member.setUsername("C");


            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();

        }finally {
            em.close();
        }

        emf.close();
    }
}
