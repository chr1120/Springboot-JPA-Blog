package com.chr.blog.model;

import java.security.Timestamp;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable=false, length=200)
	private String content;
	
	@ManyToOne
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne	// 여러개의 답변을 하나의 유저가 쓸 수 있다.
	@JoinColumn(name="userId")
	private User user;
	
	@CreatedDate
	private LocalDateTime createDate;
}
