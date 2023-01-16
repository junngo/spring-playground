package com.hello.springplayground.inflearn.discount;

import com.hello.springplayground.inflearn.member.Member;

public interface DiscountPolicy {
    /**
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
