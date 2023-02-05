package com.hello.springplayground.study.tr.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class UserTr {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;
}
