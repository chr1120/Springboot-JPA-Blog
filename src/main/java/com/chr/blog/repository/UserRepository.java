package com.chr.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chr.blog.model.User;

// 자동으로 bean 등록이 된다. // 스프링 IoC에서 객체를 가지고 있다.
// 즉, @Repository 생략 가능하다.
public interface UserRepository extends JpaRepository<User, Integer>{
	// SELECT * FROM user WHERE username=1?;
	Optional<User> findByUsername(String username);
}

//로그인을 위한 함수 만들기
	// JPA Naming 쿼리
	// SELECT * FROM user WHERE username=?(1) AND password=?(2)
	// User findByUsernameAndPassword(String username, String password);

//@Query(value='SELECT * FROM user WHERE username=?(1) AND password=?(2), nativeQuery=true)
//User login(String username, String password);
