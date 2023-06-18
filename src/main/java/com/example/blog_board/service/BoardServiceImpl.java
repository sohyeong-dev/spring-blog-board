package com.example.blog_board.service;

import com.example.blog_board.domain.Board;
import com.example.blog_board.mapper.BoardMapper;
import com.example.blog_board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public int boardCount() {
        return boardMapper.boardCount();
    }

    @Override
    public List<Board> boardList() {
        return boardMapper.findAll();
    }

    @Override
    public Board findById(Long boardId) {
        return boardMapper.findById(boardId);
    }

    @Override
    public Long add(Board board) {
        boardMapper.save(board);
        return board.getBoardId();
    }

    @Override
    public Long update(Board board) {
        return boardMapper.update(board);
    }

    @Override
    public void deleteById(Long boardId) {

        boardMapper.delete(boardId);
    }
}
