package helloo.springhello.repository;

import helloo.springhello.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id)); //null일 때 대비
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()     //루프로 돌리기
                .filter(member -> member.getName().equals(name))
                .findAny(); //하나 찾으면 반환 없으면 optional에 감싸져서 null 반환

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
