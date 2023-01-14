package com.hello.springplayground;

import com.hello.springplayground.discount.DiscountPolicy;
import com.hello.springplayground.discount.FixDiscountPolicy;
import com.hello.springplayground.member.MemberRepository;
import com.hello.springplayground.member.MemberService;
import com.hello.springplayground.member.MemberServiceImpl;
import com.hello.springplayground.member.MemoryMemberRepository;
import com.hello.springplayground.order.OrderService;
import com.hello.springplayground.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
