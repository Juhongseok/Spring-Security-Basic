package com.jhs.springsecuritybasic.chap06.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Product {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private Double price;

    @Enumerated(EnumType.STRING)
    private Currency currency;
}
