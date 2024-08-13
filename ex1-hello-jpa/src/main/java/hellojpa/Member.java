package hellojpa;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private int age;

    //기간 Period
    //embedded 객체로 관리, 클래스는 따로지만 테이블은 하나
    @Embedded
    private Period workPeriod;

    //주소
    @Embedded
    private Address homeAddress;

    //값 타입 컬렉션 사용예제
    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOODS", joinColumns =
    @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    //이 방식으로는 데이터를 모두 삭제하고 남은 값을 재생성한다. 실무에서는 사용x
    //실무에서는 일대다 관계를 고려하는것이 낫다.
//    @ElementCollection
//    @CollectionTable(name = "ADDRESS", joinColumns =
//    @JoinColumn(name = "MEMBER_ID"))
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();

    //중복
    public void setId(Long id) {
        this.id = id;
    }

    @Embedded
    @AttributeOverrides({ //속성 재정의
            @AttributeOverride(name = "city",
                    column = @Column(name = "WORK_CITY")),
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

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }

    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }

   public int getAge() {
       return age;
   }

    public void setAge(int age) {
        this.age = age;
    }

    //단순 setter가 아닌 편의 메서드가 들어가면 메서드의 이름을 바꿔주는게 구분하기 좋다.
//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }
}
