package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원정보 저장
    /* 옵셔널은 널이 반환될 경우를 생각하는 방식 */
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}