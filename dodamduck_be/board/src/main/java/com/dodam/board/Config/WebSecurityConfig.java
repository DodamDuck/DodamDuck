package com.dodam.board.Config;

import com.dodam.board.Filter.JwtAuthencationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    JwtAuthencationFilter jwtAuthencationFilter;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                // cors 정책 (현재 Application에서 작업을 해뒀으므로 기본 설정 사용)
                .cors().and()
                // csrf 대책 (현재 CSRF 대책 비활성화)
                .csrf().disable()
                // Basic 인증 (현재 Bearer token 인증방법 사용하므로 비활성화)
                .httpBasic().disable()
                // 세션 기반 인증 (현재 Session 기반 인증을 사용하지 않으므로 상태를 없앰)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 인증없이 허용
                .authorizeRequests().antMatchers("/","/api/auth/**").permitAll()
                // 나머지 Request는 모두 인증된 사용자만 사용하게 함
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(jwtAuthencationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
