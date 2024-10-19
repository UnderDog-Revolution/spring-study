package hello.hello_spring.repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository(); // 일단 냅다 가져옴.

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring"); // setName만 해주면 회원가입이 사실상 되는것임.

        repository.save(member);

        // 옵셔널에서 값을 꺼낼 때는 get으로 꺼낼 수 있다.
        Member result = repository.findById(member.getId()).get();
        // System.out.println("result = " + (result == member)); // 테스트 방법1
        // Assertions.assertEquals(member, member); // 테스트 2

        // 얘는 다른 Assertions니까 주의!!!
        Assertions.assertThat(member).isEqualTo(member);
    }

    @Test
    public void findByName() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findByName("spring1").get();

        //then
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}