package sesac.sesacspringboot.api2.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import sesac.sesacspringboot.api2.domain.User;

import java.util.List;


// mapper는 interface로 정의되어야 함
@Mapper
public interface UserMapper {
    // sql문을 정의하거나, xml 파일을 읽거나 둘 중 하나의 작업을 진행함
    // sql문을 정의하는 경우엔 어노테이션 붙음

    // xml 파일을 읽어서 실행하겠다.
    List<User> retreiveAll();

    // sql문 정의
    @Insert("insert into user(name, nickname) values(#{name}, #{nickname})") // 전달받은 변수를 넣는 방법 -> #{변수명}
    void createUser(String name, String nickname);

    @Update("update user set nickname=#{nickname} where id=#{id}")
    void updateUser(int id, String nickname);

    @Delete("delete from user where id=#{id}")
    void deleteUser(int id);

}
