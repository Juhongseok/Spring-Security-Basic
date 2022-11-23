package com.jhs.springsecuritybasic.chap10.repository;

import com.jhs.springsecuritybasic.chap10.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findTokenByIdentifier(String identifier);
}
