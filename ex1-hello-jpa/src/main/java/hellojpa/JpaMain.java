package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");// persistence.xml 에서 설정한 name

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
           //저장
            Team team = new Team();
            team.setTeamName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);
            /*
            캐시에서 값을 가져 오기 때문에 select 쿼리가 나오지 않음
            만약 쿼리를 보고 싶다면 flush()를 통해 쿼리를 인서트하고 캐시를 초기화 해주면 쿼리가 나타남
            */
            em.flush(); // 쿼리 전송
            em.clear(); // 캐시 초기화

            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam(); // select 쿼리가 나오지 않는 이유는 캐시에서 값을 가져오기때문
            System.out.println("findTeam.getTeamName() = " + findTeam.getTeamName());

            Member member2 = new Member();
            member2.setUsername("member2");
            em.persist(member2);

            Team team1 = new Team();
            team1.setTeamName("TeamB");


            em.persist(team1);
            tx.commit(); // DB에 저장
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
