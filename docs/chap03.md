# Chap03

기본적으로 인터페이스를 상속받아 기능을 구현

## 사용자 관리
- [UserDetails](#UserDetails)
  - 스프링 시큐리티에서 사용자를 정의하는 모델 
- [UserDetailsService](#UserDetailsService)
  - 사용자 이름으로 검색하는 역할
- [UserDetailsManager](#UserDetailsManager)
  - UserDetailsService 상속받아 추가적인 작업 수행 가능 
  - 사용자 추가, 수정, 삭제 작업

## UserDetails
### 기능 목록  
- getUsername()
- getPassword()
- getAuthorities()
- isAccountNonExpired()
- isAccountNonLocked()
- isCredentialsNonExpired()
- isEnabled()

### 데이터베이스 사용시 UserDetails 정의 방법
- [DBUser](../src/main/java/com/jhs/springsecuritybasic/chap03/user/DbUser.java)

## GrantedAuthority
> 사용자에게 허가된 작업 (권한)

## UserDetailsService
```java
public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
```

### 인터페이스 구현
- [InMemoryUserDetailsService](../src/main/java/com/jhs/springsecuritybasic/chap03/InMemoryUserDetailsService.java)
  - 해당 코드는 메모리내에서 사용
- 만일 DB를 사용하면 UserRepository 사용하여 username 이용하여 user 검색 후 반환 하면 될 듯

## UserDetailsManager
