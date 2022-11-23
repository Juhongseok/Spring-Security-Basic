package com.jhs.springsecuritybasic.chap10.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
public class Token {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String identifier;
    private String token;

    @Builder
    public Token(String identifier, String token) {
        this.identifier = identifier;
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
