package com.chr.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.chr.blog.config.auth.PrincipalDetailService;

import jakarta.servlet.DispatcherType;
import lombok.AllArgsConstructor;

// Bean 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것 //@Configuration

@Configuration // Bean 등록 (IoC 관리)
@EnableWebSecurity // Security 필터가 등록이 된다.
@EnableMethodSecurity(prePostEnabled=true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
@AllArgsConstructor
public class SecurityConfig {
	
	@Autowired
	private PrincipalDetailService PrincipalDeatilService;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
	
	// 회원가입을 위한 함수
	@Bean // IoC가 된다. - 함수의 리턴값을 Spring이 관리. - 필요할 때마다 가져와서 쓰면 된다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인해주는데 password를 가로채기를 한다.
	// 이때 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬와 비교할 수 있다.
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(PrincipalDeatilService).passwordEncoder(encodePWD());
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
					.loginProcessingUrl("/auth/loginProc")		
					.defaultSuccessUrl("/") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
		);
		return http.build();
		}
}
