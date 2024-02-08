package sesac.sesacspringboot.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sesac.sesacspringboot.jpa.dto.StudentDTO;
import sesac.sesacspringboot.jpa.entity.Student;
import sesac.sesacspringboot.jpa.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class Controller {
//    @GetMapping("/count")
//    public int getCountAll() {}

    @Autowired
    StudentService studentService;

    //    1. 전체 검색 (select * from student)
    @GetMapping("/all")
    public List<StudentDTO> getAll() {
//        student의 목록을 전부 가져와서 보여주는 api
        List<StudentDTO> result = studentService.getStudentAll();
        return result;
//        return studentService.getStudentAll();
    }

    //    2. 삽입 (insert A into ~~)
    @GetMapping("/insert") // /student/insert?name=이름
    public String insertStudent(@RequestParam String name,
                                @RequestParam String nickname,
                                @RequestParam Student.LoginType type) {
        // 이름, 닉네임, 로그인 타입
        return studentService.insertStudent(name, nickname, type);
    }

    //    3. 조건에 따른 검색( select * from student where name='')
    @GetMapping("/search/name") // /student/search/name?name=이름
    public String searchStudentByName(@RequestParam String name) {
        return studentService.searchStudentByName(name);
    }

    //    4. 조건에 따른 검색(2) (select * from student where id = )
    @GetMapping("/search/id")
    public String searchStudentById(@RequestParam int id) {
        return studentService.searchStudentById(id);
    }

    // 닉네임으로 검색해서 일치하는 값 찾기
    @GetMapping("/search/count")
    public String countByNickname(@RequestParam String nickname) {
        return studentService.countNickname(nickname);
    }

    // 부분 업데이트
    @GetMapping("/update")
    public String updateStudentById(@RequestParam int id, @RequestParam String name) {
        return studentService.updateStudentName(id, name);
    }

//    @GetMapping("/search")
//    public ? getSearch(@RequestBody int id){}
}
