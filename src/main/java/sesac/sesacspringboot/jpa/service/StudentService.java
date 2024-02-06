package sesac.sesacspringboot.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sesac.sesacspringboot.jpa.dto.StudentDTO;
import sesac.sesacspringboot.jpa.entity.Student;
import sesac.sesacspringboot.jpa.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<StudentDTO> getStudentAll() {
        List<Student> result = studentRepository.findAll();
        List<StudentDTO> students = new ArrayList<>();

        for ( Student s: result ) {
            // Builder : 객체를 만들 때 순서에 의해 생기는 문제, 명시적이지 못한 문제를 해결하기 위해 나온 방법
            // 생성자 주입 : (단점)여러 개의 필드가 있을 때 순서를 지켜줘야 한다.
            // setter : (단점)필드 개수만큼 매번 메소드를 만들어줘야 한다.

            StudentDTO studentDTO = StudentDTO.builder()
                    .name(s.getName())
                    .nickname(s.getNickname())
                    .build(); // 만들고자 하는 필드 값들을 넣고 마지막에 .build();를 붙여주면 만들어짐. DTO에 빌더 어노테이션 달아줘야 함
//            StudentDTO studentDTO = new StudentDTO();
//            studentDTO.setName("이름") ...
            students.add(studentDTO);
        }
        return students;
    }
}
