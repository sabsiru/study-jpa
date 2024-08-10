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
            Address address = new Address("city", "street", "10000");

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(address);
            em.persist(member);

            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

            Member member2 = new Member();
            member2.setUsername("member2");
            //값을 공유하면 같이 변경됨. 그러므로 객체를 복사해서 넣어야함
            //Address address2 = new Address("city", "street", "10000");
            member2.setHomeAddress(copyAddress);
            em.persist(member2);

            //불변객체로 설계해야함 setter를 private로 하거나 만들지않아서 원천 차단
            //member.getHomeAddress().setCity("newCity");
            //값을 바꾸고 싶다면 통으로 갈아 끼워야한다.
            Address newAddress = new Address("NewCIty", address.getStreet(), address.getZipcode());
            member2.setHomeAddress(newAddress);

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
