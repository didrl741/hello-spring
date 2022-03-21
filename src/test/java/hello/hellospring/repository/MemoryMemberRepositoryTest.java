package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 전체 test 돌리면, 각 테스트 메서드들은 순서에 맞지 않게 실행됨.
// -> 순서에 의존적이게 설계하지 말자. -> 각 Test메서드가 끝나면 repository를 비워주자!
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 콜백 메서드. 각 메서드들이 끝나면 자동으로 동작.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("yanggi");

        repository.save(member);

        // Optional에서 값 꺼낼 때 -> get 이용.
        Member result = repository.findById(member.getId()).get();

        // 검증. 다르다면 빨간불이 뜸.
        Assertions.assertEquals(member, result);

        // 두 번째 검증 방법. 이게 좀더 가시성. static import 하면 지저분한 부분 위로 올라감.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}
