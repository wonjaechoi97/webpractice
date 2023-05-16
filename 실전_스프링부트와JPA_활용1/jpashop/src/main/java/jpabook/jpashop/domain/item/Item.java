package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //상속 전략 : 한테이블에 다 때려 박는
@DiscriminatorColumn(name = "dtype") //구분자
@Getter @Setter
public abstract class Item { //구현체 가질 것이기 때문에 추상 클래스
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    /*
    엔티티 내에서 처리할 수 있는 비즈니스 로직은 엔티티 내에서 처리하자
    그게 더 객체지향적
    외부에서 세터를 가지고 계산해서 넣지 말고
     */
    //==비즈니스 로직==//
    /*
    stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /*
    stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more Stock");
        }
        this.stockQuantity = restStock;
    }

}
