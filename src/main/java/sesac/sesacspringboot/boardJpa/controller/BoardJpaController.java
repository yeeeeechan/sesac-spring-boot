package sesac.sesacspringboot.boardJpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sesac.sesacspringboot.boardJpa.dto.BoardDTO;
import sesac.sesacspringboot.boardJpa.service.BoardJpaService;


import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardJpaController {

    @Autowired
    BoardJpaService boardService;

    // 게시판 메인
    @GetMapping("")
    public String getContent(Model model) {
//        List<BoardDTO> boardList = BoardJpaService.getAllContent();
//        model.addAttribute("boardList", boardList);
        return "board";
    }

    // 게시판 글 작성
    @PostMapping("/write")
    @ResponseBody // 응답을 주기 위함
    public boolean contentInsert(@RequestBody BoardDTO boardDTO) {
//        BoardJpaService.createContent(BoardDTO boardDTO);
        return true;
    }

    // 제목과 일치하는 게시글 수 반환
    @GetMapping("/search")
    @ResponseBody
    public int searchContent(@RequestParam String title) {
//        return BoardJpaService.searchContent(title);
        return 1;
    }

    // 게시글 수정
    @PatchMapping("/edit")
    @ResponseBody
    public void getEditContent(@RequestBody BoardDTO boardDTO) {
//        BoardJpaService.patchBoard(boardDTO);
    }

    // 게시글 삭제
    @DeleteMapping("")
    @ResponseBody
    public void deleteContent(@RequestParam int id) {
//        BoardJpaService.deleteContent(id);
    }
}
