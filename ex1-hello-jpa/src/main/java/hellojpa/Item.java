package hellojpa;

import javax.persistence.*;

@Entity
//조인 전략 디폴트 = 싱글 테이블
//InheritanceType.JOINED = 조인
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //상속관계 테이블을 생성하지 외래키로 조인된
//각각의 테이블을 생성하는 전략 (이전 커밋 에서도 Item 클래스는 추상화클래스였어야 함)
public abstract class Item {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
