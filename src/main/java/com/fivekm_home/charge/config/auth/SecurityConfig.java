package com.fivekm_home.charge.config.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들은 disable
                .and()
                    .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점, authorizeRequests가 선언되어야지 antMatchers 옵션 사용 가능
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**","/**").permitAll()
//                    .antMatchers("/api/v1/**").hasRole(Role.NOMAL.name()) // antMatchers 권한 관리 대상을 지정하는 옵션, URL, HTTP 메소드 별로 관리 가능, "/"등 지정된 URL들은 permitAll() 전체 권한 열람
                    .anyRequest().authenticated() // 설정된 값 이외 나머지 URL 여기서 authenticated()를 추가하여 로그인 사용자를 말함. ㄴ "/api/v1/***" 주소를 가진 api는 user권한을 가진 사람만 가능
                .and()
                    .formLogin()
                    .loginPage("/user/login")
                    .defaultSuccessUrl("/")
                    .permitAll()    // 로그인 페이지
                .and()
                    .logout()
                        .logoutSuccessUrl("/") // 로그아웃 기능에 대한 설정 진입점, 로그아웃 성공시 /주소로 이동
                .and()
                    .oauth2Login()// OAuth2 로그인 기능에 대한 여러 설정 진입점
                        .userInfoEndpoint() // OAuth2 로그인 성공 후 사용자 정보를 가져울 때의 설정을 담당
                            .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스 구현체를 등록
                                                                 // 리소스 서버(소셜 서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
    }
}