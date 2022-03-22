package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// Repository 어노태이션: 스프링 빈에 등록시켜준다 -> MemberService의 생성자에서 가져다 쓸 수 있다.
@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;      // 0, 1, 2.. 키 값을 생성하는 역할.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //return store.get(id);     이렇게하면 반환될 값이 없으면 null return -> 곤란해짐.
        return Optional.ofNullable(store.get(id));  // null 이여도 감쌀수 있다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))    // map에서 루프돌면서 name인지 확인.
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
