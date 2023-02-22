package com.chr.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chr.blog.model.Board;
import com.chr.blog.model.RoleType;
import com.chr.blog.model.User;
import com.chr.blog.repository.BoardRepository;
import com.chr.blog.repository.UserRepository;


//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌(IoC를 해준다.=대신 메모리에 띄워준다.)
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void 글쓰기(Board board, User user) {	// title, board
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	public List<Board> 글목록(){
		return boardRepository.findAll();
	}
}
