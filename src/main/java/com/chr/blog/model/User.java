package com.chr.blog.model;

import java.security.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//ORM -> Java(다른 언어들도 포함) Object -> 테이블로 매핑해주는 기술
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다.
//@DynamicInsert // insert시에 null인 필드를 제외시켜준다.
public class User {
	// 모든 table에는 프라이머리 키가 있어야 한다.
	
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //  프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스(오라클), auto_increment(mysql)
	
	@Column(nullable=false, length=100, unique=true)
	private String username; //아이디
	
	@Column(nullable=false, length=100) // 123456 => 해쉬(비밀번호 암호화)
	private String password;
	
	@Column(nullable=false, length=50)
	private String email;
	
	// @ColumnDefault("user")
	// DB는 RoleType 이라는게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. - 어떤 데이터의 도메인을 만들어줄 수 있다. // ADMIN, USER
	
	private String oauth; // kakao, google
	
	@CreatedDate // 시간이 자동 입력
	// 회원이 가입한 시간
	private LocalDateTime createDate;
}
