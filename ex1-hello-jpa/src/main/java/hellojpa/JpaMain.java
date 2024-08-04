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
            Movie movie = new Movie();
            movie.setActor("손석구");
            movie.setDirector("마동석");
            movie.setName("범죄도시2");
            movie.setPrice(10000);

            Book book = new Book();
            book.setTitle("해리포터");
            book.setName("마법사의 돌");
            book.setAuthor("다니엘 래드클리프");
            book.setAuthor("조앤 K 롤링");
            book.setPrice(17500);

            em.persist(movie);
            em.persist(book);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
