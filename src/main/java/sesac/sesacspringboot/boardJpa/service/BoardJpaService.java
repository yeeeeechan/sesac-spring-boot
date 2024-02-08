package sesac.sesacspringboot.boardJpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sesac.sesacspringboot.boardJpa.repository.BoardRepository;
import sesac.sesacspringboot.mybatis.domain.Board;
import sesac.sesacspringboot.mybatis.dto.BoardDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardJpaService {

    @Autowired
    BoardRepository boardRepository;

    // 전체 게시글
//    public List<BoardDTO> getAllContent() {
//        List<Board> boards = boardRepository.findAll();
//        List<BoardDTO> boardList = new ArrayList<>();
//
//        for (Board c : boards) {
//            BoardDTO boardDTO = new BoardDTO();
//            boardDTO.setTitle(c.getTitle());
//            boardDTO.setContent(c.getContent());
//            boardDTO.setWriter(c.getWriter());
//            boardDTO.setId(c.getId());
//            boardDTO.setNo(100 + c.getId());
//            boardDTO.setRegistered(String.valueOf(c.getRegistered()));
//
//            boardList.add(boardDTO);
//        }
//        return boardList;
//    }

    // 게시판 글 작성
    public void createContent(String title, String writer, String content) {
    }

    // 게시판 글 작성(해설 코드)
//    public boolean createContent(BoardDTO boardDTO) {
//        Board board = new Board();
//        board.setContent(boardDTO.getContent());
//        board.setContent(boardDTO.getContent());
//        board.setWriter(boardDTO.getWriter());
//
//        return true;
//    }

    // 게시글 전체 조회(검색어와 일치하는 제목 게시글 조회)
//    public int searchContent(String title) {
//        return boardMapper.searchContent(title);
//    }

    public int searchContent(String title) {
        return 1;
    }

    // 게시판 글 수정
    public String editContent(String content, String title, String writer, int id) {
        return null;
    }

    // 게시판 글 삭제
    public void deleteContent(int id) {

    }
}
