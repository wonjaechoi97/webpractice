package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if (item.getId() == null) { //id가 없다 == 완전 새롭게 생성하는 객체
            em.persist(item);
        }else{ //이미 등록된 것 ==> 업데이트라고 이해하자 우선
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll() { //여러건 찾는 건 jpql작성
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
