package com.beyond.board.common;

import com.beyond.board.author.service.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;



//  @Bean 사용
@Configuration

//  MVC 패턴에서 세션 방식의 로그인 방법
//  Spring Security 설정을 customizing 하기 위함.
@EnableWebSecurity

//  인증검사를 사전에 하겠다. 시점은 정확히 모른다. 대충 사전에 하자.
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig {

    @Bean
    //  접속하면 가장 먼저 filter 를 거치게 된다.
    //  FilterChain : filter 를 여러개 걸 수 있음
    public SecurityFilterChain myFilter(HttpSecurity httpSecurity) throws Exception {
        //  builder pattern
        return httpSecurity
                .csrf().disable()   //  csrf 공격에 대한 설정은 하지 않겠다는 의미

                //  웹에 접속하자마자 인증을 요구, 홈화면, 로그인 화면 등등 모든 화면까지 인증을 요구하게됨.
                .authorizeRequests()
                    //  원하는 요청들에 대해서 인증 요구를 제외하겠다.
                    //  제외되지 않은 url 요청 시 /author/login-screen 으로 redirect 된다.
                    .antMatchers("/", "/author/create", "/author/login-screen")
                    .permitAll()
                    //  제외되지 않은 모든 요청은 인증을 요구하겠다.
                    .anyRequest().authenticated()

                //  세션 로그인이 아니라, 토큰로그인일 경우
                //  .sessionManagement().sessionCreationPolicy(SessionCreationPolicy sessionCreationPolicy)
                .and().formLogin()
                    .loginPage("/author/login-screen")
                    //  doLogin 메소드는 스프링에서 미리 구현.
                    .loginProcessingUrl("/doLogin")
                        //  doLogin 에 넘겨줄 email, password 속성명을 지정해야함.
                        //  form tag 안의 name 과 맞춰야한다.
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(new LoginSuccessHandler())
                //  spring security 에 구현되어 있는 doLogout 기능을 그대로 사용한다.
                .and().logout().logoutUrl("/doLogout")
                .and()
                .build();
    }
}
