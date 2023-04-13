package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"), //중간 테이블에 있는 category_id
            inverseJoinColumns = @JoinColumn(name = "item_id") //아이템 쪽으로 들어가는 것
    ) //중간 테이블 매핑해주어야 함, 실전에서 쓰지 않기, 딱 외래 키 넣는 구조로만 가능하기 때문
    //실무에서는 등록 날짜라도 넣어주는 편
    private List<Item> items = new ArrayList<>();


    //카테고리의 계층 구조를 표현하기 위해서 사용
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
