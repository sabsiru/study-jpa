package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PracticeMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");// persistence.xml 에서 설정한 name

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = em.find(Member.class, 2L);
            Team team = em.find(Team.class, 2L);

            String memberTeamName = member.getTeam().getTeamName();
            Long memberTeamId = member.getTeam().getId();

            System.out.println("memberTeamName = " + memberTeamName);
            System.out.println("memberTeamId = " + memberTeamId);

            member.setTeam(team);
            em.persist(member);

            tx.commit(); // DB에 저장
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
