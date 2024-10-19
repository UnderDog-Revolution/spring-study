package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

// 구현체이므로, 앞서 만들어뒀던 MemberRepository 인터페이스를 implements를 해주어야 한다.
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // 예제이므로 간단히 해시맵저장소 이용
    private static long sequence = 0L; // Map에 들어갈 키값 생성.

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 여기에서 시스템상으로 직접 member에 키값 자동설정
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 쪼금 어려움!! -> java8 람다식 이용
        // stream()이 계속 돌리는 것.
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // 계속 돌리다가 필터링을 하는데, 멤버들 중 멤버이름이 매개변수 name과 같은 아무거나를 찾아라.
    }

    @Override
    // java에서 실무를 할 땐 Map보단 List를 많이 쓴다.
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 리스트로의 변환
    }

    public void clearStore() { // 추가
        store.clear(); // 저장소 싹 비워줌
    }
}