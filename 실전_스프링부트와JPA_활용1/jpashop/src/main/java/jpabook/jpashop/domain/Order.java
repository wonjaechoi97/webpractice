package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") //외래키 이름 member_id
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //
    private List<OrderItem> orderItems = new ArrayList<>();

    /*
    persist(orderItemA)
    persist(orderItemB)
    persist(orderItemC)
    persist(order)
    ------------- cascade = CascadeType.ALL
    persist(order) 간편해짐
     */

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //주로 Order 를 통해서 Delivery를 보기 때문에 order에 외래키를 둔다 **연관관계 주인**
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // Order persist 하면 delivery도 persist 가능

    private LocalDateTime orderDate; //주문 시간 : 시간 분까지

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 : {ORDER, CANCEL}

    //연관관계 편의 메서드(양방향 연관관계이면 편함)
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비즈니스 로직==//
    /*
    주문 취소
     */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel(); //여러 개 주문 하나씩 각각 order cancel 날려줘야함
        }
    }

    //==조회 로직==//
     /*
     전체 상품 전체 가격 조회
      */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
/*
        return orderItems.stream().
                mapToInt(OrderItem::getTotalPrice).
                sum();
                */
    }



}
