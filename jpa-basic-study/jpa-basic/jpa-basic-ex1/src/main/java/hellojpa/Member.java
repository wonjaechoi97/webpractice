package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity //jpa 가 로딩시 jpa 사용하는 것을 인식
//@Table(name = "USER") <-- 테이블 명으로 매핑
public class Member {
    @Id
    private Long id;
    @Column(name = "name") // db 컬럼명
    private String username;
    private Integer age;
    @Enumerated(EnumType.STRING) //db에는 enum이 없으므로 사용, 기본이 ordinal : enum의 순서를 데이터베이스에 저장
    /*
    roletype이 늘어나면 숫자 하나씩 밀리므로 사용할때는  STRING으로 사용하자
     */
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP) //타입 세가지 그 중 TIMESTAMP
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //요즘은 위에꺼보다 아래로 사용 자바 8 이후

    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;
    @Lob//varchar를 넘어서는 큰 컨텐츠
    private String description;


    public Member() {

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
