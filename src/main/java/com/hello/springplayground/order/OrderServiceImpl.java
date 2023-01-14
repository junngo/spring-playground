package com.hello.springplayground.order;

import com.hello.springplayground.discount.DiscountPolicy;
import com.hello.springplayground.discount.FixDiscountPolicy;
import com.hello.springplayground.member.Member;
import com.hello.springplayground.member.MemberRepository;
import com.hello.springplayground.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
