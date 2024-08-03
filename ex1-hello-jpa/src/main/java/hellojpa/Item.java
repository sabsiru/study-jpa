package hellojpa;

import javax.persistence.*;

@Entity
//조인 전략 디폴트 = 싱글 테이블
//InheritanceType.JOINED = 조인
@Inheritance(strategy = InheritanceType.JOINED)
public class Item {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
