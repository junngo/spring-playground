package com.hello.springplayground;

import com.hello.springplayground.member.Grade;
import com.hello.springplayground.member.Member;
import com.hello.springplayground.member.MemberService;
import com.hello.springplayground.member.MemberServiceImpl;
import com.hello.springplayground.order.Order;
import com.hello.springplayground.order.OrderService;
import com.hello.springplayground.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        // 멤버 생성
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 주문 생성
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
    }
}
