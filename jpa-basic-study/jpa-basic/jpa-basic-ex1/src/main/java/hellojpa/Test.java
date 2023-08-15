package hellojpa;

import javax.persistence.*;

@Entity
//@SequenceGenerator(name = "test_seq_generator", sequenceName = "test_seq") //시퀀스 만들 수 있다.
@TableGenerator(name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ" , allocationSize = 1)
public class Test {

    @Id // 내가 직접 넣을거면 이것만 써도 됨
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_seq_generator")
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name", nullable = false)

    private String username;

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
}
