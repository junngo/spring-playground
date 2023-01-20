package com.hello.springplayground.inflearn.annotataion;


import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;


@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
    // @Qualifier("mainDiscountPolicy") 사용시 mainDiscountPolicy 문자가 틀릴 수 있는 경우를 대비하여
    // 별도의 어노테이션을 만들어서, 어노테이션 자체를 사용할 수 있음
}
