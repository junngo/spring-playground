package com.hello.springplayground;

import com.hello.springplayground.discount.DiscountPolicy;
import com.hello.springplayground.discount.FixDiscountPolicy;
import com.hello.springplayground.member.MemberRepository;
import com.hello.springplayground.member.MemberService;
import com.hello.springplayground.member.MemberServiceImpl;
import com.hello.springplayground.member.MemoryMemberRepository;
import com.hello.springplayground.order.OrderService;
import com.hello.springplayground.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
