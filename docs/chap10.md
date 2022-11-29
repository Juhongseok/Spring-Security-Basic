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

## CORS
사이트가 로드된 도메인 이외의 도메인에 대한 요청을 거부

> ex.com 웹사이트는 ex.org 웹사이트의 요청을 거부

- CORS 이용해서 허용할 도메인, 세부정보 지정 가능
- 제한이 적용되도 엔드포인트 호출은 가능

### 부분 허용 방법
- 엔드포인트를 정의하는 메서드 위에 `@CrossOrigin` 사용
  - 장점
    - 사용이 간단함
  - 단점
    - 반복적인 코드 작성
- config 직접 작성
  - securityFilterChain 직접 등록: Spring Security 이용
    ```java
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      CorsConfigurationSource source = request -> {
          CorsConfiguration config = new CorsConfiguration();
          config.setAllowedOrigins(
                  List.of("http://localhost:8080")
          );
          config.setAllowedMethods(
                  List.of("GET", "POST", "PUT", "DELETE")
          );
          return config;
      };
      http.cors(c -> {
          c.configurationSource(source);
      });
    }
    ```
  - MVCConfigurer 등록: Spring Web MVC 이용
    ```java
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8080")
                        .allowedMethods("GET", "POST");
            }
        };
    }
    ```
    
  - CorsConfigurationSource 등록
    ```java
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:8080"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.setCorsConfigurations(setMapping(configuration));
        return source;
    }

    private HashMap<String, CorsConfiguration> setMapping(CorsConfiguration configuration) {
        HashMap<String, CorsConfiguration> corsConfigurations = new HashMap<>();
        corsConfigurations.put("/test", configuration);
        return corsConfigurations;
    }  
    ```

