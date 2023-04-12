package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    //하나의 회원이 여러 개 상품을 주문하기 때문에 일 대 다
    @OneToMany(mappedBy = "member" ) // <= 나는 연관관계의 거울이다 읽기 전용, order테이블의 member필드에 의해 매핑 됨
    private List<Order> orders = new ArrayList<>();



}
