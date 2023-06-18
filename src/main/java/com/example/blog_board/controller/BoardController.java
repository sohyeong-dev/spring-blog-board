package com.example.blog_board.controller;

import com.example.blog_board.domain.Board;
import com.example.blog_board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

//    @Autowired
//    WebApplicationContext servletAC; // Servlet AC 주입

    @Autowired @Qualifier("boardServiceImpl")
    private final BoardService boardService;

    @GetMapping("/hello")
    public String Hello(){
        return "/board/hello";
    }

    @GetMapping("/test")
    public String test(Model model){
        int boardCount = boardService.boardCount();
        List<Board> boardList = boardService.boardList();

        model.addAttribute("cnt", boardCount);
        model.addAttribute("test", boardList);

        return "/board/hello";
    }

    @GetMapping
    public String main(Model model){
        /* TODO board 리스트 조회 구현 */
        List<Board> boards = boardService.boardList();
        model.addAttribute("boards", boards);

        return "/board/boards";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId, Model model){
        /* TODO board id로 board 조회 */
        Board board = boardService.findById(boardId);
        model.addAttribute("board", board);

        if (board == null) {
            // 조회 실패

            throw new BoardNotFoundException();
        }

        return "/board/board";
    }

    @PostMapping("/add")
    public String add(@RequestParam String title, @RequestParam String content,
                      @RequestParam String name, RedirectAttributes redirectAttributes){
        /* TODO board 객체 생성하여 board 추가 */
        Board board = new Board(title, content, name);
        Long boardId = boardService.add(board);

        redirectAttributes.addAttribute("boardId", boardId);
        redirectAttributes.addAttribute("status", true);

        return "redirect:/boards/{boardId}";
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model){
        /* TODO 수정 작업 전에 board id로 기존 board 조회 */
        Board findBoard = boardService.findById(boardId);
        if (findBoard == null) {
            throw new BoardNotFoundException("조회 실패");
        }
        model.addAttribute("board", findBoard);

        return "board/editForm";
    }

    @PostMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, @RequestParam String title,
                           @RequestParam String content, @RequestParam String name)
    {


        /* TODO 수정 작업 board 속성 값을 받아 board 업데이트 */
        Board board = boardService.findById(boardId);
        board.setTitle(title);
        board.setContent(content);
        board.setName(name);
        boardService.update(board);

        return "redirect:/boards/{boardId}";
    }

    @GetMapping("/{boardId}/delete")
    public String deleteBoard(@PathVariable Long boardId){
        /* TODO board id에 해당하는 board 삭제 */;
        boardService.deleteById(boardId);
        return "redirect:/boards";
    }

    @GetMapping("/add")
    public String add(){
        return "/board/addForm";
    }



    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String boardNotFound(Model model) {
        return main(model);
    }

}

/**
 * 조회 실패 Exception
 */
class BoardNotFoundException extends RuntimeException {

    BoardNotFoundException(String msg) {
        super(msg);
    }

    BoardNotFoundException() {
        super("");
    }
}