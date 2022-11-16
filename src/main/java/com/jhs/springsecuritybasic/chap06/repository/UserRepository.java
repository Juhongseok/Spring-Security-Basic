package com.jhs.springsecuritybasic.chap06.repository;

import com.jhs.springsecuritybasic.chap06.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    @Query("select u from Users u join fetch u.authorities a")
    Optional<Users> findUserByUsername(String username);
}
