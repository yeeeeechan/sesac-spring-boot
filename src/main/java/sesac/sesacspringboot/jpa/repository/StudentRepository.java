package sesac.sesacspringboot.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sesac.sesacspringboot.jpa.entity.Student;

import java.util.List;

@Repository
// JpaRepository<대상으로 지정할 엔티티, 해당 엔티티의 pk 타입>
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // 조건 검색 메소드 만들기
    // 1. jpa의 기본 규칙을 따르는 방법 (메소드, 키워드 사용)
    // findBy 컬럼명

    List<Student> findByName(String name); // 이름으로 찾음 / primary ket, unique key로 찾음
    //findByNickname -> 닉네임으로 찾음
    // 검색 결과가 여러 개일 경우엔 list로 받기
    
    List<Student> findByNameAndNickname(String name, String nickname); // 이름과 닉네임이 모두 일치하는 경우
    List<Student> findByNameOrNickname(String name, String nickname); // 이름이 일치하거나 닉네임이 일치하는 경우
    
    // findByAgeGreaterThanEqual(ing age); // age가 변수로 보내진 값보다 크거나 같은 경우 검색

    // 2. 직접 쿼리를 작성해서 연결
    // from 뒤에 쓰는 건 entity명 -> JpaRepository<Student, Integer>
//    @Query("select s from Student s where s.name = :a") 이렇게 쓰거나,
    @Query(nativeQuery = true, value="select * from student where name= :a")
    List<Student> findTest(String a);

    // 닉네임으로 일치하는 데이터 개수 찾기
    int countByNickname(String nickname);

    // 일치하는 id의 name 값 변경하기
//    @Modifying
//    @Transactional
//    @Query(nativeQuery = true, value = "update student set name= :name")
//    String updateName(String name);
}
