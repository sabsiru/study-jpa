package hellojpa;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@Table (name = "MBR")  테이블의 이름 MBR을 매핑한다.
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1, allocationSize = 50
) //시퀀스 세팅
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "MEMBER_SEQ_GENERATOR"  //SequenceGenerator 에서 설정한 name과 매핑
    )
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    //기간 Period
    //embedded 객체로 관리, 클래스는 따로지만 테이블은 하나
    @Embedded
    private Period workPeriod;

    //주소
    @Embedded
    private Address homeAddress;

    //중복
    public void setId(Long id) {
        this.id = id;
    }

    @Embedded
    @AttributeOverrides({ //속성 재정의
            @AttributeOverride(name = "city",
                    column = @Column(name ="WORK_CITY")),
            @AttributeOverride(name = "street",
                    column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name = "zipcode",
                    column = @Column(name = "WORK_ZIPCODE"))
    })
    private Address workAddress;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    //단순 setter가 아닌 편의 메서드가 들어가면 메서드의 이름을 바꿔주는게 구분하기 좋다.
//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }
}
