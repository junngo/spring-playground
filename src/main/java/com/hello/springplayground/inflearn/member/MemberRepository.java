package com.hello.springplayground.inflearn.member;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
}
