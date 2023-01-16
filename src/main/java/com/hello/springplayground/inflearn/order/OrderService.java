package com.hello.springplayground.inflearn.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);   // 주문 생성
}
