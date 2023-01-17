package com.hello.springplayground.inflearn;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


/**
 * 컴포넌트 스캔
 * - ComponentScan / excludeFilters 사용 이유는 이전의 학습을 진행했던 Configuration을 제외시켜 주기 위해서
 * - 컴포넌트 스캔은 이름 그대로 @Component 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다
 *
 */
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =
                Configuration.class))
public class AutoAppConfig {
}
