# Chap04

## PasswordEncoder
암호 검증, 암호화 역할을 함

```java
public interface PasswordEncoder {
  String encode(CharSequence rawPassword); // 암호화
  boolean matches(CharSequence rawPassword, String encodedPassword); // 검증
  default boolean upgradeEncoding(String encodedPassword) { // 보안 향상을 위해 재 인코딩
      return false;
  }
}
```

### 제공되는 옵션
- NoOpPasswordEncoder: 암호화 하지 않음
- StandardPasswordEncoder: SHA-256 사용
- Pbkdf2PasswordEncoder: PBKDF2 사용
- BCryptPasswordEncoder: bcrypt 해싱 함수 사용
- SCryptPasswordEncoder: scrypt 해싱 함수 사용

## 여러 인코딩 설정 방법
`DelegatingPasswordEncoder 사용`

Map의 key 값을 접두사로 암호앞에 붙여서 사용

ex) `{bcrypt}12345`
```java
@Bean
public PasswordEncoder passwordEncoder(){
    Map<String, PasswordEncoder> encoders = new HashMap<>();

    encoders.put("noop", NoOpPasswordEncoder.getInstance());
    encoders.put("bcrypt", new BCryptPasswordEncoder());
    encoders.put("scrypt", new SCryptPasswordEncoder());

    return new DelegatingPasswordEncoder("bcrypt", encoders);
//    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
}
```