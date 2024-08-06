package hellojpa;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity
//@Table (name = "MBR")  테이블의 이름 MBR을 매핑한다.
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1, allocationSize = 50
) //시퀀스 세팅
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "MEMBER_SEQ_GENERATOR"  //SequenceGenerator 에서 설정한 name과 매핑
    )
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;


    @ManyToOne(fetch = FetchType.LAZY) //지연로딩, 가급적 실무에서는 지연로딩만을 쓸 것을 권장한다.
    @JoinColumn
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

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


    //단순 setter가 아닌 편의 메서드가 들어가면 메서드의 이름을 바꿔주는게 구분하기 좋다.
//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }
}
