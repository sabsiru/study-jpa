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

            /*
            캐시에서 값을 가져 오기 때문에 select 쿼리가 나오지 않음
            만약 쿼리를 보고 싶다면 flush()를 통해 쿼리를 인서트하고 캐시를 초기화 해주면 쿼리가 나타남
            */
            //저장
            Team team = new Team();
            team.setTeamName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);
            //team.getMembers().add(member); //setTeam에 메소드 생성

            //em.flush(); // 쿼리 전송
            //em.clear(); // 캐시 초기화

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();
            System.out.println("====================");
            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }
            System.out.println("====================");


            //em.persist();
            tx.commit(); // DB에 저장
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
