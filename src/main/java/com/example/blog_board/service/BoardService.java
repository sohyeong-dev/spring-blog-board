package com.example.blog_board.service;

import com.example.blog_board.domain.Board;
import com.example.blog_board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface BoardService {

    /* TODO */
    // BoardService를 implement 하여 BoardService 클래스 생성
    // BoardMapper를 참고

    public int boardCount();
    public List<Board> boardList();

    public Board findById(Long boardId);

    public Long add(Board board);

    public Long update(Board board);
    public void deleteById(Long boardId);

}
