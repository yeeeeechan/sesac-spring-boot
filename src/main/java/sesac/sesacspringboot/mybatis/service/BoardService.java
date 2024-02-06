package sesac.sesacspringboot.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sesac.sesacspringboot.mybatis.domain.Board;
import sesac.sesacspringboot.mybatis.dto.BoardDTO;
import sesac.sesacspringboot.mybatis.mapper.BoardMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardMapper boardMapper;

    // 전체 게시글
    public List<BoardDTO> getAllContent() {
        List<Board> boards = boardMapper.getAllContent();
        List<BoardDTO> boardList = new ArrayList<>();

        for (Board c : boards) {
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setTitle(c.getTitle());
            boardDTO.setContent(c.getContent());
            boardDTO.setWriter(c.getWriter());
            boardDTO.setId(c.getId());
            boardDTO.setNo(100 + c.getId());
            boardDTO.setRegistered(String.valueOf(c.getRegistered()));

            boardList.add(boardDTO);
        }
        return boardList;
    }

    // 게시판 글 작성
    public void createContent(String title, String writer, String content) {
        boardMapper.createContent(title, writer, content);
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
        List<Board> result = boardMapper.searchContent(title);
        return result.size();
    }

    // 게시판 글 수정
    public String editContent(String content, String title, String writer, int id) {
        boardMapper.editContent(content, title, writer, id);
        return content;
    }

    // 게시판 글 삭제
    public void deleteContent(int id) {
        boardMapper.deleteContent(id);
    }
}
