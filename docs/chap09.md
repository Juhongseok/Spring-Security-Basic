# Chap09

기본적으로 시큐리티에서 제공되는 필터 외에도 원하는 방식대로 모델링 가능

## Filter 종류
--사진첨부--

`@EnableWebSecurity(debug = true)`어노테이션을 붙여주면 현재 실행되는 Security Fiter들을 확인할 수 있다.

> httpBasic()을 호출하면 BasicAuthenticationFilter 추가, 이와 비슷하게 추가 정의 가능<br>
> 순서를 기존 필터앞 뒤에 새로 정의한 필터 추가 가능

## Filter 생성방법
javax.servlet.Filter 상속받아 doFilter() 구현
- [RequestValidationFilter](/src/main/java/com/jhs/springsecuritybasic/chap09/RequestValidationFilter.java)
## Filter 등록방법
- http.addFilter(): 맨뒤에 filter 등록
- http.addFilterBefore(): 특정 filter 앞에 등록
- http.addFilterAfter(): 특정 filter 뒤에 등록
- http.addFilterAt(): 특정 filter 위치에 등록

## 기존필터 재정의
기존에 시큐리티에서 재공되는 필터를 재정의 해서 사용 가능하다

어떤 필터가 어떤 역할을 하는지 알고 있는 것이 중요한듯 보임