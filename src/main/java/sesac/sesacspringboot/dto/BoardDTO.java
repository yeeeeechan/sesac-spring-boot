package sesac.sesacspringboot.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class BoardDTO {
    private String title;
    private String content;
    private String writer;
    private int id;
    private Timestamp registered;
}
