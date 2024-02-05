package sesac.sesacspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sesac.sesacspringboot.domain.Board;
import sesac.sesacspringboot.dto.BoardDTO;
import sesac.sesacspringboot.mapper.BoardMapper;

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
            boardDTO.setRegistered(c.getRegistered());

            boardList.add(boardDTO);
        }
        return boardList;
    }

    // 게시판 글 작성
    public void createContent(String title, String writer, String content) {
        boardMapper.createContent(title, writer, content);
    }

    // 게시글 전체 조회(검색어와 일치하는 제목 게시글 조회)
    public int searchContent(String title) {
        return boardMapper.searchContent(title);
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
