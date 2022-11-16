# Chap06

## 도메인
- [USER](/src/main/java/com/jhs/springsecuritybasic/chap06/model/Users.java)
- [AUTHORITY](/src/main/java/com/jhs/springsecuritybasic/chap06/model/Authority.java)
- [PRODUCT](/src/main/java/com/jhs/springsecuritybasic/chap06/model/Product.java)

## Security 관련 도메인
- [CustomUserDetails](/src/main/java/com/jhs/springsecuritybasic/chap06/model/CustomUserDetails.java)

## Repository
JPARepository 상속받아 구현
- UserRepository
- ProductRepository

## Service
- [JpaUserDetailsService](/src/main/java/com/jhs/springsecuritybasic/chap06/service/JpaUserDetailsService.java): UserDetailsService 구현
- [ProductService](/src/main/java/com/jhs/springsecuritybasic/chap06/service/ProductService.java)
- [AuthenticationProviderService](/src/main/java/com/jhs/springsecuritybasic/chap06/service/AuthenticationProviderService.java): AuthenticationProvider 구현, 사용자 정보 검증 로직 구현

## Controller
- [MainPageController](/src/main/java/com/jhs/springsecuritybasic/chap06/controller/MainPageController.java)

## Config
- [ProjectConfig](/src/main/java/com/jhs/springsecuritybasic/chap06/config/ProjectConfigCh06.java): PasswordEncoder bean 등록 파일
- [WebConfig](/src/main/java/com/jhs/springsecuritybasic/chap06/config/WebConfig.java): web 관련 조합 설정 파일
  - authenticationProvider 등록
  - http 설정 (form login, request 관리)

## 과정
filter -> authenticationManager -> authenticationProvider(AuthenticationProviderService) -> UserDetailsService(JpaUserDetailsService), PasswordEncoder 사용하여 사용자 정보 조회 및 인증 과정 처리 -> filter 에 의해 SecurityContext 내부에 사용자 정보 저장 -> Controller 에서 Authentication 받아 사용