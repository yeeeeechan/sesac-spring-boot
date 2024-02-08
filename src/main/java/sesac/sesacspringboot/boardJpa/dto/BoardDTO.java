package sesac.sesacspringboot.boardJpa.dto;

import lombok.Getter;
import lombok.Setter;

// 필드 이름과 일치하지 않아도 상관없음
@Getter
@Setter
public class BoardDTO {
    private String title, content, writer, registered;
    private int id;
    private int no;
}
