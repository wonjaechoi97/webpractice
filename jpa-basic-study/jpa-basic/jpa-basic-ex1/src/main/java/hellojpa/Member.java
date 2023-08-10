package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //jpa 가 로딩시 jpa 사용하는 것을 인식
//@Table(name = "USER") <-- 테이블 명으로 매핑
public class Member {
    @Id
    private  Long id;
    @Column(unique = true, length = 10) //unique 제약 조건, 길이 10자 제한 조건 추가
    private String name;
    private int age;

    public Member() {

    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
