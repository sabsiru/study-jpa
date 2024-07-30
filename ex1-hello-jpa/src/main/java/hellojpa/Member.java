package hellojpa;

import javax.persistence.*;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    //단순 setter가 아닌 편의 메서드가 들어가면 메서드의 이름을 바꿔주는게 구분하기 좋다.
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
