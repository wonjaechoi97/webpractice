package helloo.springhello.repository;

import helloo.springhello.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member>  findById(Long id); //Optional null일 때 optional로 감싸서 반환 자주 사용
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
