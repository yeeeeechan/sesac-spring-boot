package sesac.sesacspringboot.mybatis.vo;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class BoardVO {
    int id;
    private Timestamp registered;
}
