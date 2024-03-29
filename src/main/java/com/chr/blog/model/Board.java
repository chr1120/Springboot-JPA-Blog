package com.chr.blog.model;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable=false, length=100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인이 됨.

	private int count; //조회수
	
	// Many=Board, User=One // 한 명의 User는 여러개의 게시글을 쓸 수 있다는 의미
		// FetchType.EAGER : 네가 Board 테이블을 SELECT하면 User 정보는 바로(무조건) 가져올게.
		// 왜? 한 건 밖에 없기 때문.
		// 이것이 적혀있지 않으면, 기본적으로 LAZY 전략이다.
		//  LAZY : 필요할 때 가져온다.
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="userId")
	private User user; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
	
	// 하나의 게시글은 여러개의 답변이 가능하다.
		// mappedBy : 연관관계의 주인이 아니다. (난 FK가 아니에요) DB에 칼럼을 만들지 마세요.
		// 나는 그냥 Board를 통해서 SELECT할 때, Join문을 통해서 값을 얻기 위해 필요한겁니다.
		// @ JoinColumn(name="replyId") // 실제 MySQL 테이블에 replyId라는 FK가 필요없기 때문.
	@OneToMany(mappedBy="board", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE) 
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@CreatedDate
	private LocalDateTime createDate;
}
