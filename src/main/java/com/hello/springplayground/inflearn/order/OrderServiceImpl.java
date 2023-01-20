package com.hello.springplayground.inflearn.order;

import com.hello.springplayground.inflearn.annotataion.MainDiscountPolicy;
import com.hello.springplayground.inflearn.discount.DiscountPolicy;
import com.hello.springplayground.inflearn.member.Member;
import com.hello.springplayground.inflearn.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**
     * DB 분기가 필요한 경우, 주로 쓰는 건 @Primary 처리를 하고
     * 자주 사용하지 않는데 필요한 경우는 @Qualifier를 사용
     *
     * DiscountPolicy 빈 주입
     * 1. @Qualifier 학습
     * 2. @Primary 학습
     * 3. 커스텀 어노테이션 @MainDiscountPolicy 학습
     *
     * @param memberRepository
     * @param discountPolicy
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,
//                            @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy
//                            DiscountPolicy discountPolicy
                            @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 싱글톤 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
