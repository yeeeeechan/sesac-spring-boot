package sesac.sesacspringboot.api.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class Person {
@GetMapping("/people")
    public String getPerson(Model model) {
        ArrayList<Person1> people = new ArrayList<>();

        people.add(new Person1("kim", 10));
        people.add(new Person1("lee", 20));
        people.add(new Person1("hong", 30));
        people.add(new Person1("park", 40));
        people.add(new Person1("shin", 50));

        model.addAttribute("people", people);

    Person1 p = new Person1("hi", 100);
    System.out.println(p.getName());
    // lombok -> 실행을 할 때 적용되기 때문에 인텔리제이가 getter/setter가 생성되지 않았다고 인식해서 오류 표시 생길 수 있음

        return "people";
    }

    @Getter
    @Setter
    // lombok -> class가 갖고 있는 모든 요소에 대한 Getter와 Setter를 생성한다.
    // 특정 요소에 대한 Getter와 Setter만 생성하고 싶다면 각 요소 위에 작성한다.
    public static class Person1 {
        private String name;
        private int age;

        public Person1(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}