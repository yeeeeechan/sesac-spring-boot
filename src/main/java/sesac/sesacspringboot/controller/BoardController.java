package sesac.sesacspringboot.controller;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sesac.sesacspringboot.dto.BoardDTO;
import sesac.sesacspringboot.service.BoardService;
import sesac.sesacspringboot.vo.BoardVO;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    // 게시판 메인
    @GetMapping("/board")
    public String getContent(Model model) {
        List<BoardDTO> boardList = boardService.getAllContent();
        model.addAttribute("boardList", boardList);
        return "board";
    }

    // 게시판 글 작성
    @PostMapping("/write")
    @ResponseBody
    public void getContentInsert(@RequestBody BoardDTO boardDTO) {
        boardService.createContent(boardDTO.getTitle(), boardDTO.getContent(), boardDTO.getWriter());
    }

    // 제목과 일치하는 게시글 수 반환
    @GetMapping("/board/search")
    @ResponseBody
    public int getSearchContent(@ModelAttribute BoardDTO boardDTO) {
        return boardService.searchContent(boardDTO.getTitle());
    }

    // 게시글 수정
    @PatchMapping("/board")
    @ResponseBody
    public String getEditContent(@RequestBody BoardDTO boardDTO) {
        return boardService.editContent(boardDTO.getContent(), boardDTO.getTitle(), boardDTO.getWriter(), boardDTO.getId());
    }

    // 게시글 삭제
    @DeleteMapping("/board")
    @ResponseBody
    public void getDeleteContent(@RequestParam int id) {
        boardService.deleteContent(id);
    }
}
