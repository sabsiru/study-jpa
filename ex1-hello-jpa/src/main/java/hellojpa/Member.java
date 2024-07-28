package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Table (name = "MBR")  테이블의 이름 MBR을 매핑한다.
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1,allocationSize = 50
) //시퀀스 세팅
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
    ,generator = "MEMBER_SEQ_GENERATOR"  //SequenceGenerator 에서 설정한 name과 매핑
    )
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    //객체 연관 관계
    @ManyToOne //관계
    @JoinColumn(name="TEAM_ID") //join할 컬럼
    private Team team;


}
