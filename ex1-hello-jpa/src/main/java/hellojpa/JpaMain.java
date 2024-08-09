package hellojpa;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");// persistence.xml 에서 설정한 name

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("hello");
            member.setHomeAddress(new Address("city","street","10000"));
            member.setWorkPeriod(new Period());

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            System.out.println("rollback");
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
