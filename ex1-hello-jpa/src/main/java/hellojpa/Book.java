package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book extends Item{
    private String author;
    private String title;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
