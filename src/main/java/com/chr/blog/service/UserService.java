package com.chr.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chr.blog.model.RoleType;
import com.chr.blog.model.User;
import com.chr.blog.repository.UserRepository;


//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌(IoC를 해준다.=대신 메모리에 띄워준다.)
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired // DI 가 되어, 주입이 된다.
	private BCryptPasswordEncoder encoder;

	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); //1234 원문
		String encPassword=encoder.encode(rawPassword); // 해쉬
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
}
