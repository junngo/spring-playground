package com.hello.springplayground.discount;

import com.hello.springplayground.member.Grade;
import com.hello.springplayground.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10; //10% 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
