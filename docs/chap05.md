# Chap05

## Authentication: Principal 확장
인증 프로세스의 필수 인터페이스로 인증 요청 이벤트를 나타냄

요청한 엔티티의 세부정보를 담음
> 요청하는 사용자: Principal
```java
public interface Authentication extends Principal, Serializable {
    Collection<? extends GrantedAuthority> getAuthorities(); // 권한 반환
    Object getCredentials(); // 암호 반환
    Object getDetails();
    Object getPrincipal();
    boolean isAuthenticated(); // 인증 종료 여부
    void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException;
}
```

##AuthenticationProvider 
- 인증 논리 담당, 요청을 허용할지 결정하는 조건 명령 발견
- AuthenticationManager 로부터 책임을 위임 받음
```java
public interface AuthenticationProvider {
    Authentication authenticate(Authentication authentication) throws AuthenticationException;
    //인증 실패시 예외 투척
    //인증 성공시 Authentication 객체 반환 - isAuthenticated: true
    //인증 실패시 null 반환 - 형식은 Authentication 이지만 세부정보가 일치 하지 않음
    boolean supports(Class<?> authentication);
    //Authentication 객체로 제공된 형식이면 true
}
```
- supports
  - 집 문을 열 수 있는 방법이 열쇠, 카드, 비밀번호, 지문 인지 확인
- authenticate
  - 집 문을 열 때 알맞은 정보로 열었는지 (ex) 지문이 인식이 되어있다, 비밀번호가 일치한다
### 엔티티 인증
SecurityContext 인스턴스 저장

### 엔티티 인증 거부
401 HTTP status 반환

## SecurityContext

Authentication 객체를 저장 관리하는 역할

```java
public interface SecurityContext extends Serializable{
  Authentication getAuthentication();
  void setAuthentication(Authentication authentication);
}
```

### 관리 전략
SecurityContextHolder 사용하여 관리자 역할

- MODE_THREADLOCAL: 각 스레드가 저장 가능, 일반적인 방법
- MODE_INHERITABLETHREADLOCAL: 비동기 메서드경우 스레드 복사하여 사용
- MODE_GLOBAL: 모든 스레드가 같은 컨텍스트 사용

## HttpBasic, FormLogin
### HttpBasic 실패 구성
- AuthenticationEntryPoint 구현
-[CustomEntryPoint](/src/main/java/com/jhs/springsecuritybasic/chap05/CustomEntryPoint.java)

### FormLogin
- defaultSuccessUrl 설정
- AuthenticationSuccessHandler 구현:[CustomAuthenticationSuccessHandler](/src/main/java/com/jhs/springsecuritybasic/chap05/CustomAuthenticationSuccessHandler.java)
- AuthenticationFailureHandler 구현:[CustomAuthenticationFailureHandler](/src/main/java/com/jhs/springsecuritybasic/chap05/CustomAuthenticationFailureHandler.java)