package sesac.sesacspringboot.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import sesac.sesacspringboot.mybatis.domain.Board;

import java.util.List;

@Mapper
public interface BoardMapper {

    // sql을 작성하거나, xml을 작성하거나
    // 1) mapper에는 메소드가 있고, xml에는 없는 경우 -> 실행했을 때 오류 발생
    // 2) xml에는 있고, mapper에는 없는 경우 -> 실행 자체가 안 된다.

    // 모든 게시글 조회
    List<Board> getAllContent();

    // 게시판 글 작성
    @Insert("insert into board(title, writer, content) values(#{title}, #{writer}, #{content})")
    void createContent(String title, String writer, String content);

    // 게시판 글 작성(해설 코드-xml)
//    void createContent();

    // 게시글 전체 조회(검색어와 일치하는 제목 게시글 조회)
//    @Select("select count(title) from board where title=#{title}")
//    int searchContent(String title);

    List<Board> searchContent(String title);

    // 게시판 글 수정
    @Update("update board set content=#{content}, title=#{title}, writer=#{writer} where id=#{id}")
    void editContent(String content, String title, String writer, int id);

    // 게시판 글 삭제
    @Delete("delete from board where id=#{id}")
    void deleteContent(int id);
}
