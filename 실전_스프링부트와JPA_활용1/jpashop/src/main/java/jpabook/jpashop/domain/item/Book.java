package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") //싱글테이블이기 때문에 구분할때 넣어야할 값
@Getter
@Setter
public class Book extends Item{

    private String author;
    private String isbn;
}
