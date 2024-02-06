package sesac.sesacspringboot.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sesac.sesacspringboot.mybatis.dto.BoardDTO;
import sesac.sesacspringboot.mybatis.service.BoardService;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    // 게시판 메인
    @GetMapping("")
    public String getContent(Model model) {
        List<BoardDTO> boardList = boardService.getAllContent();
        model.addAttribute("boardList", boardList);
        return "board";
    }

    // 게시판 글 작성
    @PostMapping("/write")
    @ResponseBody // 응답을 주기 위함
    public boolean contentInsert(@RequestBody BoardDTO boardDTO) {
        boardService.createContent(boardDTO.getTitle(), boardDTO.getContent(), boardDTO.getWriter());
        // boardService.createContent(BoardDTO boardDTO); //해설 코드
        return true;
    }

    // 제목과 일치하는 게시글 수 반환
//    @GetMapping("/search")
//    @ResponseBody
//    public int searchContent(@ModelAttribute BoardDTO boardDTO) {
//        return boardService.searchContent(boardDTO.getTitle());
//    }

    @GetMapping("/search")
    @ResponseBody
    public int searchContent(@RequestParam String title) {
        return boardService.searchContent(title);
    }

    // 게시글 수정
    @PatchMapping("/edit")
    @ResponseBody
    public String editContent(@RequestBody BoardDTO boardDTO) {
        return boardService.editContent(boardDTO.getContent(), boardDTO.getTitle(), boardDTO.getWriter(), boardDTO.getId());
    }

    // 해설 코드
    // (@ResponseBody 어노테이션을 안 붙이면) 템플릿 파일을 보여주는데, void라면 현재 템플릿을 그대로 다시 보여준다.
    // void인 경우 responseBody를 보내지만, 응답 본문이 비어 있음
//    public void getEditContent(@RequestBody BoardDTO boardDTO) {
//      boardService.patchBoard(boardDTO);
//    }

    // 게시글 삭제
    @DeleteMapping("")
    @ResponseBody
    public void deleteContent(@RequestParam int id) {
        boardService.deleteContent(id);
    }
}
