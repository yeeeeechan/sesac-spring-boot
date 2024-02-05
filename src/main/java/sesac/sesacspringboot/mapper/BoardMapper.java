package sesac.sesacspringboot.mapper;

import org.apache.ibatis.annotations.*;
import sesac.sesacspringboot.domain.Board;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 모든 게시글
    List<Board> getAllContent();

    // 게시판 글 작성
    @Insert("insert into board(title, writer, content) values(#{title}, #{writer}, #{content})")
    void createContent(String title, String writer, String content);

    // 게시글 전체 조회(검색어와 일치하는 제목 게시글 조회)
    @Select("select count(title) from board where title=#{title}")
    int searchContent(String title);

    // 게시판 글 수정
    @Update("update board set values(content=#{content}, title=#{title}, writer=#{writer}) where id=#{id}")
    void editContent(String content, String title, String writer, int id);

    // 게시판 글 삭제
    @Delete("delete from board where id=#{id}")
    void deleteContent(int id);
}
