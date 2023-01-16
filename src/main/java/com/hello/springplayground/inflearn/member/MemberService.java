package com.hello.springplayground.inflearn.member;

public interface MemberService {
    void join(Member member);   // 회원가입 기능
    Member findMember(Long memberId);   // 회원 조회 기능
}
