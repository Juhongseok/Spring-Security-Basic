# Chap10

## CSRF
웹페이지 최초 요청시 CSRF 토큰 발행, 해당 토큰이 있는 요청에서만 작업 수행

기본적으로 CSRF 보호를 비활성화 시키지 않으면 GET 이외로는 직접 엔드포인트 호출 불가

### 비활성화 방법
`http.csrf().disable()`: 모든 엔드포인트에 대해서

`http.csrf(c -> c.ignoringAntMatchers("/ciao"));`: 특정 엔드포인트에 대해서

### 저장방법
기본적으로 서버 세션에 토큰 저장

많은 요청을 처리시 적합하지 않음

데이터베이스에 저장하도록 관리 변경시 아래 해당 구현을 재정의
```java
public interface CsrfToken extends Serializable {
    String getHeaderName();
    String getParameterName();
    String getToken();
}
```

시큐리티에서 `CsrfTokenRepository` 인터페이스 제공 --> 해당 기능 구현

![csrf토큰 저장](https://user-images.githubusercontent.com/75611167/203509727-9f10187e-0c82-40b3-813a-41c17ad43e96.jpg)
```java
@RequiredArgsConstructor
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    private final JpaTokenRepository jpaTokenRepository;
    
    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        return null;
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        return null;
    }
}
```

- CsrfFilter 일부 코드 발췌
```java
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException{
    request.setAttribute(HttpServletResponse.class.getName(),response);
    CsrfToken csrfToken=this.tokenRepository.loadToken(request);
    boolean missingToken=(csrfToken==null);
    // 직접 재정의한 CustomCsrfTokenRepository 기능 사용
    if(missingToken){
        csrfToken=this.tokenRepository.generateToken(request); // 토큰 생성 후
        this.tokenRepository.saveToken(csrfToken,request,response); // 토큰 저장
    }
}
```
![csrf토큰 저장2](https://user-images.githubusercontent.com/75611167/203509749-e243f89c-e04b-4269-9ac9-db84e3c15fda.jpg)

