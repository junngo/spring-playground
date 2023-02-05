package com.hello.springplayground.study.tr.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTrRepository extends JpaRepository<UserTr, Long> {
}
