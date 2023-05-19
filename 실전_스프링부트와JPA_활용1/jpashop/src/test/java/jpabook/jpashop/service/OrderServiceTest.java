package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest //스프링부트 띄운 상태로 테스트
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문()throws Exception {
        //given
        Member member = createMember(); //ctrl + alt + m => 반복라인 메서드로 만들기

        Item book = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        //주문상태
        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER);

        //주문 상품의 수량이 정확해야한다.
        assertThat(getOrder.getOrderItems().size()).isEqualTo(1);

        //주문 가격은 가격 * 수량이다
        assertThat(getOrder.getTotalPrice()).isEqualTo(10000 * orderCount);

        //주문 수량 만큼 재고가 줄어야 한다.
        assertThat(book.getStockQuantity()).isEqualTo(8);
    }


    @Test
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = createMember(); //ctrl + alt + m => 반복라인 메서드로 만들기

        Item item = createBook("시골 JPA", 10000, 10);

        int orderCount = 11;

        //when

        Assertions.assertThrows(NotEnoughStockException.class,
                () -> orderService.order(member.getId(), item.getId(), orderCount));

        //then
    }

    @Test
    public void 주문취소() throws Exception{
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);
        
        //then
        Order getOrder = orderRepository.findOne(orderId);

        //상탵는 cancel
        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        //수량도 복구
        assertThat(item.getStockQuantity()).isEqualTo(10);
    }


    private Item createBook(String name, int price, int stockQuantity) {
        Item book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }



}