package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;

@Entity
@DiscriminatorValue("M") //부모테이블 즉 item에 저장 될 구분 명을 지정하는 어노테이션
//지정하지 않으면 default값으로 저장됨 (Movie)
public class Movie extends Item{

    private String director;
    private String actor;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
