package sesac.sesacspringboot.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sesac.sesacspringboot.jpa.dto.StudentDTO;
import sesac.sesacspringboot.jpa.entity.Student;
import sesac.sesacspringboot.jpa.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public String insertStudent(String name, String nickname, Student.LoginType type) {
        // 받아온 데이터로 repository의 save 메소드를 호출
        Student student = Student.builder().name(name).nickname(nickname).type(type).build();
        Student newStudent = studentRepository.save(student); // save는 저장할 엔티티를 보내면 저장한 엔티티를 반환해준다.
        // newStudent : save를 한 후 반환되는 entity 객체
        
        return newStudent.getId() + newStudent.getName();
    }

    public String searchStudentByName(String name) {
        List<Student> student = studentRepository.findByName(name);

        return "해당 사용자는 " + student.size() + "명입니다.";
    }

    public String searchStudentById(int id) {
//        Student student = StudentRepository.findById(id);

        // findById와 같이 단일 값을 찾을 땐 null이 나올 수 있으므로 optional로 받고,
        // 그 외엔 List로 받으면 된다.
        
        // optional은 null일 때 일어날 수 있는 에러를 처리하는 방법을 모아둔 객체

        Student student = studentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("사용자가 없다!"));
        // orElse() : 있으면 반환하고 없으면 다른 값 반환
        // orElseThrow() : 있으면 반환하고 없으면 error 처리
            return student.getName();
//        Optional<Student> student = studentRepository.findById(id);
//        if(student.isPresent())  {
//            // ispresent : 객체 여부를 boolean으로 반환
//            return student.get().getName();
//            // get : Optional에 담긴 객체를 반환
//        }
//        return "null";


        // Optional<T> : java 8부터 등장
        // null일 수도 있는 객체를 감싸는 wrapper 클래스
    }

    public String countNickname(String nickname) {
        int countStudent = studentRepository.countByNickname(nickname);
        return "일치하는 사용자는 총 " + countStudent + "명입니다.";
    }

    // save(T) : 새로운 entity를 insert 하거나, 기존 entity를 update한다.
    // T의 기본값(pk)의 상태에 따라 다르게 동작
    // - pk값이 존재하는 경우, pk와 연결된 entity를 update
    // - pk값이 존재하지 않는 경우, 새로운 entity를 insert
    public String updateStudentName(int id, String name) {
        // 불러온 student는 VO이므로, value에 직접 접근해 수정할 수 없음
        // 그렇기 때문에 updateStudent로 수정하고자 하는 객체를 새롭게 만들어서 사용한다. (기존 객체는 건드리지 않아야 함!)
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("일치하는 사용자가 없음"));

        // save를 동작시키기 위해 entity를 새로 만들어 넣는다.
        Student updateStudent = studentRepository
                .save(Student.builder().nickname(student.getNickname())
                        .id(student.getId()).type(student.getType())
                        .name(name).build());

        return updateStudent.getName() + " 수정 완료!";
    }
}
