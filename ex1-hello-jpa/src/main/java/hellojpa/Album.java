package hellojpa;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;

@Entity
public class Album extends Item{
    private String artist;

}
