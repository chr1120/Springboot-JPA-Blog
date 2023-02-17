package com.chr.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

// Bean 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것 //@Configuration

@Configuration // Bean 등록 (IoC 관리)
@EnableWebSecurity // Security 필터가 등록이 된다.
@EnableMethodSecurity(prePostEnabled=true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig {
	
	// 회원가입을 위한 함수
	@Bean // IoC가 된다. - 함수의 리턴값을 Spring이 관리. - 필요할 때마다 가져와서 쓰면 된다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable() // csrf 토큰 비활성화 (테스트시 걸어두는게 좋음)
			.authorizeHttpRequests(request -> request
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					.requestMatchers("/","/auth/**","/js/**","/css/**","/image/**").permitAll()
					.anyRequest().authenticated()
		)
			.formLogin(login -> login
					.loginPage("/auth/loginForm").permitAll()
		);
		return http.build();
		}
}