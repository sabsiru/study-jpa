package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book extends Item{
    private String author;
    private String title;
}
