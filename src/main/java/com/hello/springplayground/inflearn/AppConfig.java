package com.hello.springplayground.inflearn;

import com.hello.springplayground.inflearn.discount.DiscountPolicy;
import com.hello.springplayground.inflearn.discount.FixDiscountPolicy;
import com.hello.springplayground.inflearn.member.MemberRepository;
import com.hello.springplayground.inflearn.member.MemberService;
import com.hello.springplayground.inflearn.member.MemberServiceImpl;
import com.hello.springplayground.inflearn.member.MemoryMemberRepository;
import com.hello.springplayground.inflearn.order.OrderService;
import com.hello.springplayground.inflearn.order.OrderServiceImpl;
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
