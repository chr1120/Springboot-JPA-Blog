package com.chr.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chr.blog.model.User;

// 자동으로 bean 등록이 된다. // 스프링 IoC에서 객체를 가지고 있다.
// 즉, @Repository 생략 가능하다.
public interface UserRepository extends JpaRepository<User, Integer>{

}
