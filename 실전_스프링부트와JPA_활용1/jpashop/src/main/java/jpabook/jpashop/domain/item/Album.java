package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") //싱글테이블이기 때문에 구분할때 넣어야할 값
@Getter
@Setter
public class Album extends Item{
    private String artist;
    private String etc;
}
