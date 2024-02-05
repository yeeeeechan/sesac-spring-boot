package sesac.sesacspringboot.api2.controller;

// MyBatis 수업 컨트롤러

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sesac.sesacspringboot.api2.dto.UserDTO;
import sesac.sesacspringboot.api2.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user") // 아래에서 실행되는 메소드 앞에 공통으로 여기에 정의한 url 붙임
public class UserController {
    // C, R
    // 1. table 생성 완료 (user)
    // 2. domain 만들기 (domain/user)
    // 3. mapper 만들기
    // 4. service 만들기
    // 5. controller 만들기

    @Autowired
    UserService userService;
    @GetMapping("/all") // /user/all -> 실제 url
    public List<UserDTO> getUser() {
        List<UserDTO> result = userService.retreiveAll();
        return result; // 결과 []
    }

    @GetMapping("/user") // /user/user
    public String getUserInsert(@RequestParam String name, @RequestParam String nickname) {
        userService.createUser(name, nickname);
        return "Success";
    }

    @GetMapping("/update")
    public String getUserUpdate(@RequestParam int id, @RequestParam String nickname) {
        userService.updateUser(id, nickname);
        return "ID " + id + " is Updated";
    }

    @GetMapping("/delete")
    public String getUserDelete(@RequestParam int id) {
        userService.deleteUser(id);
        return "ID " + id + " is Deleted";
    }
}
