package hellojpa;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass //엔티티속성만 상속하는 어노테이션
//매핑정보만 제공하므로 조회, 검색(em.find)가 불가하다.
public abstract class BaseEntity {
    private String createBy;
    private LocalDateTime createDate;
    private String updateBy;
    private LocalDateTime updateDate;
}
