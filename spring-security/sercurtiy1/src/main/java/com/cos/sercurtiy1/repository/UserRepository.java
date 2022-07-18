package com.cos.sercurtiy1.repository;

import com.cos.sercurtiy1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD 함수를 JpaRepository가 들고 있음
//@Repository 어노테이션 없어도 ioc가능 JpaRepository를 상속했기 때문
public interface UserRepository extends JpaRepository<User, Integer> {//<테이블/ primary key>

}
