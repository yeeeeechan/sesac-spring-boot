package sesac.sesacspringboot.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sesac.sesacspringboot.jpa.dto.StudentDTO;
import sesac.sesacspringboot.jpa.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class Controller {
//    @GetMapping("/count")
//    public int getCountAll() {}

    @Autowired
    StudentService studentService;

    @GetMapping("/all")
    public List<StudentDTO> getAll() {
//        student의 목록을 전부 가져와서 보여주는 api
        List<StudentDTO> result = studentService.getStudentAll();
        return result;
//        return studentService.getStudentAll();
    }

//    @GetMapping("/search")
//    public ? getSearch(@RequestBody int id){}
}
