package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
외래 키가 두군데에서 존재하는데 도대체 누굴 바꿔야 할까 -> 연관관계의 주인 개념 등장
테이블에서 외래키를 가진쪽을 연관관계의 주인으로 설정
 */
@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id") //외래키 이름 member_id
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne //주로 Order 를 통해서 Delivery를 보기 때문에 order에 외래키를 둔다 **연관관계 주인**
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //주문 시간 : 시간 분까지

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 : {ORDER, CANCEL}


}
