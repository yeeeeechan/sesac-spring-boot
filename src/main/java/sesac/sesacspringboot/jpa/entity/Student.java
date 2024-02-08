package sesac.sesacspringboot.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // *class Student() {} // 빈 생성자가 필수로 필요하다.
//: 데이터 베이스의 필드와 변수의 연관관계가 정의된 클래스
//: db 테이블에 대응되는 하나의 클래스
// @Table(name="Student") 
// -> 테이블 이름을 강제로 지정하고 싶을 때. (+ properties에 추가 설정 필요) (기본적으로 대문자로 작성하더라도 jpa에서 생성할 땐 소문자로 들어감)

@NoArgsConstructor // *entity가 필요로 하는 빈 생성자
@Getter
@Builder // *필드가 전체 다 들어있는 생성자가 필수로 필요하다.
@AllArgsConstructor // *builder가 필요로하는 생성자 => entity와 builder사이에 충돌이 일어났기 때문에 각각 써줘야 함
public class Student {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // -> auto_increment
    private int id;
    // id int primary key auto_increment
    // SEQUENCE : 새로운 오브젝트를 만들어서 id를 부여하는 방법 (mysql에는 없음)
    // TABLE : SEQUENCE 전략을 흉내낸 전략 -> 모든 DBMS에서 사용 가능

    @Column(name = "name", nullable = false, length = 20)
    private String name;
    // name varchar(20) not null

    @Column(columnDefinition = "TEXT") // String과 구분하기 위해 별도로 text 타입 정의
    private String nickname;

    // enum 타입
    @Column
    @Enumerated(EnumType.STRING) // 스프링부트 버전 유의 (3.0 안 됨)
    private LoginType type;
    public enum LoginType {
        GOOGLE, KAKAO, NAVER;
    }
}
